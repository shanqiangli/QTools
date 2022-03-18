package com.beautify.qtools.mvp.base;

import android.content.Context;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseViewer> {
    protected Context context;
    protected WeakReference<V> attachedView;

    public BasePresenter(@NonNull Context context, V listener) {
        attachView(listener);
        this.context = context;
    }

    private void attachView(@NonNull V view) {
        attachedView = new WeakReference<>(view);
    }

    public void detachView() {
        if (isAttached()) {
            attachedView.clear();
        }
    }

    public boolean isAttached() {
        return attachedView != null && attachedView.get() != null;
    }

    public void onCreate(){

    }

    public void onResume(){

    }

    public void onPause(){

    }

    public void onDestroy(){

    }
}
