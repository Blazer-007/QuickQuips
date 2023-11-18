package com.blazer.quickquips.blog.service;

import com.blazer.quickquips.blog.model.Blog;
import com.blazer.quickquips.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public Integer createBlog(Blog blog) {
        return blogRepository.save(blog).getId();
    }

    public Blog findBlogById(Integer id) {
        return blogRepository.findById(id).get();
    }

    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public String deleteBlog(Integer id) {
        blogRepository.deleteById(id);
        return "SUCCESS";
    }
}
