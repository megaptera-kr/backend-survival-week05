package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public GetCommentsService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<CommentDto> list(String postId) {
        if (postRepository.findById(PostId.from(postId)) == null) {
            throw new PostNotFoundException("Invalid post id");
        }

        return this.commentRepository.findAll(PostId.from(postId))
                .stream()
                .map(CommentDto::from)
                .toList();
    }
}
