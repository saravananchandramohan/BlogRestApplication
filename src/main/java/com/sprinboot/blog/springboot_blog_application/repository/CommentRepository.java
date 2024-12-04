package com.sprinboot.blog.springboot_blog_application.repository;

import com.sprinboot.blog.springboot_blog_application.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
