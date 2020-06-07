package com.dendeberia.project.database.controller;

import com.dendeberia.project.database.entity.News;
import com.dendeberia.project.database.service.NewsService;
import com.dendeberia.project.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@Controller
public class NewsPageController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String getMainView(Model model, Principal principal){
        if(userService.isAdmin(principal.getName())){
            model.addAttribute("isAdmin", true);
        }

        model.addAttribute("news", newsService.getNews());
        return "html/main";
    }

    @PostMapping("/addNew")
    public String addNew(Model model, @ModelAttribute("title") String title, @ModelAttribute("content") String content, @ModelAttribute("imagePath") String imgPath){
        String fullImgPath = "img\\cars\\"+imgPath;
        newsService.save(new News(fullImgPath, new Date(), title, content));
        return "html/addNew";
    }

    @GetMapping("/addNew")
    public String getAddNewView(){
        return "html/addNew";
    }

    @GetMapping("/newsEditor")
    public String getNewsEditorView(@RequestParam("id") Long id, Model model){
        model.addAttribute("post", newsService.getOne(id));
        return "html/newsEditor";
    }

    @PostMapping("/editNew")
    public String editNew(News news, @ModelAttribute("imagePath") String imagePath, Model model, Principal principal){
        String fullImagePath = "img\\newsImage\\"+imagePath;
        news.setImgPath(fullImagePath);
        newsService.save(news);
        model.addAttribute("news", newsService.getNews());
        model.addAttribute("isAdmin", userService.isAdmin(principal.getName()));
        return "html/main/main";
    }

    @GetMapping("/deleteNew")
    public String deleteNew(Model model, @RequestParam("id") Long id, Principal principal){
        newsService.delete(id);
        model.addAttribute("news", newsService.getNews());
        model.addAttribute("isAdmin", userService.isAdmin(principal.getName()));
        return "html/main";
    }
}
