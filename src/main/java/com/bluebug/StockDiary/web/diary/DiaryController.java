package com.bluebug.StockDiary.web.diary;

import com.bluebug.StockDiary.domain.diary.Diary;
import com.bluebug.StockDiary.domain.diary.DiaryContent;
import com.bluebug.StockDiary.domain.diary.DiaryService;
import com.bluebug.StockDiary.domain.member.Member;
import com.bluebug.StockDiary.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping
    public String diaries(Model model, HttpServletRequest request){
        Long userId = getLoginId(request);

        model.addAttribute("diaries", diaryService.findDiaries(userId));

        return "diaries/diaries";
    }

    @GetMapping("/{diaryId}")
    public String diary(@PathVariable Long diaryId, Model model, HttpServletRequest request){
        Long userId = getLoginId(request);

        Optional<Diary> diary = diaryService.findDiary(userId, diaryId);

        model.addAttribute("diary",diary.get());

        return "diaries/diary";
    }


    @GetMapping("/create")
    public String addForm(Model model) {
        Diary diary = new Diary();

        List<DiaryContent> content= new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            content.add(new DiaryContent());
        }

        diary.setContent(content);
        model.addAttribute("diary", diary);
        return "diaries/addForm";
    }

    @PostMapping("/create")
    public String addDiary(@ModelAttribute("diary") Diary form, HttpServletRequest request,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "diaries/addForm";
        }
        Long userId = getLoginId(request);


        Diary diary = new Diary();
        diary.setUserId(userId);
        diary.setContent(form.getContent());
        Diary savedDiary = diaryService.saveDiary(userId, diary);

        redirectAttributes.addAttribute("diaryId",savedDiary.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/diaries/{diaryId}";
    }


    @GetMapping("/{diaryId}/edit")
    public String editForm(@PathVariable Long diaryId, Model model,HttpServletRequest request) {
        Long userId = getLoginId(request);

        Optional<Diary> diary = diaryService.findDiary(userId, diaryId);
        model.addAttribute("diary", diary.get());
        return "diaries/editForm";
    }

    @PostMapping("/{diaryId}/edit")
    public String edit(@PathVariable Long diaryId,Model model, HttpServletRequest request,
                       @ModelAttribute("diary") Diary form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "diaries/editForm";
        }
        Long userId = getLoginId(request);

        Diary diary = new Diary();
        diary.setUserId(userId);
        diary.setId(diaryId);
        diary.setContent(form.getContent());

        diaryService.updateDiary(userId, diary);
        return "redirect:/diaries/{diaryId}";
    }

    @PostMapping("/{diaryId}/delete")
    public String delete(@PathVariable Long diaryId,Model model, HttpServletRequest request){
        Long userId = getLoginId(request);

        diaryService.deleteDiary(userId,diaryId);

        model.addAttribute("diaries", diaryService.findDiaries(userId));

        return "redirect:/diaries";
    }

    private Long getLoginId(HttpServletRequest request) {
        //로그인 체크 후 실행되므로 세션이 존재
        HttpSession session = request.getSession(false);
        //세션에서 로그인 된 멤버 가져옴
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return member.getId();
    }


}
