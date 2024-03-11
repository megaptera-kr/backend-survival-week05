package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentCreateDto;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CreateCommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDto create(String postId,
                             CommentCreateDto commentCreateDto) {
        Optional<Post> tPost = postRepository.find(PostId.of(postId));
        if (tPost.isEmpty()) {
            throw new PostNotFound();
        }

        Comment comment = new Comment(
                PostId.of(postId),
                commentCreateDto.getAuthor(),
                commentCreateDto.getContent()
        );

        commentRepository.save(comment);

        return new CommentDto(comment);
    }
}
