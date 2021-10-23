import Foundation
import Capacitor

import Photos
import UIKit

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(ExportBase64ImageToGalleryPlugin)
public class ExportBase64ImageToGalleryPlugin: CAPPlugin {
    private let implementation = ExportBase64ImageToGallery()
    private var globalCall: CAPPluginCall = CAPPluginCall()

    @objc override public func checkPermissions(_ call: CAPPluginCall) {
        call.resolve(["gallery": implementation.checkPermissions()])
    }

    @objc override public func requestPermissions(_ call: CAPPluginCall) {
       implementation.requestPermissions(call)
    }
    
    @objc func exportImageToGallery(_ call: CAPPluginCall) {
        implementation.exportImageToGallery(call)
    }
}
