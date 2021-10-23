import type { PermissionState } from '@capacitor/core';

export interface PermissionStatus {
  gallery: PermissionState;
}
export interface ExportBase64ImageToGalleryPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  checkPermissions(): Promise<PermissionStatus>;
  requestPermissions(): Promise<PermissionStatus>;
  exportImageToGallery(options: { data: string }): Promise<{"success": boolean, "error": string}>;
}
