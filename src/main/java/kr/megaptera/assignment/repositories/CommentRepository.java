package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {

    private final Map<PostId, Map<CommentId, Comment>> repository = new HashMap<>();

    public List<Comment> findAll(PostId postId) {
        return repository.get(postId)
                .values()
                .stream()
                .sorted((a, b) -> a.id().compareTo(b.id()))
                .toList();
    }

    public void save(PostId postId, Comment comment) {
        Map<CommentId, Comment> comments = repository.computeIfAbsent(postId, k -> new HashMap<>());
        comments.put(comment.id(), comment);
    }

    public void delete(PostId postId, CommentId commentId) {
        Map<CommentId, Comment> comments = repository.get(postId);
        comments.remove(commentId);
    }
}
