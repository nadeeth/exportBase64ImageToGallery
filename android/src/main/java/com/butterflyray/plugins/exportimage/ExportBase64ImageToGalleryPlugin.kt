package com.butterflyray.plugins.exportimage

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.insertImage
import android.provider.SyncStateContract.Helpers.insert
import android.util.Base64
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import java.io.File
import java.io.File.separator
import java.io.FileOutputStream
import java.io.OutputStream

@CapacitorPlugin(name = "ExportBase64ImageToGallery")
class ExportBase64ImageToGalleryPlugin : Plugin() {

    private val implementation = ExportBase64ImageToGallery()

    @PluginMethod
    fun exportImageToGallery(call: PluginCall) {
        val base64String = call.getString("data")

        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.count())

        val ret = JSObject()
        try {
            implementation.saveImage(decodedImage, context, "DrawingGrid")
            ret.put("success", true)
            ret.put("error", "")
            call.resolve(ret)
        } catch (e: Exception) {
            ret.put("success", true)
            ret.put("error", e.message)
            call.resolve(ret)
        }
    }
}