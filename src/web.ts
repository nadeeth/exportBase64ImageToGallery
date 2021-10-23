import { WebPlugin } from '@capacitor/core';

import type { ExportBase64ImageToGalleryPlugin } from './definitions';

export class ExportBase64ImageToGalleryWeb
  extends WebPlugin
  implements ExportBase64ImageToGalleryPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
