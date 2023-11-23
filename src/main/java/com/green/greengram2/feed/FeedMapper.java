package com.green.greengram2.feed;

import com.green.greengram2.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsPrcoDto dto);
    int insFeedPic(FeedInsPrcoPic dto);
    List<FeedSelVo> selFeedAll(FeedSelDto dto);
    int delFeed(FeedDelDto dto);
    int selFeedToDel(FeedDelDto dto);
    int delFeedPre(FeedDelDto dto);
}
