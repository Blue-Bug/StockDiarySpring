package com.bluebug.StockDiary.domain.diary;

import com.bluebug.StockDiary.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public List<Diary> findDiaries(Long userId) {
        return diaryRepository.findAll(userId);
    }
    public Optional<Diary> findDiary(Long userId, Long diaryId){
        return diaryRepository.findOne(userId,diaryId);
    }
    public Diary saveDiary(Long userId, Diary diary){
        return diaryRepository.save(userId,diary);
    }
    public void updateDiary(Long userId, Diary diary){
        diaryRepository.update(userId,diary);
    }

    public void deleteDiary(Long userId, Long diaryId) {
        diaryRepository.delete(userId,diaryId);
    }
}
