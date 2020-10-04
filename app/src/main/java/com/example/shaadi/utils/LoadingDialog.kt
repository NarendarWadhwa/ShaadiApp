package com.example.shaadi.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.example.shaadi.R
import com.example.shaadi.databinding.DialogProgressBinding

class LoadingDialog(context: Context) : AppCompatDialog(context, R.style.customDialog) {

    private val dialogProgressBinding: DialogProgressBinding =
        DialogProgressBinding.inflate(LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(dialogProgressBinding.root)
        window!!.setLayout(WRAP_CONTENT, WRAP_CONTENT)
    }

    fun setMessage(msg: String) {
        dialogProgressBinding.txtMessage.text = msg
    }

}