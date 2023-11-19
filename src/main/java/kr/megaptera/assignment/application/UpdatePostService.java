package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto updatePostDto(String id, PostDto postDto) {
        Post post = postRepository.find(PostId.of(id));
        if (post == null) {
            throw new PostNotFound();
        }
        post.update(PostTitle.of(postDto.getTitle()), MultilineText.of(postDto.getContent()));
        return new PostDto(post);
    }
}
