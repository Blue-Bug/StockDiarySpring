package com.bluebug.StockDiary.service;

import com.bluebug.StockDiary.domain.member.MemberService;
import com.bluebug.StockDiary.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

//테스트 전에 트랜잭션 시작, 테스트 완료 후 롤백
@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입")
    void join(){
        //given
        Member member = new Member();
        member.setName("spring1211");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}
