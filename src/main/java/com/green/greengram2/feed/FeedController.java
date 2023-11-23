package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
@Tag(name = "피드", description = "피드 관련 처리")
public class FeedController {
    private final FeedService service;

    @Operation(summary = "게시물 등록")
    @Parameters(value = {
            @Parameter(name = "iuser", description = "로그인 한 유저"),
            @Parameter(name = "contents", description = "피드 내용"),
            @Parameter(name = "location", description = "위치"),
            @Parameter(name = "pics", description = "사진들")
    })
    @PostMapping
    public ResVo postFeed(@RequestBody FeedInsDto dto) {
        return service.postFeed(dto);
    }

    @Operation(summary = "피드 불러오기", description = "페이지당 30개의 피드")
    @GetMapping
    public List<FeedSelVo> getFeedAll(int page, int loginedIuser,
                                      @RequestParam(defaultValue = "0", required = false) int targetIuser) {
        //@RequestParam 쿼리스트링으로 요청 받을 때 파라미터 앞에 자동으로 붙음. defaultValue 없이 값 안 보내면 에러
        final int ROW_COUNT = 30;
        return service.getFeedAll(FeedSelDto.builder()
                .loginedIuser(loginedIuser)
                .targetIuser(targetIuser)
                .startIdx((page - 1) * ROW_COUNT)
                .rowCount(ROW_COUNT)
                .build());
    }

    @Operation(summary = "좋아요 처리", description = "좋아요 : 1, 취소 : 2")
    @Parameters(value = {
            @Parameter(name = "ifeed", description = "좋아요 누를 피드의 ifeed"),
            @Parameter(name = "iuser", description = "로그인한 유저의 iuser")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 처리: result(1), 좋아요 취소: result(0)")
    })
    @GetMapping("/fav")     // insert : 1, delete : 0
    public ResVo toggleFav(FeedFavDto dto) { //get방식은 @RequestBody 안 씀. @PathVariable 쓰는 편
        return service.toggleFav(dto);
    }

    @Operation(summary = "댓글 등록", description = "댓글 작성 성공 : 1, 실패 : 0")
    @Parameters(value = {
            @Parameter(name = "iuser", description = "로그인 한 유저"),
            @Parameter(name = "ifeed", description = "댓글을 달 피드"),
            @Parameter(name = "comment", description = "댓글 내용")
    })
    @PostMapping("/comment")
    private ResVo postComment(@RequestBody FeedCommentInsDto dto) {
        return service.postComment(dto);
    }

    @GetMapping("/comment")
    public List<FeedCommentSelVo> getCommentAll(int ifeed) {     //댓글 더보기 눌렀을 때 나오는 댓글
        return service.getCommentAll(ifeed);
    }

    @DeleteMapping("/comment")
    public ResVo delComment(@RequestParam("ifeed_comment") int ifeedComment,
                            @RequestParam("logined_iuser") int loginedIuser) {
        return service.delComment(FeedCommentDelDto.builder()
                .ifeedComment(ifeedComment)
                .loginedIuser(loginedIuser)
                .build());
    }

    @DeleteMapping()
    public ResVo delFeed(FeedDelDto dto) {
        return service.delFeed(dto);
    }
}
