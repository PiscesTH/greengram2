package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedInsPrcoPic {
    private int ifeed;
    private List<String> pics;
}
