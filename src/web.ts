import { WebPlugin } from '@capacitor/core';

import type { ExportBase64ImageToGalleryPlugin, ExportResponse, PermissionStatus } from './definitions';

export class ExportBase64ImageToGalleryWeb
  extends WebPlugin
  implements ExportBase64ImageToGalleryPlugin {
  async checkPermissions(): Promise<PermissionStatus> {
    return new Promise((resolve, _reject) => resolve);
  }
  async requestPermissions(): Promise<PermissionStatus> {
    return new Promise((resolve, _reject) => resolve);
  }
  async exportImageToGallery(options: { data: string }): Promise<ExportResponse> {
    console.log('exportImageToGallery', options);
    return new Promise((resolve, _reject) => resolve);
  }
}
