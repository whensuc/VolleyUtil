# VolleyUtil
Volley工具类
---
1. ***Volley介绍***
	
	Volley是Google在2013年推出的一个新的网络通信框架。Volley集AsyncHttpClient和Universal-Image_Loader的优点集于一身，既可以像AsyncHttpCLient一样非常简单地进行HTTP通信，也可以像Universal-Image-Loader一样轻松加载网络上的图片。除了简单易用之外，Volley在性能方面也进行了大幅度的调整，它的设计目标就是非常合适去进行数据量不大，但通信频繁的网络操作，而对于大数据量的网络操作，比如说下载文件等，Volley并不适合。
	项目中有volly-1.0.11.jar。

2. ***代码简介***

	VolleyUtil单例模式实现。


		/**
	    /* 单例模式
	    /* @param context
	    /* @return
	    /*/
	    <p>public static VolleyUtil getInstance(Context context){
	        if(volleyUtil==null){
	             ics = new ArrayList < ImageLoader.ImageContainer > ();
	            volleyUtil = new VolleyUtil(context);
	        }
	        return volleyUtil;
	    }



		     /**
		     /*
		     /* @param url : 请求图片url
		     /* @param networkImageView : Volley图片控件
		     */
		  public  void loadImage(String url,NetworkImageView        networkImageView){
		        if(requestQueue==null) {
		            throw new BusinessRuntimeException("requestQueue未实例化");
		        }
		        ImageLoader imageLoader = new ImageLoader(requestQueue, BitmapCache.instance());
		        networkImageView.setImageUrl(url,imageLoader);
		
		    }


  BitmapCache图片缓存类
  	
	public class BitmapCache implements ImageLoader.ImageCache {
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
    }