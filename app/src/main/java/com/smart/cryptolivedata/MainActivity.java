package com.smart.cryptolivedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.smart.cryptolivedata.fragments.UILessFragment;
import com.smart.cryptolivedata.recview.CoinModel;
import com.smart.cryptolivedata.recview.Divider;
import com.smart.cryptolivedata.recview.MyCryptoAdapter;
import com.smart.cryptolivedata.screens.MainScreen;
import com.smart.cryptolivedata.viewmodel.CryptoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainScreen{

    private final static int DATA_FETCHING_INTERVAL=10*1000; //10 seconds
    private static final String TAG = MainActivity.class.getSimpleName();
    private final Observer<List<CoinModel>> dataObserver = coinModels -> updateData(coinModels);
    private final Observer<String> errorObserver = errorMsg -> setError(errorMsg);
    private RecyclerView recView;
    private MyCryptoAdapter mAdapter;
    private CryptoViewModel mViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private long mLastFetchedDataTimeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        mViewModel = ViewModelProviders.of(this).get(CryptoViewModel.class);
        mViewModel.setAppContext(getApplicationContext());
        mViewModel.getCoinsMarketData().observe(this,dataObserver);
        mViewModel.getErrorUpdates().observe(this,errorObserver);

        getSupportFragmentManager().beginTransaction().add(new UILessFragment(),"UILessFragment")
                .commit();
    }

    private void bindViews() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        mSwipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        recView = this.findViewById(R.id.recView);
        mSwipeRefreshLayout.setOnRefreshListener(()->{
            if(System.currentTimeMillis() - mLastFetchedDataTimeStamp < DATA_FETCHING_INTERVAL){
                Log.d(TAG, "\tNot fetching from network because interval didn't reach");
                mSwipeRefreshLayout.setRefreshing(false);
                return;
            }
            mViewModel.fetchData();
        });
        mAdapter = new MyCryptoAdapter();
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recView.setLayoutManager(lm);
        recView.setAdapter(mAdapter);
        recView.addItemDecoration(new Divider(this));
        setSupportActionBar(toolbar);
    }


    @Override
    public void updateData(List<CoinModel> data) {
        mLastFetchedDataTimeStamp = System.currentTimeMillis();
        this.mAdapter.setItems(data);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setError(String msg) {
        showErrorToast(msg);
    }

    private void showErrorToast(String error) {
        Toast.makeText(this, "Error:" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "BEFORE super.onDestroy() called");
        super.onDestroy();
        Log.d(TAG, "AFTER super.onDestroy() called");
    }
}
