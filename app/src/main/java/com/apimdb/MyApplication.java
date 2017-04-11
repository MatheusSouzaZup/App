package com.apimdb;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ZUP on 11/04/2017.
 */

public class MyApplication extends Application {
    private  static MyApplication myInstance;
    private static RequestQueue myRequestQueue;
    private static String TAG ="DEFAULT";
    @Override
    public void onCreate() {
        super.onCreate();
        myInstance = this;
    }
    public static synchronized MyApplication getInstance() {
        return myInstance;
    }
    public RequestQueue getRequestQueue() {
        if(myRequestQueue == null)
        {

            myRequestQueue = Volley.newRequestQueue(this.getApplicationContext());
        }
        return myRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag)? TAG : tag);
        getRequestQueue().add(request);
    }
    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequests(Object tag){
        if(myRequestQueue!= null)
        {
            myRequestQueue.cancelAll(tag);
        }
    }
}
