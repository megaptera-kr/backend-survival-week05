package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;

public class GetPostService {
    public static PostDto getPostDto(String id) {
        return PostRepository.find(id);
    }
}
