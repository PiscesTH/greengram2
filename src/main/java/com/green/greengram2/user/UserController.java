package com.green.greengram2.user;

import com.green.greengram2.ResVo;
import com.green.greengram2.user.model.UserSigninDto;
import com.green.greengram2.user.model.UserSigninVo;
import com.green.greengram2.user.model.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "유저 API", description = "인증 관련")  //swagger에서 컨트롤러 ui 변경
public class UserController {
    private final UserService service;

    @Operation(summary = "인증", description = "아이디/비밀번호를 활용한 인증처리")
    @Parameters(value = {
    //@Parameter 를 여러 개 받는 용도
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호"),
            //하나의 파라미터에 대한 설명
    })
    @PostMapping("signin")
    public UserSigninVo postSignin(@RequestBody UserSigninDto dto) {
        return service.postSignin(dto);
    }

    //여러 값 받을 때 사용
    @Operation(summary = "회원가입", description = "회원가입용 정보")
    @Parameters(value = {
            @Parameter(name = "uid", description = "아이디"),
            @Parameter(name = "upw", description = "비밀번호"),
            @Parameter(name = "nm", description = "이름"),
            @Parameter(name = "pic", description = "프로필 사진")
    })
    @PostMapping("/signup")
    public ResVo postSignup(@RequestBody UserSignupDto dto) {
        //System.out.println(dto); 사용안하기
        //log.info("dto: {}", dto);
        return service.postSignup(dto); // insert한 레코드 pk값 담아서 응답처리
    }
}
