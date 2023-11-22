package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedCommentInsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedCommentMapper {
    int insComment(FeedCommentInsDto dto);
}
