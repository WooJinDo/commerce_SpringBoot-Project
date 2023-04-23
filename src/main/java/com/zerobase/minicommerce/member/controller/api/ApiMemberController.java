package com.zerobase.minicommerce.member.controller.api;

import com.zerobase.minicommerce.member.dto.MemberDto;
import com.zerobase.minicommerce.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiMemberController {

  private final MemberService memberService;

  /**
   *
   * @param request 회원 정보
   * @return ResponseEntity<?> 201 Created, 가입된 회원의 정보
   */
  @PostMapping("/api/members")
  public ResponseEntity<?> memberJoin(
            @RequestBody @Valid MemberDto.RegisterRequest request) {
      MemberDto.RegisterResponse memberRegisterResponse = memberService.register(request);

      return ResponseEntity.status(HttpStatus.CREATED).body(memberRegisterResponse);
  }



}
