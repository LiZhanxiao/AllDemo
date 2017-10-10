package com.listudio.alldemo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by kehui on 2016/5/5.
 */
public class ActivityStack {
    String TAG=getClass().getSimpleName();

    private ArrayList<WeakReference<Activity>> mActivities = new ArrayList<>();

    public Activity topActivity(){
        while (mActivities.size() > 0){
            int index  = mActivities.size() - 1;
            Activity activity = mActivities.get(index).get();
            if (activity == null || !isActivityActive(activity)){
                mActivities.remove(index);
            } else {
                return activity;
            }
        }
        return null;
    }

    public void onActivityToFront(Activity activity){
        removeExistActivity(activity);
        mActivities.add(new WeakReference<Activity>(activity));
    }
    public void onActitivyCreate(Activity activity){
        removeExistActivity(activity);
        Log.v(TAG, String.format("count: %d ", mActivities.size()));
        for (WeakReference<Activity> wra: mActivities){
            Activity ac = wra.get();
            if (ac != null){
                Log.v(TAG, ac.getClass().getSimpleName());
            }
        }
        mActivities.add(new WeakReference<>(activity));

    }

    public void onActivityDestory(Activity activity){
//        removeExistActivity(activity);
    }

    private void removeExistActivity(Activity activity){
        for (int i = 0; i < mActivities.size();){
            WeakReference<Activity> wa = mActivities.get(i);
            if (wa.get() == null || wa.get() == activity){
                mActivities.remove(i);
            } else {
                i++;
            }
        }
    }

    @SuppressLint("NewApi")
    private boolean isActivityActive(Activity activity){
        if (activity.isFinishing()){
            return  false;
        }
        try {
            if (activity.isDestroyed()){
                return false;
            }
        }
        catch (Throwable t){
            //只是防止api调用失败
            t.printStackTrace();
        }
        return true;
    }
    public void finishAllActivities(){
        for (WeakReference<Activity> activity : mActivities) {
            try {
                Activity strongActivity = activity.get();
                if (strongActivity != null && isActivityActive(strongActivity)){
                    strongActivity.finish();
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        mActivities.clear();
    }
}
