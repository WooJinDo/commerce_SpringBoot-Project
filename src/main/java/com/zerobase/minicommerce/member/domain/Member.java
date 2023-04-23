package com.zerobase.minicommerce.member.domain;

import com.zerobase.minicommerce.member.type.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
* 회원 Entity
* */
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    /* 사용자아이디 */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 이름 */
    private String userName;

    /* 비밀번호 */
    private String password;

    /* 이메일 */
    private String email;

    /* 휴대폰 번호 */
    private String userPhone;

    /* 우편번호 */
    private String zipcode;

    /* 주소 */
    private String address;

    /* 상세주소 */
    private String addressDetail;

    /* 역할 (사용자, 관리자) */
    @Enumerated(EnumType.STRING)
    private Role role;

    /* 등록일자 */
    private LocalDateTime createdAt;

    /* 수정일자 */
    private LocalDateTime updatedAt; //
}
