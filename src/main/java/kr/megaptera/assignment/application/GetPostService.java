package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostResponseDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostService {
  private final PostRepository postRepository;

  public GetPostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponseDto getPostDetail(String id) {
    PostEntity post = postRepository.find(id);

    return PostResponseDto.of(post);
  }
}
