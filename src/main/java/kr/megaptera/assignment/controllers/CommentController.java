package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDetailDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<CommentDetailDto> list(@RequestParam String postId) {
        return getCommentsService.getComments(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDetailDto create(@RequestParam String postId, @RequestBody CommentCreateDto dto) {
        return createCommentService.createComment(postId, dto);
    }

    @PatchMapping("/{id}")
    public CommentDetailDto update(@RequestParam String postId, @PathVariable String id, @RequestBody CommentUpdateDto dto) {
        return updateCommentService.updateComment(id, postId, dto);
    }

    @DeleteMapping("/{id}")
    public CommentDetailDto delete(@RequestParam String postId, @PathVariable String id) {
        return deleteCommentService.deleteComment(postId, id);
    }
}
