package com.kanykeinu.cinematica.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

fun <T> MutableLiveData<T>.observe(lifecycleOwner: LifecycleOwner, handler:((T) -> Unit)){
    if (!hasObservers()) {
        observe(lifecycleOwner, Observer<T>(handler))
    }
}

fun View.showSnackBar(text : String){
    Snackbar.make(this,text, Snackbar.LENGTH_LONG).show()
}

fun Context.showToast(text : String){
    Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}