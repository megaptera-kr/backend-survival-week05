package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    private final List<Comment> comments;

    public CommentRepository() {
        this.comments = new ArrayList<>();
    }

    public List<Comment> findByPostId(String postId) {
        return comments.stream().filter(comment -> comment.postId().equals(postId)).collect(Collectors.toList());
    }

    public Comment findComment(String postId,String commentId) {
        return comments.stream().filter(comment -> comment.postId().equals(postId)).filter(comment -> comment.id().equals(commentId)).findFirst().orElseThrow();
    }

    public void save(Comment comment) {
        comments.add(comment);
    }


    public void delete(String postId, String commentId) {
        comments.stream().filter(comment -> comment.postId().equals(postId)).collect(Collectors.toList())
                .stream().filter(comment -> comment.id().equals(commentId)).forEach(comment -> comments.remove(comment));
    }

}
