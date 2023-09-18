package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dto.PostDto;
import kr.megaptera.assignment.dto.PostUpdateDto;
import kr.megaptera.assignment.model.MultiLineText;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.model.PostId;
import kr.megaptera.assignment.model.SingleLineText;
import kr.megaptera.assignment.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;


    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto updatePost(PostUpdateDto postUpdateDto, String id) {
        Post post = postRepository.findOne(PostId.of(id));
        post.update(
                new SingleLineText(postUpdateDto.getTitle()),
                new MultiLineText(postUpdateDto.getContent())
        );

        return new PostDto(post);
    }
}
