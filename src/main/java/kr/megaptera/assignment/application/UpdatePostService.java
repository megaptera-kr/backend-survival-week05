package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePostService {
    @Autowired
    PostRepository postRepository;
    public PostDto updatePost(PostDto post) {
        PostDto dto = postRepository.getPost(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        postRepository.updatePost(dto);
        return dto;
    }
}
