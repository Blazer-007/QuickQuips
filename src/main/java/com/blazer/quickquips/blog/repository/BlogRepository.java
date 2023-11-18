package com.blazer.quickquips.blog.repository;

import com.blazer.quickquips.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByCategory(String category);

    List<Blog> findByCreatedBy(String author);
}
