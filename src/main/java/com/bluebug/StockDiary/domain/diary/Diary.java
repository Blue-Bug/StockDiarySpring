package com.bluebug.StockDiary.domain.diary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Diary {

    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long id;
    @NotEmpty
    private List<DiaryContent> content;
    @NotEmpty
    private String created_at;
    private String updated_at;

    public Diary() {
    }

    public Diary(List<DiaryContent> content){
        this.content = content;
    }

    public Diary(Long userId, Long id, List<DiaryContent> content, String created_at, String updated_at) {
        this.userId = userId;
        this.id = id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
