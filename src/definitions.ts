import type { PermissionState } from '@capacitor/core';

export interface PermissionStatus {
  gallery: PermissionState;
}
export interface ExportBase64ImageToGalleryPlugin {
  checkPermissions(): Promise<PermissionStatus>;
  requestPermissions(): Promise<PermissionStatus>;
  exportImageToGallery(options: { data: string }): Promise<{"success": boolean, "error": string}>;
}
