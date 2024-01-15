package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.repositories.PostRepository;

public class UpdatePostService {
    public static PostDto UpdatePostDto(String id, PostUpdateDto postUpdateDto) {
        return PostRepository.update(id, postUpdateDto);
    }
}
