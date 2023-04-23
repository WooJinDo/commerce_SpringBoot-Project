package com.zerobase.minicommerce.member.service;

import com.zerobase.minicommerce.member.dto.MemberDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    /* 회원 가입*/
    MemberDto.RegisterResponse register(MemberDto.RegisterRequest request);
}
