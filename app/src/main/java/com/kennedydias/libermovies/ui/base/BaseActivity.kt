package com.kennedydias.libermovies.ui.base

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    protected open var containerId: Int = -1
    private var noConnectionDialogCallback: (() -> Unit)? = null
    private val noConnectionDialog: AlertDialog by lazy {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Connection")
        builder.setMessage("There is no internet connection. \nCheck it and try again.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            noConnectionDialogCallback?.invoke()
        }
        builder.create()
    }
    private val snackBarLayout: View by lazy {
        (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(containerId)
        val shouldCallParent: Boolean? = (fragment as? OnBackPressed)?.onBackPressed()?.not()
        if (shouldCallParent == null || shouldCallParent == true) {
            super.onBackPressed()
        }
    }

    fun showNoConnectionDialog(callback: (() -> Unit)? = null) {
        noConnectionDialogCallback = callback
        if (!noConnectionDialog.isShowing) {
            noConnectionDialog.show()
        }
    }

    fun hideNotConnectedDialog() {
        if (noConnectionDialog.isShowing) {
            noConnectionDialog.dismiss()
        }
    }

    fun showSnackbar(message: String) {
        Snackbar.make(snackBarLayout, message, Snackbar.LENGTH_LONG).apply {
            setAction("Ok") {
                dismiss()
            }
            show()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}