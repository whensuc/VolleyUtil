package com.dyg.health.work.volleytest.utils;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wzy on 2015-10-9.
 */
public class VolleyUtil {
    private String url = "http://www.xxx.com";
    private static VolleyUtil volleyUtil;
    private RequestQueue requestQueue;
    private static List<ImageLoader.ImageContainer> ics;
    private VolleyUtil(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 单例模式
     * @param context
     * @return
     */
    public static VolleyUtil getInstance(Context context){
        if(volleyUtil==null){
             ics = new ArrayList < ImageLoader.ImageContainer > ();
            volleyUtil = new VolleyUtil(context);
        }
        return volleyUtil;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    /**
     *
     * @param url : 请求图片url
     * @param imageView : 图片控件
     * @param defimgid : 默认显示图片资源id
     * @param errorimgid : 加载失败显示图片资源id
     */
    public  void loadImage(String url,ImageView imageView,int defimgid,int errorimgid){
        if(requestQueue==null) {
            throw new BusinessRuntimeException("requestQueue未实例化");
        }
        ImageLoader imageLoader = new ImageLoader(requestQueue, BitmapCache.instance());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defimgid,errorimgid);
        ImageLoader.ImageContainer ic = imageLoader.get(url, listener);
        ics.add(ic);
    }

    public List<ImageLoader.ImageContainer> getImageContainers(){
        return ics;
    }

    /**
     *
     * @param url : 请求图片url
     * @param networkImageView : Volley图片控件
     */
    public  void loadImage(String url,NetworkImageView networkImageView){
        if(requestQueue==null) {
            throw new BusinessRuntimeException("requestQueue未实例化");
        }
        ImageLoader imageLoader = new ImageLoader(requestQueue, BitmapCache.instance());
        networkImageView.setImageUrl(url,imageLoader);

    }

    /**
     *
     * @param url : get请求url
     * @param listener : 请求监听
     * @param errorListener : 请求失败监听
     */
    public void httpGetRequest(String url,Response.Listener<String> listener,Response.ErrorListener errorListener){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,listener,errorListener);
        if(requestQueue==null) {
            throw new BusinessRuntimeException("requestQueue未实例化");
        }
        requestQueue.add(stringRequest);
    }

    /**
     *
     * @param url : post请求url
     * @param params : 请求参数
     * @param listener : 请求监听
     * @param errorListener : 请求失败监听
     */
    public void httpPostRequest(String url,final Map<String,String> params,Response.Listener<String> listener,Response.ErrorListener errorListener){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,  listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
