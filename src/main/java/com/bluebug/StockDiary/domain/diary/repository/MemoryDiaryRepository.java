package com.bluebug.StockDiary.domain.diary.repository;

import com.bluebug.StockDiary.domain.diary.Diary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemoryDiaryRepository implements DiaryRepository{
    private static final ConcurrentHashMap<Long, List<Diary>> store= new ConcurrentHashMap<>();
    private static Long sequence = 1L;
    @Override
    public Diary save(Long userId, Diary diary) {
        String date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());
        diary.setId(sequence++);
        diary.setCreated_at(date);
        diary.setUpdated_at(date);
        log.info("save: userId={},diary={}", userId,diary);
        if(store.containsKey(userId)){
            store.get(userId).add(diary);
        }
        else{
            ArrayList<Diary> diaries = new ArrayList<>();
            diaries.add(diary);
            store.put(userId,diaries);
        }
        return diary;
    }

    @Override
    public Optional<Diary> findOne(Long userId,Long diaryId) {
        return findAll(userId).stream()
                .filter(diary -> diary.getId().equals(diaryId))
                .findFirst();
    }

    @Override
    public List<Diary> findAll(Long userId) {
        if(store.get(userId) != null){
            return new ArrayList<>(store.get(userId));
        }
        return new ArrayList<>();
    }

    @Override
    public void update(Long userId, Diary diary) {

        Optional<Diary> findDiary = findOne(userId, diary.getId());
        Diary updateDiary = findDiary.get();

        String date = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss").format(new Date());
        updateDiary.setUpdated_at(date);
        updateDiary.setContent(diary.getContent());
    }

    @Override
    public void delete(Long userId, Long diaryId) {
        List<Diary> diaries = store.get(userId);
        for(int i = 0; i < diaries.size(); i++){
            if(diaries.get(i).getId().equals(diaryId)){
                diaries.remove(i);
                return;
            }
        }
    }

    public void clearStore() {
        store.clear();
    }
}
