package kr.megaptera.assignment.application;

import java.util.ArrayList;
import java.util.List;
import kr.megaptera.assignment.dtos.GetCommentListResponse;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCommentsService {

    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<GetCommentListResponse> getCommentList(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<GetCommentListResponse> result = new ArrayList<>();

        for (Comment comment : comments) {
            result.add(new GetCommentListResponse(comment.getId().toString(),
                    comment.getPostId().toString(), comment.getAuthor(), comment.getContent()));
        }

        return result;
    }
}
