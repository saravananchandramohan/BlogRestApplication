package com.sprinboot.blog.springboot_blog_application.service;

import com.sprinboot.blog.springboot_blog_application.entity.Comment;
import com.sprinboot.blog.springboot_blog_application.payload.CommentDto;

import java.util.ArrayList;
import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> getAlComment(long postId);
    CommentDto updateComment(long postId,long commentId,CommentDto commentDto);
    void deleteComment(long postId,long commentId);
    CommentDto getCommentByCommentId(long postId,long commentId);
}
