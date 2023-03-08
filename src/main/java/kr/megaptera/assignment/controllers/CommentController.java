package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.comment.*;
import kr.megaptera.assignment.dtos.comment.*;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private final CreateCommentService createCommentService;
    private final DeleteCommentService deleteCommentService;
    private final GetCommentsService getCommentsService;
    private final UpdateCommentService updateCommentService;

    public CommentController(CreateCommentService createCommentService,
                             DeleteCommentService deleteCommentService,
                             GetCommentsService getCommentsService,
                             UpdateCommentService updateCommentService) {
        this.createCommentService = createCommentService;
        this.deleteCommentService = deleteCommentService;
        this.getCommentsService = getCommentsService;
        this.updateCommentService = updateCommentService;
    }

    @GetMapping
    public List<CommentDto> list(
            @RequestParam String postId
    ) {
        List<CommentDto> commentDtos = getCommentsService.getCommentDtos(postId);
        return commentDtos;
    }

    @PostMapping
    public CommentDto create(
            @RequestParam String postId,
            @RequestBody CommentCreateDto commentCreateDto
    ) {
        CommentDto commentDto = createCommentService.create(postId, commentCreateDto);
        return commentDto;
    }

    @PatchMapping("/{id}")
    public CommentDto update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentUpdateDto commentUpdateDto
    ) {
        CommentDto updated = updateCommentService.update(id, postId, commentUpdateDto);
        return updated;
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(
            @PathVariable String id,
            @RequestParam String postId
    ) {
        CommentDto deleted = deleteCommentService.delete(id, postId);
        return deleted;
    }

    @ExceptionHandler(CommentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String commentNotFound() {
        return "댓글을 찾을 수 없습니다.";
    }
}
