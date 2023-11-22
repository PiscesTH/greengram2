package com.green.greengram2.feed.model;

import lombok.Data;

@Data
public class FeedCommentInsDto {
    private int iuser;
    private int ifeed;
    private String comment;
}
