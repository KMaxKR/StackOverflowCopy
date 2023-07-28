package ks.msx.SpringSecurity.controller;


import jakarta.servlet.http.HttpServletResponse;
import ks.msx.SpringSecurity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @RequestMapping(value = "app/posts", method = RequestMethod.GET)
    public String getPosts(Model model) {
        model.addAttribute("post", postService.getAllPosts());
        return "main";
    }

    @RequestMapping(value = "app/posts/create", method = RequestMethod.GET)
    public String createPostPage(){
        return "createPostPage";
    }

    @RequestMapping(value = "app/posts/add", method = RequestMethod.POST)
    public String createPost(@RequestParam String postTitle, String postText, Principal principal){
        postService.createPost(postTitle, postText, principal.getName());
        return "donePageCreating";
    }

    @RequestMapping(value = "app/posts/post/delete")
    public void deletePost(@RequestParam(value = "buttonTest") String idPost, Principal principal, HttpServletResponse response) throws IOException {
        postService.deletePost(Long.valueOf(idPost) , principal.getName());
        response.sendRedirect("/app/posts");
    }

    @RequestMapping("app/posts/post/update")
    public void updatePost(@RequestParam(value = "buttonUpdatePost") String idPost, Principal principal, String postTitleUpdated, String postTextUpdated, HttpServletResponse response) throws IOException {
        postService.updatePost(Long.valueOf(idPost), principal.getName(), postTitleUpdated, postTextUpdated);
        response.sendRedirect("/app/posts");
    }

    @RequestMapping("app/posts/post/ban/{id}")
    @Secured("hasRole('ROLE_ADMIN')")
    public void setPostLocked(@PathVariable(value = "id") int id, HttpServletResponse response) throws IOException {
        postService.setPostBlocked((long) id);
        response.sendRedirect("/app/posts");
    }
}