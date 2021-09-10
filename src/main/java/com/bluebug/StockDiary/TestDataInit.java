package com.bluebug.StockDiary;

import com.bluebug.StockDiary.domain.diary.Diary;
import com.bluebug.StockDiary.domain.diary.DiaryContent;
import com.bluebug.StockDiary.domain.diary.DiaryService;
import com.bluebug.StockDiary.domain.diary.repository.DiaryRepository;
import com.bluebug.StockDiary.domain.member.Member;
import com.bluebug.StockDiary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        List<DiaryContent> dc = new ArrayList<>();
        dc.add(new DiaryContent("TestA", "A", "BUY", 1L, 1L));
        dc.add(new DiaryContent("TestB", "B", "SELL", 1L, 1L));
        String date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());
        diaryRepository.save(1L,new Diary(1L,1L,dc,date,date));
        diaryRepository.save(1L,new Diary(1L,1L,dc,date,date));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("123");
        member.setName("테스터");

        memberRepository.save(member);
    }

}