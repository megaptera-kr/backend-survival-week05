package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.CommentEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private Map<String, CommentEntity> comments;

    public CommentRepository() {
        comments = new HashMap<>();
    }

    public List<CommentEntity> findByPostId(String postId) {
        return comments.values()
                .stream()
                .filter(row -> row.getPostId().equals(postId))
                .toList();
    }

    public CommentEntity find(String commentId) {
        return comments.get(commentId);
    }

    public void add(CommentEntity comment) {
        comments.put(comment.getId(), comment);
    }

    public void update(CommentEntity comment) {
        comments.replace(comment.getId(), comment);
    }

    public void remove(CommentEntity comment) {
        comments.remove(comment.getId());
    }
}
