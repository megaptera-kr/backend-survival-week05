package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    @Autowired
    PostRepository postRepository;
    public PostDto getPost(String id) {
        return postRepository.getPost(id);
    }
}
