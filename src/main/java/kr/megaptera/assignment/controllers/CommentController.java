package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CreateCommentService createCommentService;

    @Autowired
    DeleteCommentService deleteCommentService;

    @Autowired
    GetCommentsService getCommentsService;

    @Autowired
    UpdateCommentService updateCommentService;

    @CrossOrigin(origins = "*")
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> list(@RequestParam String postId){
        return getCommentsService.findCommentList(postId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestParam String postId,@RequestBody CommentCreateDto commentDto) {
        return createCommentService.createComment(postId, commentDto);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestParam String postId,@RequestBody CommentUpdateDto commentUpdateDto) {
        updateCommentService.updateComment(postId,id,commentUpdateDto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id, @RequestParam String postId) {
        deleteCommentService.deleteComment(id, postId);
    }

}
