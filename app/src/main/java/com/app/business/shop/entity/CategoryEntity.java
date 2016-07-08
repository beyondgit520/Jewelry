package com.app.business.shop.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 李 on 16-7-8.
 */
public class CategoryEntity {
    @SerializedName("title") @Expose public String title;
    @SerializedName("count") @Expose public Integer count;
}
