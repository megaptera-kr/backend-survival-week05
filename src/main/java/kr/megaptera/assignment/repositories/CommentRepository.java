package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.comments.Comment;
import kr.megaptera.assignment.models.comments.CommentId;
import kr.megaptera.assignment.models.posts.PostId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private final Map<PostId, Map<CommentId, Comment>> comments = new HashMap<>();

    public void save(String postIdStr, Comment comment) {
        PostId postId = PostId.of(postIdStr);

        if (!this.comments.containsKey(postId)) {
            this.comments.put(postId, new HashMap<>());
        }

        Map<CommentId, Comment> commentMap = this.comments.get(postId);

        commentMap.put(comment.getId(), comment);
    }

    public List<Comment> findAll(String postId) {
        Map<CommentId, Comment> commentMap = this.comments.get(PostId.of(postId));
        if (commentMap == null) {
            return new ArrayList<>();
        } else {
            return commentMap.values().stream().toList();
        }
    }

    public Comment find(String postId, String commentId) {
        return this.comments.get(PostId.of(postId)).get(CommentId.of(commentId));
    }

    public void remove(String postId, String commentId) {
        Map<CommentId, Comment> commentMap = this.comments.get(PostId.of(postId));

        if (commentMap != null) {
            commentMap.remove(CommentId.of(commentId));
        }
    }
}
