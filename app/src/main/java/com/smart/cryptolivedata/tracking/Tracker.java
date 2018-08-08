package com.smart.cryptolivedata.tracking;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Tracker implements LifecycleObserver{
    private static String TAG = Tracker.class.getSimpleName();
    private Context mCon;

    public Tracker(Context mCon) {
        this.mCon = mCon;
        ((AppCompatActivity)mCon).getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void trackOncreate(){
        Log.d(TAG, "trackOncreate() called");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void trackOnStart(){
        Log.d(TAG, "trackOnStart() called");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void trackOnResume(){
        Log.d(TAG, "trackOnResume() called");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void trackOnPause(){
        Log.d(TAG, "trackOnPause() called");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void trackOnStop(){
        Log.d(TAG, "trackOnStop() called");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void trackOnDestroy(){
        Log.d(TAG, "trackOnDestroy() called");
        ((AppCompatActivity)mCon).getLifecycle().removeObserver(this);

        Lifecycle.State currentState = ((AppCompatActivity)mCon).getLifecycle().getCurrentState();
        Log.d(TAG, "trackOnDestroy() called" + currentState);
    }
}
