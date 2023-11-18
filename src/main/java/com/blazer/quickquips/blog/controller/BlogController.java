package com.blazer.quickquips.blog.controller;

import com.blazer.quickquips.blog.model.Blog;
import com.blazer.quickquips.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/{blogId}")
    public ResponseEntity<Blog> getBlog(@PathVariable("blogId") Integer blogId) {
        Blog blog = blogService.findBlogById(blogId);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Integer> createBlog(@RequestBody Blog blog) {
        Integer blogId = blogService.createBlog(blog);
        return new ResponseEntity<>(blogId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable("blogId") Integer blogId) {
        String result = blogService.deleteBlog(blogId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
