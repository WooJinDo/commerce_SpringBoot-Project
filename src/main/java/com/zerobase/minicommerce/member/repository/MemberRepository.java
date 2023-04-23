package com.zerobase.minicommerce.member.repository;

import com.zerobase.minicommerce.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

}
