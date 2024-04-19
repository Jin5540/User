package com.example.user.member.repository;

import com.example.user.member.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    Member findByUserId(String userId);
}

