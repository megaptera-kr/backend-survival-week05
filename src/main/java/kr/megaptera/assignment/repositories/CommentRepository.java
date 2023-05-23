package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private final Map<CommentId, Comment> comments;


    public CommentRepository() {
        this.comments = new HashMap<CommentId, Comment>();
    }

    public List<Comment> findAll(String postId) {
        List<Comment> comments = new ArrayList<>(this.comments.values());
        return comments.stream().filter(comment -> comment.postId().equals(PostId.of(postId))).toList();
    }

    public Comment find(String commentId) {
        return comments.get(CommentId.of(commentId));
    }

    public void create(Comment comment) {
        comments.put(comment.id(), comment);
    }

    public void delete(String commentId) {
        comments.remove(CommentId.of(commentId));
    }
}
