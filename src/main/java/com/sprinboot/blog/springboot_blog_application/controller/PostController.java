package com.sprinboot.blog.springboot_blog_application.controller;

import com.sprinboot.blog.springboot_blog_application.entity.Post;
import com.sprinboot.blog.springboot_blog_application.payload.PostDto;
import com.sprinboot.blog.springboot_blog_application.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    //create blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
       return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //get all blog post
    @GetMapping
    public List<PostDto>getAllPosts(){
       return postService.getAllPost();
    }
    //get post by id
    @GetMapping("{id}")
    public PostDto getPostById(@PathVariable (name="id") long id){
        return postService.getPostById(id);
    }
    //update post
    @PutMapping("{id}")
    public PostDto updatePost(@RequestBody PostDto postDto,@PathVariable(name="id") long id){
        return postService.updatePost(postDto, id);
    }
    //delete post
    @DeleteMapping("{id}")
    public String deletePost(@PathVariable(name="id") long id){
        postService.deletePost(id);
        return "post deleted successfully";
    }
}
