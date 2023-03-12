package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostResponseDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostsService {
  private final PostRepository postRepository;

  public GetPostsService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<PostResponseDto> getPosts() {
    List<PostEntity> posts = postRepository.findAll();

    return posts.stream().map(PostResponseDto::of).toList();
  }
}
