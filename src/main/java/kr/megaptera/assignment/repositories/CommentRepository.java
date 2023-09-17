package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private Map<CommentId, Comment> comments;

    public CommentRepository() {
        comments = new HashMap<>();

        comments.put(new CommentId("0001COMMENT"),
                new Comment(new CommentId("0001COMMENT"),
                        new PostId("0001POST"),
                        "작성자",
                        new MultilineText("내용")));
    }

    public List<Comment> findAll(String postId) {
        return comments.values().stream().filter(comment -> comment.postId().equals(postId)).toList();
    }

    public void save(Comment comment) {
        comments.put(comment.id(), comment);
    }

    public Comment find(CommentId commentId, PostId postId) {
        Comment comment = comments.get(commentId);

        if (comment == null || !comment.postId().equals(postId)) {
            throw new CommentNotFound();
        }

        return comment;
    }

    public void delete(CommentId commentId) {
        comments.remove(commentId);
    }
}
