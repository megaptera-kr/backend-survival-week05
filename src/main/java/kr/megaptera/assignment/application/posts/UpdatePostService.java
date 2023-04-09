package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.dtos.posts.PostUpdateDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    @Autowired
    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostReadDto execute(String postId, PostUpdateDto reqBody) throws PostNotFoundException {
        var oldEntity = postRepository.find(postId);
        if(oldEntity == null){
            throw new PostNotFoundException();
        }

        var model = new Post(oldEntity);
        model.update(reqBody.getTitle(), reqBody.getContent());

        var newEntity = new PostEntity(model);
        postRepository.update(newEntity.getId(), newEntity);

        return new PostReadDto(model);
    }
}
