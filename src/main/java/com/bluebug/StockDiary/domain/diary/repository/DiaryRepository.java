package com.bluebug.StockDiary.domain.diary.repository;

import com.bluebug.StockDiary.domain.diary.Diary;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository {
    Diary save(Long userId, Diary diary);
    Optional<Diary> findOne(Long userId,Long diaryId);
    List<Diary> findAll(Long userId);
    void update(Long userId, Diary diary);
    void delete(Long userId, Long diaryId);
}
