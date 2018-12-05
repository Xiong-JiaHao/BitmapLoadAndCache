package com.gin.xjh.bitmap;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

public class SimpleImageLoader {

    private static SimpleImageLoader mLoader = null;

    private LruCache<String, Bitmap> mLruCache;

    public static SimpleImageLoader getInstance() {
        if (mLoader == null) {
            synchronized (SimpleImageLoader.class) {
                if (mLoader == null) {
                    mLoader = new SimpleImageLoader();
                }
            }
        }
        return mLoader;
    }

    /**
     * 初始化缓存对象
     */
    private SimpleImageLoader() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    /**
     * 加载网络图片
     */
    public void displayImage(ImageView view, String url) {
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
        } else {
            downloadImage(view, url);
        }
    }

    /**
     * 从缓存中读取图片
     */
    private Bitmap getBitmapFromCache(String url) {
        return mLruCache.get(url);
    }

    /**
     * 将下载下来的图片保存到缓存中
     */
    private void putBitmapToCache(Bitmap bitmap, String url) {
        if (bitmap != null) {
            mLruCache.put(url, bitmap);
        }
    }

    /**
     * 下载图片，并且加载到缓存中
     */
    private void downloadImage(final ImageView imageView, final String url) {
        //进行图片的网络下载功能
        //在图片下载成功后调用
        Bitmap bitmap = null;
        putBitmapToCache(bitmap, url);
    }

}
