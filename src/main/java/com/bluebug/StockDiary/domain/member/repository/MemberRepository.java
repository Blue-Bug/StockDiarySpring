package com.bluebug.StockDiary.domain.member.repository;

import com.bluebug.StockDiary.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
}
