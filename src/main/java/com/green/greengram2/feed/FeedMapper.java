package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedInsPrcoDto;
import com.green.greengram2.feed.model.FeedInsPrcoPic;
import com.green.greengram2.feed.model.FeedSelDto;
import com.green.greengram2.feed.model.FeedSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsPrcoDto dto);
    int insFeedPic(FeedInsPrcoPic dto);
    List<FeedSelVo> selFeedAll(FeedSelDto dto);
}
