package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import io.swagger.v3.oas.annotations.*;
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
    @GetMapping("/fav")     // insert : 1, delete : 2
    public ResVo toggleFav(FeedFavDto dto){ //get방식은 @RequestBody 안 씀. @PathVariable 쓰는 편
        return service.toggleFav(dto);
    }
}
