package com.green.greengram2.feed.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class FeedSelVo {
    private int ifeed;
    private String contents;
    private String location;
    private int writerIuser;
    private String writerNm;
    private String createdAt;
    private int isFav;
    private List<String> pics;
}
