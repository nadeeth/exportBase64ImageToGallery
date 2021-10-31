import type { PermissionState } from '@capacitor/core';

export interface GalleryPermissionStatus {
  gallery: PermissionState;
}
export interface GalleryExportResponse {
  success: boolean,
  error: string
}
export interface ExportBase64ImageToGalleryPlugin {
  checkPermissions(): Promise<GalleryPermissionStatus>;
  requestPermissions(): Promise<GalleryPermissionStatus>;
  exportImageToGallery(options: { data: string }): Promise<GalleryExportResponse>;
}
