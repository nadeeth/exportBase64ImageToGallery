package com.butterflyray.plugins.exportimage

import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

@CapacitorPlugin(name = "ExportBase64ImageToGallery")
class ExportBase64ImageToGalleryPlugin : Plugin() {
    private val implementation = ExportBase64ImageToGallery()
    @PluginMethod
    fun echo(call: PluginCall) {
        val value = call.getString("value")
        val ret = JSObject()
        ret.put("value", value?.let { implementation.echo(it) })
        call.resolve(ret)
    }

    @PluginMethod
    fun exportImageToGallery(call: PluginCall) {
        val value = call.getString("data")
        val ret = JSObject()
        ret.put("success", value?.let { implementation.echo(it) })
        ret.put("error", "no")
        call.resolve(ret)
    }
}