package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.UpdateCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CreateCommentService commentService;
    private GetCommentsService getCommentsService;
    private UpdateCommentService updateCommentService;
    private DeleteCommentService deleteCommentService;
}
