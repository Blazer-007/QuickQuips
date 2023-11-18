package com.blazer.quickquips.blog.service;

import com.blazer.quickquips.blog.model.Blog;
import com.blazer.quickquips.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogsService {

    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getTopBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByCategory(String category) {
        return blogRepository.findByCategory(category);
    }

    public List<Blog> getBlogsByAuthor(String author) {
        return blogRepository.findByCreatedBy(author);
    }
}
