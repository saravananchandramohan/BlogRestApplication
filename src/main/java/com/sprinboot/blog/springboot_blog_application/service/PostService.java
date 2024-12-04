package com.sprinboot.blog.springboot_blog_application.service;

import com.sprinboot.blog.springboot_blog_application.payload.PostDto;
;import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto>getAllPost();
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    void deletePost(long id);
}
