package kr.megaptera.assignment.application;

import java.util.ArrayList;
import java.util.List;
import kr.megaptera.assignment.dtos.GetPostListResponse;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostsService {
    private final PostRepository postRepository;

    public GetPostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<GetPostListResponse> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<GetPostListResponse> response = new ArrayList<>();

        for (Post post : posts) {
            response.add(new GetPostListResponse(post.getId().toString(), post.getTitle(),
                    post.getAuthor(), post.getContent()));
        }

        return response;
    }
}
