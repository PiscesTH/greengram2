package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedInsPrcoDto;
import com.green.greengram2.feed.model.FeedInsPrcoPic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsPrcoDto dto);
    int insFeedPic(FeedInsPrcoPic dto);
}
