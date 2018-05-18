package ro.irian.well.well_delivery.camera;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageData {

    private Uri uri;
    private Uri newUri;
    private Bitmap bitmap;

    public ImageData(Uri uri, Bitmap bitmap) {
        this.uri = uri;
        this.bitmap = bitmap;
    }

    public ImageData() {
    }

    public ImageData(Uri uri, Uri newUri, Bitmap bitmap) {
        this.uri = uri;
        this.newUri = newUri;
        this.bitmap = bitmap;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Uri getNewUri() {
        return newUri;
    }

    public void setNewUri(Uri newUri) {
        this.newUri = newUri;
    }
}

