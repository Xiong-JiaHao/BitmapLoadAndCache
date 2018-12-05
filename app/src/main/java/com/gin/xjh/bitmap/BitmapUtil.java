package com.gin.xjh.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {

    public static Bitmap ratio(String filePath, int piexlW, int piexlH) {
        BitmapFactory.Options newOptions = new BitmapFactory.Options();
        newOptions.inJustDecodeBounds = true;
        newOptions.inPreferredConfig = Bitmap.Config.RGB_565;//压缩质量参数
        //预加载
        BitmapFactory.decodeFile(filePath, newOptions);

        int originalW = newOptions.outWidth;
        int originalH = newOptions.outHeight;

        newOptions.inSampleSize = getSimpleSize(originalW, originalH, piexlW, piexlH);
        newOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, newOptions);
    }

    private static int getSimpleSize(int originalW, int originalH, int piexlW, int piexlH) {
        int simpkeSize = 1;
        if (originalW > originalH && originalW > piexlW) {
            simpkeSize = originalW / piexlW;
        } else if (originalW < originalH && originalH > piexlH) {
            simpkeSize = originalH / piexlH;
        }
        if (simpkeSize <= 0) {
            simpkeSize = 1;
        }
        return simpkeSize;
    }

}
