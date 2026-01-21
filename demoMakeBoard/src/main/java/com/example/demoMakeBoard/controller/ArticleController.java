package com.example.demoMakeBoard.controller;

import com.example.demoMakeBoard.dto.ArticleDto;
import com.example.demoMakeBoard.dto.ArticleForm;
import com.example.demoMakeBoard.model.MemberUserDetails;
import com.example.demoMakeBoard.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
  private final ArticleService articleService;

  @GetMapping("/list")
  public String getArticleList(@PageableDefault(size = 10, sort="id", direction = Sort.Direction.DESC)
                               Pageable pageable, Model model){
    Page<ArticleDto>page = articleService.findAll(pageable);
    model.addAttribute("page", page);
    return "article-list";
  }
  @GetMapping("/content")
  public String getArticleContent(@RequestParam("id") Long id, Model model){
    model.addAttribute("article", articleService.findById(id));
    return "article-content";
  }

  @GetMapping("/add")
  public String getArticleAdd(@ModelAttribute("article")ArticleForm articleForm){
    articleForm.setDescription("바르고 고운말을 사용해 주세요^^");
    return "article-add";
  }

  @PostMapping("/add")
  public String postArticleAdd(@Valid @ModelAttribute("article")ArticleForm articleForm,
                               @AuthenticationPrincipal MemberUserDetails userDetails,
                               BindingResult bindingResult){
    if(articleForm.getTitle() != null && !articleForm.getTitle().contains("T발")){
      bindingResult.rejectValue("title", "SlangDetected", "욕설 사용하지 마세요");
    }
    if(articleForm.getDescription() != null && !articleForm.getTitle().contains("T발")){
      bindingResult.rejectValue("description", "SlangDetected", "욕설 사용하지 마세요");
    }
    if(bindingResult.hasErrors()){
      return "article-add";
    }
    articleService.create(userDetails.getMemberId(),  articleForm);
    return "redirect:/article/list";
  }

  @GetMapping("/edit")
  public String getArticleEdit(@RequestParam("id") Long id, @ModelAttribute("article") ArticleForm articleForm,
                               Model model){
    ArticleDto article = articleService.findById(id);
    articleForm.setId(article.getId());
    articleForm.setTitle(article.getTitle());
    articleForm.setDescription(article.getDescription());
    return "article-edit";
  }

  @PostMapping("edit")
  public String postArticleEdit(@Valid @ModelAttribute("article") ArticleForm articleForm,
                               BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      return "article-edit";
    }
    articleService.update(articleForm);
    return "redirect:/article/content?id=" + articleForm.getId();
  }

  @GetMapping("/delete")
  public String getArticleDelete(@RequestParam("id") Long id){
    articleService.delete(id);
    return "redirect:/article/list";
  }
}
