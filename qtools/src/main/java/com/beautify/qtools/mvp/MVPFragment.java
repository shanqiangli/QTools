package com.beautify.qtools.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.beautify.qtools.mvp.base.BasePresenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class MVPFragment extends Fragment {
    protected View rootView;
    protected List<BasePresenter> presenters = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(this.layoutId(), container, false);
        this.initPresent();
        this.initViewDisplay();
        this.initEventAndListener();
        for (BasePresenter presenter:this.presenters) {
            presenter.onCreate();
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        this.releasePresent();
        super.onDestroyView();
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
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
