package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreatePostService {
    private final PostRepository postRepository;

    @Autowired
    public CreatePostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostReadDto execute(PostCreateDto dto){
        var model = new Post(dto.getTitle(), dto.getAuthor(), dto.getContent());
        var entity = new PostEntity(model);

        postRepository.add(entity);

        return new PostReadDto(model);
    }

}
