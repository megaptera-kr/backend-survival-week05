package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    @Autowired
    PostRepository postRepository;
    public PostDto deletePost(PostDto dto) {
        PostDto deletePost = postRepository.getPost(dto.getId());
        postRepository.deletePost(dto);
        return deletePost;
    }
}
