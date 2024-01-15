package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CommentRepository {
    private final Map<PostId, Map<CommentId, Comment>> postsComments = new HashMap<>();

    private Map<CommentId, Comment> getCommentsByPostId(PostId postId) {
        Map<CommentId, Comment> comments = postsComments.get(postId);
        if (comments == null) {
            return Collections.emptyMap();
        }
        return comments;
    }

    public List<Comment> findAll(PostId postId) {
        return getCommentsByPostId(postId).values().stream()
                .sorted(Comparator.comparing(Comment::getId))
                .toList();
    }

    public Comment find(CommentId commentId, PostId postId) {
        return getCommentsByPostId(postId).get(commentId);
    }

    public void save(Comment comment) {
        if (!postsComments.containsKey(comment.getPostId())) {
            postsComments.put(comment.getPostId(), new HashMap<>());
        }
        postsComments.get(comment.getPostId()).put(comment.getId(), comment);
    }

    public Comment delete(PostId postId, CommentId commentId) {
        return getCommentsByPostId(postId).remove(commentId);
    }
}
