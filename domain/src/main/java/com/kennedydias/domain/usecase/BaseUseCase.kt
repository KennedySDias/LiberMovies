package com.kennedydias.domain.usecase

import com.kennedydias.commom.expection.NotConnectedException
import com.kennedydias.commom.expection.UnauthorizedException
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeoutException
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = UseCase.Request<T>.() -> Unit

abstract class UseCase<T> {

    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) { executeOnBackground() }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (throwable: Exception) {
                val error = when (throwable) {
                    is HttpException -> {
                        if (throwable.code() == 401) {
                            UnauthorizedException()
                        } else {
                            throwable
                        }
                    }

                    // handle api call timeout error
                    is SocketTimeoutException -> {
                        TimeoutException()
                    }

                    // handle connection error
                    is IOException -> {
                        NotConnectedException()
                    }

                    is UnknownHostException -> {
                        NotConnectedException()
                    }
                    else -> throwable
                }

                response(error)
            }
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((Exception) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (Exception) -> Unit) {
            onError = block
        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }

        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: Exception) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }

    }

}