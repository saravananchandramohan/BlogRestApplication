package com.sprinboot.blog.springboot_blog_application.service.impl;

import com.sprinboot.blog.springboot_blog_application.entity.Post;
import com.sprinboot.blog.springboot_blog_application.exception.ResourceNotFoundException;
import com.sprinboot.blog.springboot_blog_application.payload.PostDto;
import com.sprinboot.blog.springboot_blog_application.repository.PostRepository;
import com.sprinboot.blog.springboot_blog_application.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository=postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        // convert DTO to entity
        Post post=mapToEntity(postDto);

        Post newPost=postRepository.save(post);

        //convert entity to DTO

        PostDto postResponse=mapToDto(newPost);
        return postResponse;

    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        postRepository.save(post);
        return mapToDto(post);
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    private PostDto mapToDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }
    private Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }


}
