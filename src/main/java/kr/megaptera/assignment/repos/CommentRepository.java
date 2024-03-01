package kr.megaptera.assignment.repos;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    private List<Comment> comments = new ArrayList<>();
    public List<CommentDto> getComments(CommentDto dto) {
        return comments.stream()
                .filter(comment -> comment.postId().equals(dto.getPostId()))
                .map(comment -> new CommentDto(comment))
                .collect(Collectors.toList());
    }

    public CommentDto getCommentByCommentId(CommentDto dto){
        Comment findComment = comments.stream().filter(comment -> comment.id().equals(dto.getId()))
                .findFirst()
                .get();
        return new CommentDto(findComment);
    }


    public void createComment(CommentDto comment) {
        comments.add(new Comment( comment.getPostId()
                                , comment.getId()
                                , comment.getAuthor()
                                , comment.getAuthor()));
    }

    public void updateComment(CommentDto dto) {
        comments = comments.stream().map(comment -> {
            if(comment.id().equals(dto.getId())
                    && comment.postId().equals(dto.getPostId())) return new Comment(dto);
            else return comment;
        }).collect(Collectors.toList());
    }

    public void deleteComment(CommentDto dto) {
        comments = comments.stream()
                .filter(comment -> !comment.id().equals(dto.getId()))
                .collect(Collectors.toList());
    }
}
