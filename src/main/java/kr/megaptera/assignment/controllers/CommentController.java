package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CreateCommentDto;
import kr.megaptera.assignment.dtos.UpdateCommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import org.springframework.http.HttpStatus;
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
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService,
                             CreateCommentService createCommentService,
                             UpdateCommentService updateCommentService,
                             DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    private List<CommentDto> list(
        @RequestParam String postId
    ) {
        return getCommentsService.comments(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private CommentDto create(
        @RequestParam String postId,
        @RequestBody CreateCommentDto createCommentDto
    ) {
        return createCommentService.create(postId, createCommentDto);
    }

    @PatchMapping("/{id}")
    private CommentDto update(
        @PathVariable String id,
        @RequestParam String postId,
        @RequestBody UpdateCommentDto updateCommentDto
    ) {
        return updateCommentService.update(id, postId, updateCommentDto);
    }

    @DeleteMapping("/{id}")
    private CommentDto delete(
        @PathVariable String id,
        @RequestParam String postId
    ) {
        return deleteCommentService.delete(id, postId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private CommentNotFound commentNotFound() {
        return new CommentNotFound();
    }
}
