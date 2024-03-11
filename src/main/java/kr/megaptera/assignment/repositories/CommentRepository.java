package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CommentRepository {
    Map<CommentId, Comment> comments;

    public CommentRepository() {
        this.comments = new HashMap<>();
    }

    public List<Comment> findAll(PostId postId) {

        return comments.values().stream()
                .filter(comment -> comment.postId().equals(postId))
                .toList();
    }

    public Optional<Comment> find(CommentId id, PostId postId) {
        Comment comment = comments.get(id);

        if (comment == null || !comment.postId().equals(postId)) {
            return Optional.empty();
        }

        return Optional.of(comment);
    }

    public void save(Comment comment) {
        comments.put(comment.id(), comment);
    }

    public void delete(CommentId commentId) {
        comments.remove(commentId);
    }
}
