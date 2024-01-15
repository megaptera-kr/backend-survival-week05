package kr.megaptera.assignment.application;

import kr.megaptera.assignment.converters.PostConverter;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.SingleLineText;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    private final PostRepository postRepository;

    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDetailDto updatePost(String id, PostUpdateDto dto) {
        Post post = postRepository.find(PostId.of(id));
        post.updatePost(
                SingleLineText.of(dto.getTitle()),
                MultiLineText.of(dto.getContent())
        );
        return PostConverter.toPostDetailDto(postRepository.save(post));
    }
}
