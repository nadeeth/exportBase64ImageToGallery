package com.butterflyray.plugins.exportimage

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.getcapacitor.*
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.annotation.Permission
import com.getcapacitor.annotation.PermissionCallback

@CapacitorPlugin(
    name = "ExportBase64ImageToGallery",
    permissions = [
        Permission(
            strings = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            alias = "gallery"
        )
    ]
)
class ExportBase64ImageToGalleryPlugin : Plugin() {

    private val implementation = ExportBase64ImageToGallery()

    @PluginMethod
    override fun checkPermissions(call: PluginCall) {
        val ret = JSObject()
        when {
            ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                ret.put("gallery", PermissionState.GRANTED)
            }
            shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                ret.put("gallery", PermissionState.PROMPT_WITH_RATIONALE)
            }
            else -> {
                ret.put("gallery", PermissionState.PROMPT)
            }
        }
        call.resolve(ret)
    }

    @PluginMethod
    override fun requestPermissions(call: PluginCall) {
        requestAllPermissions(call, "requestPermissionsCallback");
    }

    @PermissionCallback
    fun requestPermissionsCallback(pluginCall: PluginCall) {
        val permissionsResult =
            permissionStates
        Log.i("PERMISSION", permissionsResult.toString())
        if (permissionsResult.isEmpty()) {
            // if no permissions are defined on the plugin, resolve undefined
            pluginCall.resolve()
        } else {
            val permissionsResultJSON = JSObject()
            for ((key, value) in permissionsResult) {
                permissionsResultJSON.put(key, value)
            }
            pluginCall.resolve(permissionsResultJSON)
        }
    }

    @PluginMethod
    fun exportImageToGallery(call: PluginCall) {
        val base64String = call.getString("data")
        val base64ImageData = base64String?.split(",")?.toTypedArray()
        val imageBytes = Base64.decode(base64ImageData?.get(1), Base64.DEFAULT)
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