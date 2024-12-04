package com.sprinboot.blog.springboot_blog_application.repository;

import com.sprinboot.blog.springboot_blog_application.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    //noting to write a sql language there is inbuild function for manupulating data from DB
}
