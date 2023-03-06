package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {

    private final Map<CommentId, Comment> comments;

    public CommentRepository() {
        comments = new HashMap<>();
    }

    public List<Comment> findByPostId(String postId) {
        List<Comment> commentDtosByPostId = comments.values().stream()
                .filter(i -> i.getPostId().equals(PostId.of(postId)))
                .collect(Collectors.toList());

        return commentDtosByPostId;
    }

    public void save(Comment comment) {
        comments.put(comment.getId(), comment);
    }

    public Comment find(CommentId id) {
        Comment comment = comments.get(id);
        if ( comment == null ) {
            throw new CommentNotFound();
        }
        return comment;
    }

    public void delete(CommentId id) {
        comments.remove(id);
    }

}
