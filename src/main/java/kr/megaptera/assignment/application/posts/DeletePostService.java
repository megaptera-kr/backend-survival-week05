package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    @Autowired
    public DeletePostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostReadDto execute(String postId) throws PostNotFoundException {

        var entity = postRepository.find(postId);
        if(entity == null){
            throw new PostNotFoundException();
        }

        postRepository.remove(postId);
        var model = new Post(entity);

        return new PostReadDto(model);
    }
}
