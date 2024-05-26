package com.example.swipeproducts.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.swipeproducts.databinding.ProgressDialogBinding


fun Fragment.showToast(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

@SuppressLint("Recycle")
fun Uri.isValidImage(context: Context): Boolean {

    // checking image format
    var strMimeType: String? = null
    val cursor = context.contentResolver.query(
        this,
        arrayOf(MediaStore.MediaColumns.MIME_TYPE),
        null, null, null
    )
    if (cursor != null && cursor.moveToNext()) {
        strMimeType = cursor.getString(0)
    }
    Log.d("iamg" , strMimeType.toString())
    if(
        !strMimeType.equals("image/png")&&
        !strMimeType.equals("image/jpeg")&&
        !strMimeType.equals("image/jpg")
    ){
        Toast.makeText(
            context,
            "Please select an image in JPEG or PNG format.",
            Toast.LENGTH_LONG
        ).show()
        return false
    }



    // Checking if  the aspect ratio of the image is  (1:1 ratio) or not
    val inputStream = context.contentResolver.openInputStream(this)
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeStream(inputStream, null, options)
    inputStream?.close()

    val width = options.outWidth
    val height = options.outHeight
    Log.d("dimens" , "$width $height")
    if(width != height){
        Log.d("dimens" , "$width $height")
        Toast.makeText(
            context,
            "Please select an image with a 1:1 aspect ratio.",
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    return true
}

fun Fragment.showDialog(message: String){
     var dialog : AlertDialog? = null
    val progress = ProgressDialogBinding.inflate(LayoutInflater.from(requireContext()))
    progress.tvMessage.text = message
    dialog   = AlertDialog.Builder(requireContext()).setView(progress.root).setCancelable(false).create()
    dialog.show()
}