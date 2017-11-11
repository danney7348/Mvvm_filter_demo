package com.example.test1108.model;

import com.example.test1108.common.Api;
import com.example.test1108.entity.BaseRespinseEntity;
import com.example.test1108.entity.Products;
import com.example.test1108.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： 张少丹
 * 时间：  2017/11/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ProductsModel {
    public void getData(int pscid){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_API)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<BaseRespinseEntity<List<Products>>> products = apiService.getProducts(pscid);
        products.filter(new Predicate<BaseRespinseEntity<List<Products>>>() {
            @Override
            public boolean test(BaseRespinseEntity<List<Products>> listBaseRespinseEntity) throws Exception {

                 return listBaseRespinseEntity.code.equals("0");

            }
        })/*flatMap(new Function<BaseRespinseEntity<List<Products>>, ObservableSource< List<Products>>>() {
            @Override
            public ObservableSource< List<Products>> apply(BaseRespinseEntity<List<Products>> listBaseRespinseEntity) throws Exception {
                return null;
            }
        }).*/.map(new Function<BaseRespinseEntity<List<Products>>, List<Products>>() {
            @Override
            public List<Products> apply(BaseRespinseEntity<List<Products>> listBaseRespinseEntity) throws Exception {
                List<Products> list = new ArrayList<>();
                list = listBaseRespinseEntity.data;
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Products>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Products> value) {
                        getProductsData.getSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                        getProductsData.faulire(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    private GetProductsData getProductsData;

    public void setGetProductsData(GetProductsData getProductsData) {
        this.getProductsData = getProductsData;
    }

    public interface GetProductsData{
        void getSuccess(List<Products> value);
        void faulire(Throwable e);
    }
}
