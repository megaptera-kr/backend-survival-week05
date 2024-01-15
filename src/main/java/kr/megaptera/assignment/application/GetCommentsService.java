package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Comment;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetCommentsService {

    private final CommentRepository commentRepository;

    public List<CommentDto> getCommentList(String postId) {
        log.info("postId: {}", postId);
        List<Comment> comments = commentRepository.findAll();

        return comments.stream().map(comment -> new CommentDto(comment)).toList();
    }
}
