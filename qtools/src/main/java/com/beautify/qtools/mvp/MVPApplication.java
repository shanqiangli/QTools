package com.beautify.qtools.mvp;

import androidx.multidex.MultiDexApplication;

import com.beautify.qtools.mvp.base.BasePresenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public abstract class MVPApplication extends MultiDexApplication {

    protected List<BasePresenter> presenters = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        this.initPresent();
        for (BasePresenter presenter:this.presenters) {
            presenter.onCreate();
        }
    }

    @Override
    public void onTerminate() {
        this.releasePresent();
        super.onTerminate();
    }

    private void releasePresent(){
        Iterator<BasePresenter> iterator = this.presenters.iterator();
        while (iterator.hasNext()){
            BasePresenter presenter = iterator.next();
            presenter.onDestroy();
            presenter.detachView();
            iterator.remove();
        }
    }

    protected abstract void initPresent();
}
