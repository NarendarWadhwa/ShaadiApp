package com.example.shaadi.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shaadi.R
import com.example.shaadi.ShaadiApp
import de.hdodenhof.circleimageview.CircleImageView


fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.hideKeyboard() {
    val view = this.currentFocus
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view?.let {
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

@BindingAdapter("visibleGone")
fun View.showHide(status: Boolean) {
    this.visibility = if (status) View.VISIBLE else View.GONE
}

@BindingAdapter("showStatus")
fun AppCompatTextView.showStatus(status: Int) {
    this.text = when (status) {
        1 -> {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.accepted))
            "Member Accepted"
        }
        -1 -> {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.rejected))
            "Member Declined"
        }
        else -> "NA"
    }
}

@BindingAdapter("loadUrl")
fun CircleImageView.loadUrl(imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        Glide.with(this.context)
            .load(imageUrl)
            .into(this)
    }
}

fun isNetworkAvailable(): Boolean {
    val cm =
        ShaadiApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm.activeNetwork != null
    } else {
        val activeNetwork = cm.activeNetworkInfo ?: return false
        return activeNetwork.isConnected
    }
}

