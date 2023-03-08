package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;
import kr.megaptera.assignment.model.post.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class PostRepository {

    Map<PostId, Post> postMap;

    public PostRepository() {
        this.postMap = new HashMap<>();
    }

    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    public Post find(String id) {
        Post post = postMap.get(PostId.of(id));

        if (post == null) {
            throw new postNotFound();
        }
        return post;
    }

    public Post save(PostCreateDto postCreateDto) {
        Post post = new Post(
                postCreateDto.getTitle(),
                postCreateDto.getAuthor(),
                PostContent.of(postCreateDto.getContent())
        );
        postMap.put(post.id(), post);
        return post;
    }

    public void remove(Post post) {
        postMap.remove(post.id());
    }
}
