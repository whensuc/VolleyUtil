package com.dyg.health.work.volleytest.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by wzy on 2015-10-9.
 */
public class BitmapCache implements ImageLoader.ImageCache {
   /* private LruCache<String, Bitmap> mCache;

    public BitmapCache() {
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }*/
   private static LruCache<String, Bitmap> mMemoryCache;

    private static BitmapCache lruImageCache;

    private BitmapCache(){
        // Get the Max available memory
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap){
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    public static BitmapCache instance(){
        if(lruImageCache == null){
            lruImageCache = new BitmapCache();
        }
        return lruImageCache;
    }

    @Override
    public Bitmap getBitmap(String arg0) {
        return mMemoryCache.get(arg0);
    }

    @Override
    public void putBitmap(String arg0, Bitmap arg1) {
        if(getBitmap(arg0) == null){
            mMemoryCache.put(arg0, arg1);
        }
    }
}
