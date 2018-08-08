package com.smart.cryptolivedata.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.smart.cryptolivedata.viewmodel.CryptoViewModel;

public class UILessFragment extends Fragment {
    private static final String TAG = UILessFragment.class.getSimpleName();
    private CryptoViewModel mViewModel;
    private Observer<Double> mObserver = totalMarketCap ->
            Log.d(TAG, "onChanged() called with: aDouble = [" +totalMarketCap + "]");

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(CryptoViewModel.class);
        mViewModel.getTotalMarketCap().observe(this,mObserver);
    }
}
