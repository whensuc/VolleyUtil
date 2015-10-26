package com.dyg.health.work.volleytest.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dyg.health.work.volleytest.R;
import com.dyg.health.work.volleytest.adapter.InfoAdapter;
import com.dyg.health.work.volleytest.modle.Info;
import com.dyg.health.work.volleytest.utils.VolleyUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzy on 2015-10-9.
 */
public class MainActivity extends Activity {
    private PullToRefreshListView pulllistView;
    //private ListView listView;
    private List<Info> infos;
    private InfoAdapter adapter;
    private VolleyUtil volleyUtil;
    int lastItem;
    TextView tv_load;
    private int mFirstVisibleItem; //
    private int mVisibleItemCount;//
    List<ImageLoader.ImageContainer> listic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewithline);
       /* MyGridView gridview=(MyGridView) findViewById(R.id.gridview);
        gridview.setAdapter(new MyGridAdapter(this));*/
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        volleyUtil = VolleyUtil.getInstance(this);
        tv_load = (TextView)findViewById(R.id.tv_loadmsg);
        listic = new ArrayList<ImageLoader.ImageContainer>();
        pulllistView = (PullToRefreshListView)findViewById(R.id.lv_showimg);
        pulllistView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        pulllistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> listViewPullToRefreshBase) {
                new GetDownDataTask().execute();
            }
        });
        pulllistView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //Toast.makeText(MainActivity.this,""+lastItem+","+adapter.getCount()+","+scrollState,Toast.LENGTH_LONG).show();
                if (lastItem-1 == adapter.getCount() && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                   // Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG).show();
                    tv_load.setVisibility(View.VISIBLE);
                    tv_load.setText("加载更多....");
                    new GetDownDataTask2().execute();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount - 1 ;
            }
        });
        //listView = (ListView)findViewById(R.id.lv_showimg);
       /* ListView actualListView = pulllistView.getRefreshableView();
        registerForContextMenu(actualListView);
        SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(this);
        pulllistView.setOnPullEventListener(soundListener);*/
        //pulllistView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        infos = new ArrayList<Info>();
        adapter = new InfoAdapter(MainActivity.this,R.layout.listview_image,infos);
       // pulllistView.setAdapter(adapter);
        pulllistView.setAdapter(adapter);

        String url = "http://192.168.191.1:8080/dygappbackstage/test";
        volleyUtil.httpGetRequest(url,new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("Test","--->"+s);
                Gson gson  =new Gson();
                infos = (List<Info>)gson.fromJson(s,new TypeToken<List<Info>>() {
                }.getType());
                adapter.refresh(infos);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG).show();
            }
        });
    }
    private class GetDownDataTask extends AsyncTask<Void, Void, List<Info>> {

        //���߳��������
        @Override
        protected List<Info> doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return infos;
        }

        //���̸߳���UI
        @Override
        protected void onPostExecute(List<Info> result) {
            String url = "http://192.168.191.1:8080/dygappbackstage/test";
            volleyUtil.httpGetRequest(url,new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Gson gson  =new Gson();
                    infos = (List<Info>)gson.fromJson(s,new TypeToken<List<Info>>() {
                    }.getType());
                    adapter.refresh(infos);
                    pulllistView.onRefreshComplete();
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MainActivity.this,"网络异常",Toast.LENGTH_LONG).show();
                }
            });
           // mAdapter.notifyDataSetChanged();

            //֪ͨRefreshListView �����Ѿ��������
            // Call onRefreshComplete when the list has been refreshed.


            super.onPostExecute(result);
        }
    }
    private class GetDownDataTask2 extends AsyncTask<Void, Void, List<Info>> {


        @Override
        protected List<Info> doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return infos;
        }


        @Override
        protected void onPostExecute(List<Info> result) {
            String url = "http://192.168.191.1:8080/dygappbackstage/test";
            volleyUtil.httpGetRequest(url,new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Gson gson  =new Gson();
                    infos = (List<Info>)gson.fromJson(s,new TypeToken<List<Info>>() {
                    }.getType());
                    tv_load.setVisibility(View.GONE);
                    adapter.loadmore(infos);
                    pulllistView.onRefreshComplete();
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    tv_load.setText("加载失败");
                }
            });
            // mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.


            super.onPostExecute(result);
        }
    }
}
