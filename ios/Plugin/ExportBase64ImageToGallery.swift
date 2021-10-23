import Foundation
import Capacitor
import Photos

@objc public class ExportBase64ImageToGallery: NSObject {

    private var globalCall: CAPPluginCall = CAPPluginCall()
    
    @objc public func checkPermissions() -> String {
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
        return galleryState
    }
    
    @objc public func requestPermissions(_ call: CAPPluginCall) -> Void {
        PHPhotoLibrary.requestAuthorization({ (newStatus) in
            if (newStatus == PHAuthorizationStatus.authorized) {
                call.resolve(["gallery": "granted"])
            } else {
                call.resolve(["gallery": "denied"])
            }
        })
    }
    
    @objc public func exportImageToGallery(_ call: CAPPluginCall) {
        let string = call.getString("data");
        let dataArray = string!.components(separatedBy: [","])
        let imageData = Data.init(base64Encoded: dataArray[1], options: .init(rawValue: 0))
        if let image = UIImage(data: imageData!)
        {
            self.globalCall = call;
            UIImageWriteToSavedPhotosAlbum(image, self, #selector(savedImage), nil)
        }
    }
    
    @objc public func savedImage(_ im:UIImage, error:Error?, context:UnsafeMutableRawPointer?) -> Void {
        if let err = error {
            self.globalCall.resolve(["success": false, "error": err.localizedDescription])
            NSLog(err.localizedDescription)
        }
        self.globalCall.resolve(["success": true, "error": ""])
    }
}
