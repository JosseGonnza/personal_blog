package com.jossegonnza.controller;

import com.jossegonnza.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/home") //EndPoint que nos retornará una vista con el método para ver todos los posts
    public String homePage(Model model){
        model.addAttribute("posts", postService.getAllPost());
        return "/posts/home";
    }
}
