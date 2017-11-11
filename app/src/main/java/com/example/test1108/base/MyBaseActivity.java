package com.example.test1108.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.test1108.databinding.ActivityMainBinding;

/**
 * 作者： 张少丹
 * 时间：  2017/11/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public abstract class MyBaseActivity<VDB extends ActivityMainBinding> extends AppCompatActivity {
    public VDB binging;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binging = DataBindingUtil.setContentView(this,getLayoutId());
        init(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
