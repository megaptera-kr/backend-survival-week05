package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CreateCommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDto create(String postId, CommentDto commentDto) {
        if (postRepository.findById(PostId.from(postId)).isEmpty()) {
            throw new PostNotFoundException("Invalid post id");
        }

        Comment comment = new Comment(commentDto.getAuthor(), MultilineText.from(commentDto.getContent()));
        commentRepository.save(PostId.from(postId), comment);

        return CommentDto.from(comment);
    }
}
