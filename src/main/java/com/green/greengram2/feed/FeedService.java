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
    private final FeedFavMapper favMapper;
    private final FeedCommentMapper commentMapper;

    public ResVo postFeed(FeedInsDto dto) {
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

    //N + 1 허용 방법
    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
        List<FeedSelVo> list = mapper.selFeedAll(dto);
        for (FeedSelVo vo : list) {
            vo.setPics(picsMapper.selFeedPicsAll(vo.getIfeed()));

            List<FeedCommentSelVo> comments = commentMapper.selCommentAll(FeedCommentSelDto.builder()
                    .ifeed(vo.getIfeed())
                    .startIdx(0)
                    .rowCount(4)
                    .build());

            if (comments.size() == 4) {
                vo.setIsMoreComment(1);
                comments.remove(comments.size() - 1);
            }
            vo.setComments(comments);
        }
        return list;
    }

    public ResVo toggleFav(FeedFavDto dto) {
        int delResult = favMapper.delFav(dto);
        if (delResult == 0) {
            int insResult = favMapper.insFav(dto);
            return new ResVo(insResult);
        }
        return new ResVo(0);
    }

    public ResVo postComment(FeedCommentInsDto dto) {
        FeedCommentInsProcDto pDto = FeedCommentInsProcDto.builder()
                .comment(dto.getComment())
                .ifeed(dto.getIfeed())
                .iuser(dto.getIuser())
                .build();
        int result = commentMapper.insComment(pDto);
        if (result == 1) {
            return new ResVo(pDto.getIfeedComment());
        }
        return new ResVo(result);
    }

    public List<FeedCommentSelVo> getCommentAll(int ifeed) {
        return commentMapper.selCommentAll(FeedCommentSelDto.builder()
                .ifeed(ifeed)
                .startIdx(3)
                .rowCount(9999)
                .build());
    }

    public ResVo delComment(FeedCommentDelDto dto) {
        int result = commentMapper.delComment(dto);
        if (result == 1) {
            return new ResVo(dto.getIfeedComment());
        }
        return new ResVo(result);
    }

    public ResVo delFeed(FeedDelDto dto){
        int selResult = mapper.selFeedToDel(dto);
        if(selResult == 0){
            return new ResVo(0);
        }
        int picsResult = picsMapper.delPicsByIfeed(dto.getIfeed());
        int favResult = favMapper.delFavByIfeed(dto.getIfeed());
        int commentResult = commentMapper.delCommentByIfeed(dto.getIfeed());
        int feedResult = mapper.delFeed(dto);
        return new ResVo(feedResult);
    }

    public ResVo delOneFeed(FeedDelDto dto){
        int selResult = mapper.selFeedToDel(dto);
        if(selResult == 0){
            return new ResVo(0);
        }
        int delProcResult = mapper.delFeedPre(dto);
        int delFeedResult = mapper.delFeed(dto);
        return new ResVo(delFeedResult);
    }
}
