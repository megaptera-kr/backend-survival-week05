package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.model.comment.*;
import kr.megaptera.assignment.model.post.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class CommentRepository {
    Map<CommentId, Comment> commentMap;

    public CommentRepository() {
        this.commentMap = new HashMap<>();
    }

    public List<Comment> findAll(String postId) {
        List<Comment> comments = commentMap
                .values()
                .stream()
                .filter(t -> t.postId().equals(PostId.of(postId))).toList();
        return comments;
    }

    public void save(Comment comment) {
        commentMap.put(comment.id(), comment);
    }

    public Comment find(CommentId id, PostId postId) {
        Comment comment = commentMap.get(id);

        if (comment == null || !comment.postId().equals(postId)) {
            throw new CommentNotFound();
        }
        return comment;
    }

    public void remove(Comment comment) {
        commentMap.remove(comment.id());
    }
}
