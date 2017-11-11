package com.example.test1108.service;

import com.example.test1108.entity.BaseRespinseEntity;
import com.example.test1108.entity.Products;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者： 张少丹
 * 时间：  2017/11/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface ApiService {

    @GET("/product/getProducts")
    Observable<BaseRespinseEntity<List<Products>>> getProducts(@Query("pscid") int pscid);
}
