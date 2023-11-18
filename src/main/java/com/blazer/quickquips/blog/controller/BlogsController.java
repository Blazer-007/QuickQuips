package com.blazer.quickquips.blog.controller;

import com.blazer.quickquips.blog.model.Blog;
import com.blazer.quickquips.blog.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("blogs")
public class BlogsController {

    @Autowired
    BlogsService blogsService;

    @GetMapping("/")
    public ResponseEntity<List<Blog>> getTopBlogs() {
        List<Blog> blogs = blogsService.getTopBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<Blog>> getBlogsByAuthor(@PathVariable("userName") String author) {
        List<Blog> blogs = blogsService.getBlogsByAuthor(author);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable("category") String category) {
        List<Blog> blogs = blogsService.getBlogsByCategory(category);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}
