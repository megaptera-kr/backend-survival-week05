package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private List<CommentDto> getComments(@RequestParam("postId") String postId) {
        return getCommentsService.findAll(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private CommentDto createComment(@RequestParam("postId") String postId, @RequestBody CommentCreateDto commentCreateDto) {
        return createCommentService.create(postId, commentCreateDto);
    }

    @PatchMapping("/{id}")
    private CommentDto updateComment(@PathVariable("id") String commentId, @RequestParam("postId") String postId, @RequestBody CommentUpdateDto commentUpdateDto) {
        return updateCommentService.update(commentId, postId, commentUpdateDto);
    }

    @DeleteMapping("/{id}")
    private CommentDto deleteComment(@PathVariable("id") String commentId, @RequestParam("postId") String postId) {
        return deleteCommentService.delete(commentId, postId);
    }
}
