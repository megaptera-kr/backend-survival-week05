package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("http://localhost:8000")
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(
            GetCommentsService getCommentsService,
            CreateCommentService createCommentService,
            UpdateCommentService updateCommentService,
            DeleteCommentService deleteCommentService
    ) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> list(@RequestParam String postId) {
        return getCommentsService.list(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestParam String postId, @RequestBody CommentDto commentDto) {
        return createCommentService.create(postId, commentDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto update(@PathVariable String id, @RequestParam String postId, @RequestBody CommentDto commentDto) {
        return updateCommentService.update(id, postId, commentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto delete(@PathVariable String id, @RequestParam String postId) {
        return deleteCommentService.delete(id, postId);
    }
}
