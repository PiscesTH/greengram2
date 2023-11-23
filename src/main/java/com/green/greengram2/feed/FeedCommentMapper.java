package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedCommentInsDto;
import com.green.greengram2.feed.model.FeedCommentSelDto;
import com.green.greengram2.feed.model.FeedCommentSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insComment(FeedCommentInsDto dto);
    List<FeedCommentSelVo> selCommentAll(FeedCommentSelDto dto);
}
