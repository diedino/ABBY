package com.krasnov.abby;


import android.app.AlertDialog;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.krasnov.abby.adapters.RecycleViewAdapter;
import com.krasnov.abby.models.News;
import com.krasnov.abby.network.ConnectionDetector;
import com.krasnov.abby.viewmodels.MainActivityViewModel;

import java.util.List;

import io.reactivex.annotations.Nullable;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "MainActivity";

    private RecyclerView mRecycleView;
    private RecycleViewAdapter mAdapter;
    private MainActivityViewModel mMainActivityViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConnectionDetector cd = new ConnectionDetector(this);

        if (!cd.isConnected()) {
            buildeDialog(MainActivity.this).show();
        }


        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecycleView = findViewById(R.id.recyclev_view);

        initRecyclerView();
        Log.d(TAG, "RecycleView is inited");

        mRecycleView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        
        mMainActivityViewModel.init();

        mMainActivityViewModel.getNews().observe((LifecycleOwner) this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                mAdapter.setData(news);
            }
        });
        mMainActivityViewModel.getIsUpdating().observe((LifecycleOwner) this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                }
                else{
                    mRecycleView.smoothScrollToPosition(mMainActivityViewModel.getNews().getValue().size()-1);
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Отменяем анимацию обновления
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    private void initRecyclerView(){
        mAdapter = new RecycleViewAdapter(this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mAdapter);
    }

    public AlertDialog.Builder buildeDialog(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Check your network. Press ok to exit");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        return builder;
    }
}
