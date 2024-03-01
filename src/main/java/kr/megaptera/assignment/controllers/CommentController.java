package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CreateCommentService createCommentService;
    private GetCommentsService getCommentsService;
    private UpdateCommentService updateCommentService;
    private DeleteCommentService deleteCommentService;
    private final ObjectMapper objectMapper;

    public CommentController() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping
    public List<CommentDto> getComments(@PathVariable("postId") String postId){
        CommentDto dto = new CommentDto(postId);
        return getCommentsService.getComments(dto);
    }

    @PostMapping
    public CommentDto postComment(@PathVariable("postId") String postId
                                , @RequestBody CommentDto comment){
        comment.setPostId(postId);
        return createCommentService.createComment(comment);
    }

    @PatchMapping("/{id}")
    public CommentDto updateComment( @PathVariable("id")String id
                                    ,@PathVariable("postId")String postId
                                    ,@RequestBody CommentDto comment){
        comment.setId(id);
        comment.setPostId(postId);
        return updateCommentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public CommentDto deleteComment( @PathVariable("id") String id
                                    ,@PathVariable("postId") String postId){
        CommentDto deleteComment = new CommentDto(postId, id);
        return deleteCommentService.deleteComment(deleteComment);
    }
}
