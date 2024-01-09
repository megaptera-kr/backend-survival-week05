package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;

public class CreatePostService {
    public static PostDto CreatePostDto(PostCreateDto postCreateDto) {
        return PostRepository.save(postCreateDto);

    }
}
