import { WebPlugin } from '@capacitor/core';

import type { ExportBase64ImageToGalleryPlugin, PermissionStatus } from './definitions';

export class ExportBase64ImageToGalleryWeb
  extends WebPlugin
  implements ExportBase64ImageToGalleryPlugin {
  async checkPermissions(): Promise<PermissionStatus> {
    return new Promise((resolve, _reject) => resolve);
  }
  async requestPermissions(): Promise<PermissionStatus> {
    return new Promise((resolve, _reject) => resolve);
  }
  async exportImageToGallery(options: { data: string }): Promise<{"success": boolean, "error": string}> {
    console.log('exportImageToGallery', options);
    return new Promise((resolve, _reject) => resolve);
  }
}
