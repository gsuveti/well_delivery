package ro.irian.well.well_delivery.camera;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageData {

    private Uri uri;
    private Bitmap bitmap;

    public ImageData(Uri uri, Bitmap bitmap) {
        this.uri = uri;
        this.bitmap = bitmap;
    }

    public ImageData() {
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
}

