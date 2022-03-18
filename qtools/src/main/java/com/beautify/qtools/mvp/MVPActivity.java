package com.beautify.qtools.mvp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.beautify.qtools.mvp.base.BasePresenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MVPActivity extends AppCompatActivity {
    protected List<BasePresenter> presenters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.layoutId());
        this.initPresent();
        this.initViewDisplay();
        this.initEventAndListener();
        for (BasePresenter presenter:this.presenters) {
            presenter.onCreate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (BasePresenter presenter:this.presenters) {
            presenter.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        for (BasePresenter presenter:this.presenters) {
            presenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        this.releasePresent();
        super.onDestroy();
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
    /***
     * 初始化UI组件
     */
    protected abstract int layoutId();
    /***
     * 初始化业务处理器
     */
    protected abstract void initPresent();
    /***
     * 初始化页面显示(绑定数据)
     */
    protected abstract void initViewDisplay();
    /***
     * 绑定View事件
     */
    protected abstract void initEventAndListener();
}
