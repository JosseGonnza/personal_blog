package com.jossegonnza.controller;

import com.jossegonnza.entity.CommentEntity;
import com.jossegonnza.entity.PostEntity;
import com.jossegonnza.entity.UserEntity;
import com.jossegonnza.service.CommentService;
import com.jossegonnza.service.PostService;
import com.jossegonnza.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/addComment")
    public String addComment(@RequestParam("postId") Long postId, CommentEntity comment, HttpSession session) {

        UserEntity user = userService.getUserById(Long.parseLong(session.getAttribute("user_session_id").toString())).get();
        PostEntity post = postService.getPostById(postId).orElseThrow(() -> new IllegalArgumentException("Invalid post id"));

        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setPost(post);

        commentService.createComment(comment);
        return "redirect:/post/postPage/" + postId;
    }

    @GetMapping("/edit/{id}")
    public String editComment(@PathVariable Long id, Model model){
        CommentEntity comment = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid comment id"));
        model.addAttribute("comment", comment);
        return "/posts/update-comment";
    }

    @PostMapping("/update")
    public String updateComment(@RequestParam("commentId") Long id, CommentEntity comment) {
        CommentEntity commentDB = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid comment id"));
        commentService.udateComment(id, comment);
        return "redirect:/post/postPage/" + commentDB.getPost().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id){
        CommentEntity comment = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid comment id"));
        commentService.deleteComment(id);
        return "redirect:/post/postPage/" + comment.getPost().getId();
    }

    @GetMapping("/cancel/{id}")
    public String cancelEditComment(@PathVariable Long id){
        CommentEntity comment = commentService.getCommentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid comment id"));
        return "redirect:/post/postPage/" + comment.getPost().getId();
    }

}
