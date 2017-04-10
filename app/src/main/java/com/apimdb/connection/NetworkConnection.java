package com.apimdb.connection;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ZUP on 10/04/2017.
 */

public class NetworkConnection {
    private static NetworkConnection instance;
    private Context context;
    private RequestQueue requestQueue;

        public NetworkConnection(Context c)
        {
            context = c;
            requestQueue = getRequestQueue();
        }

        public NetworkConnection getInstance(Context c){
            if(instance== null){
                instance = new NetworkConnection(c.getApplicationContext());
            }
            return(instance);
        }

        public RequestQueue getRequestQueue(){
            if(requestQueue == null){
                requestQueue = Volley.newRequestQueue(context);
            }
            return (requestQueue);
        }
        public <T> void addRequestQueue(Request<T> request){
            getRequestQueue().add(request);
        }


}
