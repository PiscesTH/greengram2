package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFavMapper {
    int delFav(FeedFavDto dto);
    int insFav(FeedFavDto dto);
    int delFavByIfeed(int ifeed);
}
