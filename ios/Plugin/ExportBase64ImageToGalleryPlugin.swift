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
        let galleryState: String

        switch PHPhotoLibrary.authorizationStatus() {
        case .notDetermined:
            galleryState = "prompt"
        case .restricted, .denied, .limited:
            galleryState = "denied"
        case .authorized:
            galleryState = "granted"
        @unknown default:
            galleryState = "prompt"
        }

        call.resolve(["gallery": galleryState])
    }

    @objc override public func requestPermissions(_ call: CAPPluginCall) {
        PHPhotoLibrary.requestAuthorization({ (newStatus) in
            if (newStatus == PHAuthorizationStatus.authorized) {
                call.resolve(["gallery": "granted"])
            } else {
                call.resolve(["gallery": "denied"])
            }
        })
    }
    
    @objc func exportImageToGallery(_ call: CAPPluginCall) {
        let string = call.getString("data");
        let imageData = Data.init(base64Encoded: string!, options: .init(rawValue: 0))
        if let image = UIImage(data: imageData!)
        {
            self.globalCall = call;
            UIImageWriteToSavedPhotosAlbum(image, self, #selector(savedImage), nil)
        }
    }
    
    @objc func savedImage(_ im:UIImage, error:Error?, context:UnsafeMutableRawPointer?) -> Void {
        if let err = error {
            self.globalCall.resolve(["success": false, "error": err.localizedDescription])
            NSLog(err.localizedDescription)
        }
        self.globalCall.resolve(["success": true, "error": ""])
    }
}
