package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.comments.CreateCommentService;
import kr.megaptera.assignment.application.comments.DeleteCommentService;
import kr.megaptera.assignment.application.comments.GetCommentsService;
import kr.megaptera.assignment.application.comments.UpdateCommentService;
import kr.megaptera.assignment.dtos.comments.CommentDto;
import kr.megaptera.assignment.dtos.comments.CreateCommentDto;
import kr.megaptera.assignment.dtos.comments.UpdateCommentDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    CreateCommentService createCommentService;
    DeleteCommentService deleteCommentService;
    GetCommentsService getCommentsService;
    UpdateCommentService updateCommentService;

    public CommentController(CreateCommentService createCommentService, DeleteCommentService deleteCommentService, GetCommentsService getCommentsService, UpdateCommentService updateCommentService) {
        this.createCommentService = createCommentService;
        this.deleteCommentService = deleteCommentService;
        this.getCommentsService = getCommentsService;
        this.updateCommentService = updateCommentService;
    }

    // 댓글 조회
    @GetMapping("")
    public List<CommentDto> getComment(@RequestParam("postId") String postId) {
        return this.getCommentsService.findAll(postId);
    }

    // 댓글 작성
    @PostMapping("")
    public CommentDto postComment(@RequestParam("postId") String postId, @RequestBody CreateCommentDto createCommentDto) {
        return this.createCommentService.create(postId, createCommentDto);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public CommentDto patchComment(@PathVariable("id") String commentId, @RequestParam("postId") String postId, @RequestBody UpdateCommentDto updateCommentDto) {
        return this.updateCommentService.update(postId, commentId, updateCommentDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public CommentDto deleteComment(@PathVariable("id") String commentId, @RequestParam("postId") String postId) {
        return this.deleteCommentService.delete(postId, commentId);
    }
}
