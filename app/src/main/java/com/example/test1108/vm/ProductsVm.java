package com.example.test1108.vm;

import android.content.Context;
import com.example.test1108.R;
import com.example.test1108.adapter.MyAdapter;
import com.example.test1108.databinding.ActivityMainBinding;
import com.example.test1108.entity.BaseRespinseEntity;
import com.example.test1108.entity.Products;
import com.example.test1108.model.ProductsModel;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/11/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ProductsVm implements ProductsModel.GetProductsData {
    private ProductsModel productsModel;
    private Context context;
    private ActivityMainBinding binding;
    private int page;
    private int pscid;
    private MyAdapter adapter;

    public ProductsVm(Context context, ActivityMainBinding binding, int page,int pscid) {
        this.pscid = pscid;
        this.context = context;
        this.binding = binding;
        this.page = page;
        productsModel = new ProductsModel();
        productsModel.getData(pscid);
        productsModel.setGetProductsData(this);
    }

    public void refresh(int page) {
        this.page = page;
        productsModel.getData(pscid);

    }

    public void loadmore(int page) {
        this.page = page;
        productsModel.getData(pscid);

    }
    @Override
    public void getSuccess(List<Products> value) {

        System.out.println("value = " + value.size());
        if(adapter == null){
            adapter = new MyAdapter(context,value,R.layout.lv_item, com.android.databinding.library.baseAdapters.BR.p);
            binding.lv.setAdapter(adapter);
        }else {
            if (page==0){
                adapter.refresh(value);
                binding.lv.refreshComplete();
            }else {
                adapter.loadmore(value);
                binding.lv.loadMoreComplete();
            }
        }
    }

    @Override
    public void faulire(Throwable e) {

    }
}
