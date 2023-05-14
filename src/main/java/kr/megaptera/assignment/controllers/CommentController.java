package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public List<CommentDto> list(@RequestParam String postId) {
        List<CommentDto> commentDtos =
                getCommentsService.getCommentDtos(postId);

        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestParam String postId,
                             @RequestBody CommentDto commentDto) {
        CommentDto created = createCommentService
                .create(postId, commentDto);

        return created;
    }

    @PatchMapping("/{id}")
    public CommentDto update(
            @PathVariable String id,
            @RequestParam String postId,
            @RequestBody CommentDto commentDto
    ) throws CommentNotFound {
        CommentDto updated = updateCommentService
                .updateComment(id, postId, commentDto);

        return updated;
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(
            @PathVariable String id,
            @RequestParam String postId
    ) throws CommentNotFound {
        CommentDto deleted = deleteCommentService.deleteComment(id, postId);

        return deleted;
    }

//    @ExceptionHandler(CommentNotFound.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String postNotFound() {
//        return "댓글을 찾을 수 없습니다.";
//    }
}
