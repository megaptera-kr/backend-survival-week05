package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPostsService {
    @Autowired
    private PostRepository postRepository;
    public List<PostDto> getPosts() {
        return postRepository.getPosts();
    }
}
