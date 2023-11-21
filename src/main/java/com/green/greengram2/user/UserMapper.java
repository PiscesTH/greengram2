package com.green.greengram2.user;

import com.green.greengram2.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int signupUser(UserSignupProcDto dto);
    String selUpwByUid(String uid);
    UserSigninVo selSigninedUserInfoByUid(String uid);
    UserSigninProcDto selUserInfoByUid(String uid);
}
