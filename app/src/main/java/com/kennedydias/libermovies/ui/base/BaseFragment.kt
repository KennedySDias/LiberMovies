package com.kennedydias.libermovies.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment(), OnBackPressed {

    private var noConnectionDialogCallback: (() -> Unit)? = null
    private val noConnectionDialog: AlertDialog? by lazy {
        context?.let { currentContext ->
            val builder = AlertDialog.Builder(currentContext)
            builder.setTitle("No Connection")
            builder.setMessage("There is no internet connection. \nCheck it and try again.")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                noConnectionDialogCallback?.invoke()
            }
            builder.create()
        }
    }
    private val snackBarLayout: View by lazy {
        (activity?.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    protected fun invalidateOptionsMenu() {
        activity?.invalidateOptionsMenu()
    }

    protected fun finish() {
        activity?.finish()
    }

    protected fun setResult(result: Int) {
        activity?.setResult(result)
    }

    protected fun setResult(result: Int, intent: Intent) {
        activity?.setResult(result, intent)
    }

    protected fun setSupportActionBar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    protected fun setTitle(titleId: Int) {
        (activity as? AppCompatActivity)?.supportActionBar?.setTitle(titleId)
    }

    protected fun setTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    protected fun showBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun showNoConnectionDialog(callback: (() -> Unit)? = null) {
        noConnectionDialogCallback = callback
        if (noConnectionDialog?.isShowing == false) {
            noConnectionDialog?.show()
        }
    }

    fun hideNotConnectedDialog() {
        if (noConnectionDialog?.isShowing == true) {
            noConnectionDialog?.dismiss()
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
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}