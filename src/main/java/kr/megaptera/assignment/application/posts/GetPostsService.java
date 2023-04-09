package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final PostRepository repository;

    @Autowired
    public GetPostsService(PostRepository postRepository) {
        repository = postRepository;
    }

    public List<PostReadDto> execute() {
        var entities = repository.findAll();
        var posts = entities.stream().map(entity -> new Post(entity)).toList();
        var dtos = posts.stream().map(post -> new PostReadDto(post)).toList();
        return dtos;
    }
}
