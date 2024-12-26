package com.example.demoex.controllers;

import com.example.demoex.entities.Review;
import com.example.demoex.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/review")
@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping("/create")
    public String create() {
        return "create";

    }
    @PostMapping("/create/{id}")
    public String create(@PathVariable int id,
                         @RequestParam String content) {
//        this.reviewService.create(Review.builder()
//                        .content(content)
//                        .createDate(LocalDateTime.now())
//                        .post()
//                .build());
        return "redirect:/post/detail/" + id;

    }

    @GetMapping("/list")
    public String list() {
        return "list";

    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable int id) {
        return "board/review_form";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return "delete";

    }
}
