package com.green.greengram2.user;

import com.green.greengram2.ResVo;
import com.green.greengram2.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public ResVo postSignup(UserSignupDto dto) {
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        UserSignupProcDto tmpDto = UserSignupProcDto.builder()
                .uid(dto.getUid())
                .upw(hashedPw)
                .nm(dto.getNm())
                .pic(dto.getPic())
                .build();
        int result = mapper.signupUser(tmpDto);
        if (result == 0) {
            return new ResVo(result);
        }
        return new ResVo(tmpDto.getIuser());
    }

    public UserSigninVo postSignin(UserSigninDto dto) {  //1 : 성공, 2: 아이디없음 3: 비밀번호 틀림
        String savedPw = mapper.selUpwByUid(dto.getUid());
        UserSigninVo result = new UserSigninVo();
        result.setResult(3);
        if (savedPw == null) {
            result.setResult(2);
            return result;
        }
        boolean comparedPw = BCrypt.checkpw(dto.getUpw(), savedPw);
        if (comparedPw) {
            result = mapper.selSigninedUserInfoByUid(dto.getUid());
            result.setResult(1);
        }
        return result;
    }

    public UserSigninVo postSigninVer2(UserSigninDto dto) {
        UserSigninProcDto tmpDto = mapper.selUserInfoByUid(dto.getUid());
        UserSigninVo result = new UserSigninVo();
        result.setResult(3);
        if (tmpDto == null) {
            result.setResult(2);
            return result;
        }
        String savedUpw = tmpDto.getUpw();
        boolean comparedPw = BCrypt.checkpw(dto.getUpw(), savedUpw);
        if (comparedPw) {
            result.setNm(tmpDto.getNm());
            result.setPic(tmpDto.getPic());
            result.setIuser(tmpDto.getIuser());
            result.setResult(1);
        }
        return result;
    }
    public UserInfoVo getProfileInfo(int targetIuser){
        return mapper.selUserProfile(targetIuser);
    }
}
