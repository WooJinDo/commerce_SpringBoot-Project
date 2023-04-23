package com.zerobase.minicommerce.member.service.impl;

import com.zerobase.minicommerce.common.exception.customexception.MemberException;
import com.zerobase.minicommerce.common.exception.type.MemberErrorCode;
import com.zerobase.minicommerce.member.domain.Member;
import com.zerobase.minicommerce.member.dto.MemberDto;
import com.zerobase.minicommerce.member.repository.MemberRepository;
import com.zerobase.minicommerce.member.service.MemberService;
import com.zerobase.minicommerce.member.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public MemberDto.RegisterResponse register(MemberDto.RegisterRequest request) {
        Member memberCheck = memberRepository.findByEmail(request.getEmail());

        // 중복 검사
        if (memberCheck != null) {
            //error message 출력 (뷰 매칭해서 보여주는 컨트롤러에서 사용하는 서비스)
            throw new MemberException(MemberErrorCode.USER_EMAIL_EXIST);
        }

        // password 암호화
        String encPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());

        Member member = memberRepository.save(
                Member.builder()
                .userName(request.getUserName())
                .password(encPassword)
                .email(request.getEmail())
                .userPhone(request.getUserPhone())
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .addressDetail(request.getAddressDetail())
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build()
        );

        return MemberDto.RegisterResponse.fromEntity(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username - 이메일로 로그인하기 때문에 이메일 값이 넘어온다
        // security - UserAuthenticationFailureHandler 화면으로 이동 (로그인 실패)
        Member member = Optional.ofNullable(memberRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("회원정보가 존재하지 않습니다"));

        // 사용자 권한 추가
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // 관리자 권한 추가
        if (Role.ADMIN.equals(member.getRole())) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(member.getEmail(), member.getPassword(),grantedAuthorities);
    }
}
