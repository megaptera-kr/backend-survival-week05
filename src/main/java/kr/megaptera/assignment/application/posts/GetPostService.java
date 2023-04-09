package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
    private final PostRepository postRepository;

    @Autowired
    public GetPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostReadDto execute(String id) throws PostNotFoundException {
        var postEntity = postRepository.find(id);
        if(postEntity == null){
            throw new PostNotFoundException();
        }

        var post = new Post(postEntity);
        var dto = new PostReadDto(post);
        return dto;
    }
}
