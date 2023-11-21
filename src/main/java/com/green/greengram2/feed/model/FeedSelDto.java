package com.green.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedSelDto {
    private int loginedIuser;
    private int targetIuser;
    private int startIdx;
    private int rowCount;
}
