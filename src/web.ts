import { WebPlugin } from '@capacitor/core';

import type { ExportBase64ImageToGalleryPlugin, GalleryExportResponse, GalleryPermissionStatus } from './definitions';

export class ExportBase64ImageToGalleryWeb
  extends WebPlugin
  implements ExportBase64ImageToGalleryPlugin {
  async checkPermissions(): Promise<GalleryPermissionStatus> {
    return new Promise((resolve, _reject) => resolve);
  }
  async requestPermissions(): Promise<GalleryPermissionStatus> {
    return new Promise((resolve, _reject) => resolve);
  }
  async exportImageToGallery(options: { data: string }): Promise<GalleryExportResponse> {
    console.log('exportImageToGallery', options);
    return new Promise((resolve, _reject) => resolve);
  }
}
