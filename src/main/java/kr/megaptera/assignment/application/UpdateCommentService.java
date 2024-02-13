package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.CommentNotFoundException;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateCommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public UpdateCommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDto update(String id, String postId, CommentDto commentDto) {
        if (postRepository.findById(PostId.from(postId)) == null) {
            throw new PostNotFoundException("Invalid post id");
        }
        
        Comment found = commentRepository.findAll(PostId.from(postId)).stream()
                .filter(comment -> comment.id().equals(CommentId.from(id)))
                .findFirst()
                .orElseThrow(() -> new CommentNotFoundException("Invalid comment id"));
        Comment updated = new Comment(found, MultilineText.from(commentDto.getContent()));
        commentRepository.save(PostId.from(postId), updated);

        return CommentDto.from(updated);
    }
}
