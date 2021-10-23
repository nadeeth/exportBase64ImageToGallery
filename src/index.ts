import { registerPlugin } from '@capacitor/core';

import type { ExportBase64ImageToGalleryPlugin } from './definitions';

const ExportBase64ImageToGallery = registerPlugin<ExportBase64ImageToGalleryPlugin>(
  'ExportBase64ImageToGallery',
  {
    web: () => import('./web').then(m => new m.ExportBase64ImageToGalleryWeb()),
  },
);

export * from './definitions';
export { ExportBase64ImageToGallery };
