package kr.megaptera.assignment.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.megaptera.assignment.models.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {
    private final Map<Long, Comment> comments;
    private Long id;

    public CommentRepository() {
        this.comments = new HashMap<>();
        this.id = 0L;
    }

    public void generateId() {
        this.id++;
    }

    public void degenerateId() {
        if (this.id > 0) {
            this.id--;
        }
    }

    public Comment save(Comment comment) {
        generateId();
        comment.generateId(id);

        comments.put(id, comment);

        return comment;
    }

    public List<Comment> findByPostId(Long postId) {
        List<Comment> result = new ArrayList<>();

        for (Comment comment: comments.values()) {
            if (comment.getPostId().equals(postId)) {
                result.add(comment);
            }
        }

        return result;
    }

    public Comment findById(Long id) {
        return comments.get(id);
    }

    public Comment delete(Long id) {
        Comment removed = comments.remove(id);
        degenerateId();

        return removed;
    }
}
