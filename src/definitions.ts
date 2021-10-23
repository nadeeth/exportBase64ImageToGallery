export interface ExportBase64ImageToGalleryPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
