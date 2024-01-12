package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domain.Post;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostsService {

    private final PostRepository postRepository;

    public List<PostDto> getPostList() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(PostDto::new).toList();
    }
}
