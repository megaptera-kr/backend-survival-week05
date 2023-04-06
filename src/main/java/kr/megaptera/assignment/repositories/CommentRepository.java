package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.controllers.dtos.CommentCreateDto;
import kr.megaptera.assignment.controllers.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    Map<CommentId, Comment> comments = new HashMap<>();

    public List<Comment> getComments(PostId postId) {
        return comments.values().stream()
                .filter(comment -> comment.postId().equals(postId))
                .sorted((a,b) -> a.id().compare(b.id()))
                .toList();
    }

    public void createComment(Comment comment) {
        comments.put(comment.id(), comment);
    }

    public void deleteComment(String id) {
        comments.remove(CommentId.of(id));
    }

    public Comment find(CommentId id){
        return comments.get(id);
    }
}
