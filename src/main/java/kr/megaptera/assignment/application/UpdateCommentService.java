package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.dtos.CommentUpdateDto;
import kr.megaptera.assignment.exceptions.CommentNotFound;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public UpdateCommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDto update(String postId, String commentId, CommentUpdateDto commentUpdateDto) {
        Optional<Post> post = postRepository.find(PostId.of(postId));
        if (post.isEmpty()) {
            throw new PostNotFound();
        }

        Optional<Comment> comment = commentRepository.find(CommentId.of(commentId), PostId.of(postId));
        if (comment.isEmpty()) {
            throw new CommentNotFound();
        }

        Comment update = new Comment(
                comment.get().id(),
                PostId.of(postId),
                comment.get().author(),
                commentUpdateDto.getContent()
        );

        commentRepository.save(update);

        return new CommentDto(update);
    }
}
