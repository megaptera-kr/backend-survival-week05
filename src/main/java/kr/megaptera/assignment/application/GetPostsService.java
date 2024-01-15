package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.PostConverter;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDetailDto> listPostDtos() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostConverter::toPostDetailDto).toList();
    }
}
