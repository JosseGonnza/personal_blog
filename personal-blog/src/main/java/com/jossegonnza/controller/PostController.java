package com.jossegonnza.controller;

import com.jossegonnza.entity.CommentEntity;
import com.jossegonnza.entity.PostEntity;
import com.jossegonnza.entity.UserEntity;
import com.jossegonnza.service.PostService;
import com.jossegonnza.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/home") //EndPoint que nos retornará una vista con el método para ver todos los posts
    public String homePage(Model model){
        model.addAttribute("posts", postService.getAllPost());
        return "/posts/home";
    }

    @GetMapping("/new")
    public String newPostPage() {
        return "/posts/create-post";
    }

    @PostMapping("/create")
    public String createPost(PostEntity post, HttpSession session) {
        post.setCreatedAt(LocalDateTime.now());

        UserEntity user = userService.getUserById(Long.parseLong(session.getAttribute("user_session_id").toString())).get();
        post.setUser(user);

        postService.createPost(post);
        return "redirect:/post/home";
    }

    @GetMapping("/postPage/{id}")
    public String postPage(@PathVariable Long id, Model model) {
        PostEntity post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("¡Id del posteo inválido!"));
        List<CommentEntity> comments = post.getCommments();

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "/posts/post-page";
    }

    @GetMapping("/mine")
    public String myPosts(Model model, HttpSession session) {
        Long userId = Long.parseLong(session.getAttribute("user_session_id").toString());
        List<PostEntity> posts = postService.getPostByUserId(userId);

        model.addAttribute("posts", posts);
        return "/posts/my-post";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        PostEntity post = postService.getPostById(id).orElseThrow(() -> new IllegalArgumentException("Id del post Inválido"));
        model.addAttribute("post", post);
        return "/posts/update-post";
    }

    @PostMapping("/update")
    public String updatePost(@RequestParam("idPost") Long id, PostEntity post) {
        postService.updatePost(id, post);
        return "redirect:/post/mine";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return "redirect:/post/mine";
    }

}
