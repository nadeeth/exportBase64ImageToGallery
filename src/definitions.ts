import type { PermissionState } from '@capacitor/core';

export interface PermissionStatus {
  gallery: PermissionState;
}
export interface ExportResponse {
  success: boolean,
  error: string
}
export interface ExportBase64ImageToGalleryPlugin {
  checkPermissions(): Promise<PermissionStatus>;
  requestPermissions(): Promise<PermissionStatus>;
  exportImageToGallery(options: { data: string }): Promise<ExportResponse>;
}
