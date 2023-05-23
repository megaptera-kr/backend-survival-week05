package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {
    private Map<CommentId, Comment> comments;
    public CommentRepository() {
        comments = new HashMap<CommentId, Comment>();
    }

    public List<Comment> findAllComments(PostId postid) {
        return comments.values().stream().filter(comment -> comment.postId().equals(postid)).toList();
    }

    public void save(Comment comment) {
        comments.put(comment.Id(), comment);
    }

    public Comment findComment(CommentId id) {
        Comment comment = comments.get(id);
        if(comment == null){
            throw new CommentNotFound();
        }
        return comment;
    }


    public Comment delete(CommentId id) {
        return comments.remove(id);
    }
}
