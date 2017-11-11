package com.example.test1108.entity;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 作者： 张少丹
 * 时间：  2017/11/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class Products extends BaseRespinseEntity {
    public String title;
    public String images;
    public String itemtype;
    @BindingAdapter("icon")
    public static void icon(ImageView iv,String icon){
        String[] split = icon.split("\\|");
        Glide.with(iv.getContext()).load(split[0]).into(iv);
    }
}
