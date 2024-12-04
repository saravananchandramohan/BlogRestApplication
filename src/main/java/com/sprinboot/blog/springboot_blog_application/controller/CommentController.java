package com.sprinboot.blog.springboot_blog_application.controller;

import com.sprinboot.blog.springboot_blog_application.entity.Comment;
import com.sprinboot.blog.springboot_blog_application.payload.CommentDto;
import com.sprinboot.blog.springboot_blog_application.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@PathVariable(name = "postId") long post_id, @RequestBody  CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(post_id,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllComments(@PathVariable(name = "postId") long postId){
        return commentService.getAlComment(postId);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentByCommentId(@PathVariable(name = "postId") long postId,
                                            @PathVariable(name = "commentId") long commentId){
        return commentService.getCommentByCommentId(postId,commentId);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPostId(@PathVariable(name = "postId") long postId,
                                            @PathVariable(name = "commentId") long commentId,
                                            @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(postId,commentId,commentDto),HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable (name = "postId") long postId,
                                @PathVariable (name = "commentId") long commentId){
        commentService.deleteComment(postId,commentId);
        return "Comment deleted Successfully";
    }




}

