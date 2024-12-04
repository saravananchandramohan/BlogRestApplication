package com.sprinboot.blog.springboot_blog_application.service.impl;

import com.sprinboot.blog.springboot_blog_application.entity.Comment;
import com.sprinboot.blog.springboot_blog_application.entity.Post;
import com.sprinboot.blog.springboot_blog_application.exception.ResourceNotFoundException;
import com.sprinboot.blog.springboot_blog_application.payload.CommentDto;
import com.sprinboot.blog.springboot_blog_application.repository.CommentRepository;
import com.sprinboot.blog.springboot_blog_application.repository.PostRepository;
import com.sprinboot.blog.springboot_blog_application.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
    }




    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment=mapToEntity(commentDto);
        Post post=postRepository.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post","id",postId));
        //set post to comment entity
        comment.setPost(post);
        //comment entity to DB
        Comment newcomment=commentRepository.save(comment);

        return mapToDto(newcomment);
    }

    @Override
    public List<CommentDto> getAlComment(long postId) {
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));

        List<Comment> comments=commentRepository.findAll();

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));

        Comment comment=commentRepository.findById(commentId).orElseThrow(() ->new ResourceNotFoundException("Comment","id",commentId));

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment= commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post=postRepository.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post","id",postId));
        Comment comment=commentRepository.findById(commentId).orElseThrow(() ->new ResourceNotFoundException("Comment","id",commentId));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto getCommentByCommentId(long postId, long commentId) {
        Post post=postRepository.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post","id",postId));
        Comment comment=commentRepository.findById(commentId).orElseThrow(() ->new ResourceNotFoundException("Comment","id",commentId));
        return mapToDto(comment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
