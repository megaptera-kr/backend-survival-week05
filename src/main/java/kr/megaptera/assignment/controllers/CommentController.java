package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.models.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService, CreateCommentService createCommentService, UpdateCommentService updateCommentService, DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    public List<CommentDto> list(@RequestParam String postId){
        List<CommentDto> cd = getCommentsService.getCommentDtos(postId);
        return cd;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto post(@RequestParam String postId,
                           @RequestBody CommentCreateDto commentCreateDto){
        CommentDto created = createCommentService
                .createCommentDto(postId, commentCreateDto);
        return created;
    }

    @PatchMapping("/{id}")
    public CommentDto update(@PathVariable String id,
                             @RequestParam String postId,
                             @RequestBody CommentUpdateDto commentUpdateDto){
        CommentDto commentDto = updateCommentService.updateComment(id, postId, commentUpdateDto);

        return commentDto;
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(@PathVariable String id,
                          @RequestParam String postId){
        CommentDto commentDto = deleteCommentService.deleteComment(id, postId);

        return commentDto;
    }
}
