package com.itemboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.util.Log;

import com.turbomanage.httpclient.AsyncCallback;
import com.turbomanage.httpclient.ParameterMap;
import com.turbomanage.httpclient.HttpResponse;
import com.turbomanage.httpclient.android.AndroidHttpClient;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AndroidHttpClient httpClient = new AndroidHttpClient("http://173.255.210.55:7000");
        httpClient.setMaxRetries(5);
        httpClient.addHeader("Accept", "application/json");
        httpClient.addHeader("Content-Type", "application/json");
        ParameterMap params = httpClient.newParams();
        httpClient.get("/thing", params, new AsyncCallback() {
        	@Override
            public void onComplete(HttpResponse httpResponse) {
                //System.out.println(httpResponse.getBodyAsString());
        		if(httpResponse != null) {
        			Log.i("itemboard", httpResponse.getBodyAsString());
        		}
            }
            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
