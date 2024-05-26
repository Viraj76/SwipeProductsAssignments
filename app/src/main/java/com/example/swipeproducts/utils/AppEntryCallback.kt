package com.example.swipeproducts.utils

interface AppEntryCallback {

    // just a callback to ensure we first read the user entry
    fun onAppEntryRead(isEntered: Boolean)
}
