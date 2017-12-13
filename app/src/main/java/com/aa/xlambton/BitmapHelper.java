package com.aa.xlambton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by araceliteixeira on 12/12/17.
 */

public class BitmapHelper {
    public static String saveBitmapInDisk(Context context, Bitmap bitmap, String fileName) {
        String path = context.getExternalFilesDir(null) + "/" + fileName;
        File file = new File(path);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static Bitmap getScaledBitmap(Context context, String path) {
        // Get the dimensions of the View
        int targetW = 300;
        int targetH = 300;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap img = BitmapFactory.decodeFile(path, bmOptions);
        File filePhoto = new File(path);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, filePhoto);

        try {
            ExifInterface ei;
            if (Build.VERSION.SDK_INT > 23) {
                ei = new ExifInterface(context.getContentResolver().openInputStream(uri));
            } else {
                ei = new ExifInterface(path);
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    img = rotateImage(img, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    img = rotateImage(img, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    img = rotateImage(img, 270);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }
}
