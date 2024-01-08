package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostAuthor;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {

    private final PostRepository postRepository;

    public CreatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPostDto(PostDto postDto) {
        Post post;
        if(postDto.getId()!=null){
            post =new Post(PostId.of(postDto.getId()),PostTitle.of(postDto.getTitle()), PostAuthor.of(postDto.getAuthor()), MultilineText.of(postDto.getContent()));
        }else{
            post = new Post(PostTitle.of(postDto.getTitle()), PostAuthor.of(postDto.getAuthor()), MultilineText.of(postDto.getContent()));
        }
        postRepository.save(post);
        return new PostDto(post);
    }
}
