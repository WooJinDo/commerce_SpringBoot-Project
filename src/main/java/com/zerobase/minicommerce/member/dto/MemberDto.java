package com.zerobase.minicommerce.member.dto;

import com.zerobase.minicommerce.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
* 회원 DTO
* */

public class MemberDto {

    @Getter @Setter
    @ToString
    public static class RegisterRequest{
        /* 이름 */
        @NotBlank(message = "이름을 입력해주세요.")
        private String userName;

        /* 비밀번호 */
        @Size(min = 6, max = 13, message = "비밀번호는 6자리 이상 13자리 이하로 입력해주세요.")
        private String password;

        /* 이메일 */
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        /* 휴대폰 번호 */
        @NotBlank(message = "핸드폰 번호를 입력해주세요.")
        private String userPhone;

        /* 우편번호 */
        @NotBlank(message = "우편번호 찾기를 통해 입력해주세요.")
        private String zipcode;

        /* 주소 */
        @NotBlank(message = "우편번호 찾기를 통해 입력해주세요.")
        private String address;

        /* 상세주소 */
        @NotBlank(message = "상세주소를 입력해주세요.")
        private String addressDetail;
    }

    @Getter @Setter
    @Builder
    public static class RegisterResponse{
        private boolean success;

        public static RegisterResponse fromEntity(boolean success) {
            return RegisterResponse.builder()
                    .success(success)
                    .build();
        }
    }


}
