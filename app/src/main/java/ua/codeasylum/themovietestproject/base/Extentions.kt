package ua.codeasylum.themovietestproject.base

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import ua.codeasylum.themovietestproject.App

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun App.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}


fun Fragment.showToast(text: String) {
    Toast.makeText(this.context,text, Toast.LENGTH_LONG).show()
}