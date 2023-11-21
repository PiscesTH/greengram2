package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedInsPrcoDto {
    private int ifeed;
    private int iuser;
    private String contents;
    private String location;
}
