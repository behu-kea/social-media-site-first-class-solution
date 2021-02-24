package com.example.demo.controllers;

import com.example.demo.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class SocialMediaController {
    private ArrayList<Post> posts = new ArrayList<>();

    @GetMapping("/submit")
    public String renderEventForm() {
        return "post-form.html";
    }

    @PostMapping("/create-new-post")
    public String createNewEvent(@RequestParam("title") String title, @RequestParam("visibility") String visibility) {
        // simulating new event creation
        Post post = new Post(title, visibility);
        posts.add(post);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    @ResponseBody
    public ArrayList renderDashboard() {
        ArrayList<Post> publicPosts = new ArrayList<>();

        for (Post post : posts) {
            if(post.visibility.equals("public")) {
                publicPosts.add(post);
            }
        }

        return publicPosts;
    }

}
