package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedDelDto {
    private int ifeedComment;
    private int loginedIuser;
}
