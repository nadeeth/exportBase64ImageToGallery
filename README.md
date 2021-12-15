# export-base64-image-to-gallery

A Capacitor plugin to export base64 encoded images to device gallery.

## Install

```bash
npm install export-base64-image-to-gallery
npx cap sync
```

## API

<docgen-index>

* [`checkPermissions()`](#checkpermissions)
* [`requestPermissions()`](#requestpermissions)
* [`exportImageToGallery(...)`](#exportimagetogallery)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### checkPermissions()

```typescript
checkPermissions() => Promise<GalleryPermissionStatus>
```

**Returns:** <code>Promise&lt;GalleryPermissionStatus&gt;</code>

--------------------


### requestPermissions()

```typescript
requestPermissions() => Promise<GalleryPermissionStatus>
```

**Returns:** <code>Promise&lt;GalleryPermissionStatus&gt;</code>

--------------------


### exportImageToGallery(...)

```typescript
exportImageToGallery(options: { data: string; }) => Promise<GalleryExportResponse>
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ data: string; }</code> |

**Returns:** <code>Promise&lt;GalleryExportResponse&gt;</code>

--------------------


### Interfaces


#### GalleryPermissionStatus

| Prop          | Type                                                                      |
| ------------- | ------------------------------------------------------------------------- |
| **`gallery`** | <code>"prompt" \| "prompt-with-rationale" \| "granted" \| "denied"</code> |


#### GalleryExportResponse

| Prop          | Type                 |
| ------------- | -------------------- |
| **`success`** | <code>boolean</code> |
| **`error`**   | <code>string</code>  |

</docgen-api>

## iOS
Add Photo Libarary usage descriptions in the Info.plist file under dict tag, or with Xcode.
```xml
<key>NSPhotoLibraryAddUsageDescription</key>
<string>Access to photo library to save photos.</string>
<key>NSPhotoLibraryUsageDescription</key>
<string>Access to photo library to save photos</string>
```

## Android
Add permissions for External Storage in your app's AndroidManifest.xml file.
```xml
<!-- Permissions -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## Example
This example is using TypeScript, but you can use this plugin in any Capacitor project. 

1. Import the plugin and response types.
```typescript
import { ExportBase64ImageToGallery, GalleryExportResponse, GalleryPermissionStatus } from 'export-base64-image-to-gallery';
```

2. Implementation. 
This is a full code example that covers all the plugin methods. Consider deviding this into two or more methods in a real implementation. 
```typescript
const base64Image: string = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAA.........";
let exportStatus: GalleryExportResponse;

const currentPermissionStatus: GalleryPermissionStatus = await ExportBase64ImageToGallery.checkPermissions();
if (currentPermissionStatus.gallery === 'prompt' || currentPermissionStatus.gallery === 'prompt-with-rationale') {
    // Consider providing an explanation to the user for 'prompt-with-rationale' case.
    const requestedPermissionStatus: GalleryPermissionStatus = await ExportBase64ImageToGallery.requestPermissions();
    if (requestedPermissionStatus.gallery === 'granted') {
        exportStatus = await ExportBase64ImageToGallery.exportImageToGallery({data: base64Image});
    } else {
        // Show a message to the user indicating the app can't export image without permission.
        // If they want to use the feature they have to allow permission in device settings.
        console.log('Gallery Export permission denied at prompt:', requestedPermissionStatus);
    }
} else if (currentPermissionStatus.gallery === 'denied') {
    // Show a message to the user explaining they have denied access before.
    // Enalbe it in settings if they want to use the feature.
    console.log('Gallery Export permission denied already:', currentPermissionStatus);
} else if (currentPermissionStatus.gallery === 'granted') {
    exportStatus = await ExportBase64ImageToGallery.exportImageToGallery({data: base64Image});
}

if (exportStatus.success === true) {
    // Export successful - Show a success message
    console.log('Export success:', exportStatus);
} else {
    // Export failed - 
    console.log('Export failed with error:', exportStatus.error);
}
``` 
