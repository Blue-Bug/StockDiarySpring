package com.bluebug.StockDiary.domain.login;

import com.bluebug.StockDiary.domain.member.Member;
import com.bluebug.StockDiary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 은 로그인 실패
     */
    public Member login(String loginId,String password){
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
