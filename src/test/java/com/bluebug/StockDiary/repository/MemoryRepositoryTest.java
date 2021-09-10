package com.bluebug.StockDiary.repository;

import com.bluebug.StockDiary.domain.diary.Diary;
import com.bluebug.StockDiary.domain.diary.DiaryContent;
import com.bluebug.StockDiary.domain.diary.repository.DiaryRepository;
import com.bluebug.StockDiary.domain.diary.repository.MemoryDiaryRepository;
import com.bluebug.StockDiary.domain.member.Member;
import com.bluebug.StockDiary.domain.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    MemoryDiaryRepository diaryRepository = new MemoryDiaryRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
        diaryRepository.clearStore();
    }

    @Test
    @DisplayName("회원 저장")
    void save(){
        Member member= new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    @DisplayName("이름으로 찾기")
    void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);
        result = repository.findByName("spring2").get();
        assertThat(member2).isEqualTo(result);
    }

    @Test
    @DisplayName("전부 찾기")
    void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("로그인 아이디로 찾기")
    void findByLoginId(){
        //given
        Member member1 = new Member();
        member1.setLoginId("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setLoginId("spring2");
        repository.save(member2);

        //when
        Optional<Member> findMember1 = repository.findByLoginId("spring1");
        Optional<Member> findMember2 = repository.findByLoginId("TEST");
        //then
        assertThat(findMember1.get()).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("일지 저장")
    void saveDiary(){
        //given
        List<DiaryContent> dc = new ArrayList<>();
        dc.add(new DiaryContent("Test1", "A", "Buy", 1L, 1L));
        dc.add(new DiaryContent("Test2", "B", "Sell", 1L, 1L));
        String date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());

        //when
        diaryRepository.save(1L,new Diary(1L,1L,dc,date,date));
        diaryRepository.save(1L,new Diary(1L,1L,dc,date,date));

        //then
        List<Diary> results = diaryRepository.findAll(1L);
        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).getContent()).isEqualTo(dc);
    }

    @Test
    @DisplayName("일지 삭제")
    void deleteDiary(){
        //given
        List<DiaryContent> dc = new ArrayList<>();
        dc.add(new DiaryContent("Test1", "A", "Buy", 1L, 1L));
        dc.add(new DiaryContent("Test2", "B", "Sell", 1L, 1L));
        String date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());

        //when
        diaryRepository.save(1L,new Diary(1L,1L,dc,date,date));
        diaryRepository.save(1L,new Diary(1L,1L,dc,date,date));

        diaryRepository.delete(1L,1L);

        //then
        List<Diary> results = diaryRepository.findAll(1L);
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("일지 조회")
    void findDiary(){
        //given
        List<DiaryContent> dc = new ArrayList<>();
        dc.add(new DiaryContent("Test1", "A", "Buy", 1L, 1L));
        dc.add(new DiaryContent("Test2", "B", "Sell", 1L, 1L));
        String date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());

        //when
        diaryRepository.save(1L,new Diary(1L,1L,dc,date,date));

        //then
        Optional<Diary> findDiary = diaryRepository.findOne(1L,1L);
        List<DiaryContent> content = findDiary.get().getContent();

        assertThat(content.size()).isEqualTo(2);
        assertThat(content).isEqualTo(dc);
    }

    @Test
    @DisplayName("일지 수정")
    void editDiary(){
        //given
        List<DiaryContent> dc1 = new ArrayList<>();
        dc1.add(new DiaryContent("Test1", "A", "Buy", 1L, 1L));

        String date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());
        diaryRepository.save(1L,new Diary(1L,1L,dc1,date,date));

        //when
        List<DiaryContent> dc2 = new ArrayList<>();
        dc2.add(new DiaryContent("Test2", "B", "Sell", 1L, 1L));
        date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());
        diaryRepository.update(1L,new Diary(1L,1L,dc2,date,date));

        //then
        Optional<Diary> findDiary = diaryRepository.findOne(1L,1L);
        List<DiaryContent> content = findDiary.get().getContent();

        assertThat(content).isEqualTo(dc2);
    }
}
