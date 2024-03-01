package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
    @Autowired
    CommentRepository commentRepository;
    public List<CommentDto> getComments(CommentDto dto) {
        return commentRepository.getComments(dto);
    }
}
