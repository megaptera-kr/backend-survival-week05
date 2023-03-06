package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getCommentDto(String postId) {
        List<CommentDto> commentDtos = new ArrayList<>();

        List<Comment> comments = commentRepository.findAll(PostId.of(postId));

        if (comments.size() > 0) {
            comments.stream().forEach(i -> {
                CommentDto cmtDto = new CommentDto(i.id().toString(), i.author(), i.content().toString());
                commentDtos.add(cmtDto);
            });
        }

        return commentDtos;
    }

}
