package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    GetCommentsService getCommentsService;

    @Autowired
    CreateCommentService createCommentService;
    @Autowired
    UpdateCommentService updateCommentService;
    @Autowired
    DeleteCommentService deleteCommentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getComments(
            @RequestParam String postId
    ){
        return getCommentsService.getComments(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(
            @RequestParam String postId
            ,@RequestBody CommentDto dto
    ){
        return createCommentService.create(postId, dto);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(
            @PathVariable String id,
            @RequestParam String postId
            ,@RequestBody CommentDto dto
    ){
        return updateCommentService.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto deleteComment(
            @PathVariable String id,
            @RequestParam String postId
    ){
        return deleteCommentService.delete(id);
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CommentNotFound(){
        return "댓글을 찾을 수 없습니다.";
    }
}
