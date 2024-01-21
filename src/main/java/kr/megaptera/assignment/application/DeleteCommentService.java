package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public DeleteCommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDto delete(String id, String postId) {
        if (postRepository.findById(PostId.from(postId)) == null) {
            throw new PostNotFoundException("Invalid post id");
        }

        Comment found = commentRepository.findAll(PostId.from(postId)).stream()
                .filter(comment -> comment.id().equals(CommentId.from(id)))
                .findFirst()
                .orElseThrow(() -> new CommentNotFoundException("Invalid comment id"));
        commentRepository.delete(PostId.from(postId), CommentId.from(id));

        return CommentDto.from(found);
    }
}
