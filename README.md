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
checkPermissions() => any
```

**Returns:** <code>any</code>

--------------------


### requestPermissions()

```typescript
requestPermissions() => any
```

**Returns:** <code>any</code>

--------------------


### exportImageToGallery(...)

```typescript
exportImageToGallery(options: { data: string; }) => any
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ data: string; }</code> |

**Returns:** <code>any</code>

--------------------


### Interfaces


#### PermissionStatus

| Prop          | Type                                                                      |
| ------------- | ------------------------------------------------------------------------- |
| **`gallery`** | <code>"prompt" \| "prompt-with-rationale" \| "granted" \| "denied"</code> |


#### ExportResponse

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
Still working on Android support. 
