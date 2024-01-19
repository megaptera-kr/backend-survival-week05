package kr.megaptera.assignment.application;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFoundException;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {

    private final PostRepository postRepository;

    public DeletePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto delete(String id) {
        PostId postId = PostId.from(id);
        Post found = this.postRepository.findById(postId);
        if (found == null) {
            throw new PostNotFoundException("invalid post id");
        }
        postRepository.delete(postId);
        
        return PostDto.from(found);
    }
}
