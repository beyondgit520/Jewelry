package com.app.business.discover.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 李 on 16-7-5.
 */
public class DiscoverEntity {
    @SerializedName("image") @Expose public String image;
    @SerializedName("title") @Expose public String title;
    @SerializedName("content") @Expose public String content;
    @SerializedName("praise") @Expose public Integer praise;
    @SerializedName("comment") @Expose public Integer comment;
}
