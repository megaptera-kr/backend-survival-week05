package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repositories.PostRepository;

public class DeletePostService {
    public static PostDto DeletePostDto(String id) {
        return PostRepository.delete(id);
    }
}
