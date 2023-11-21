package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;

    public ResVo postFeed(FeedInsDto dto){
        FeedInsPrcoDto dto1 = FeedInsPrcoDto.builder()
                .iuser(dto.getIuser())
                .contents(dto.getContents())
                .location(dto.getLocation())
                .build();
        int result1 = mapper.insFeed(dto1);
        FeedInsPrcoPic dto2 = FeedInsPrcoPic.builder()
                .ifeed(dto1.getIfeed())
                .pics(dto.getPics())
                .build();
        int result2 = mapper.insFeedPic(dto2);
        return new ResVo(dto1.getIfeed());
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto){
        List<FeedSelVo> list = mapper.selFeedAll(dto);
        for (FeedSelVo vo : list) {
            vo.setPics(picsMapper.selFeedPicsAll(vo.getIfeed()));
        }
        return list;
    }
}
