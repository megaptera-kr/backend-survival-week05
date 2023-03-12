package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateRequestDto;
import kr.megaptera.assignment.dtos.PostResponseDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostService {
  private final PostRepository postRepository;

  public CreatePostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto createPost(PostCreateRequestDto postCreateDto) {
    PostEntity post = new PostEntity(
      postCreateDto.getTitle(),
      postCreateDto.getAuthor(),
      postCreateDto.getContent()
    );

    postRepository.save(post);

    return PostResponseDto.of(post);
  }
}
