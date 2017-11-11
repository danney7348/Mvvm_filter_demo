package com.example.test1108;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.test1108.base.MyBaseActivity;
import com.example.test1108.vm.ProductsVm;
import com.jakewharton.rxbinding2.view.RxView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends MyBaseActivity implements XRecyclerView.LoadingListener {

    ProductsVm productsVm;
    private int page;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();

        RxView.clicks(binging.btnClick).throttleFirst(3,TimeUnit.SECONDS).throttleFirst(3, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(" =============== ");
                        productsVm = new ProductsVm(MainActivity.this,binging,1,1);
                    }
                });
    }

    private void initView() {
        XRecyclerView lv = binging.lv;
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.setLoadingListener(this);
        lv.setLoadingMoreEnabled(true);

    }


    @Override
    public void onRefresh() {
        Toast.makeText(this, "下拉刷新", Toast.LENGTH_SHORT).show();
        productsVm.refresh(0);
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(this, "上拉加载", Toast.LENGTH_SHORT).show();
        page++;
        productsVm.loadmore(page);
    }
}
