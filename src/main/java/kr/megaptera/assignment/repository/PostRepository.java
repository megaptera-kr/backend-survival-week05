package kr.megaptera.assignment.repository;

import kr.megaptera.assignment.dto.PostDto;
import kr.megaptera.assignment.exception.PostNotFound;
import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.model.PostId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private Map<PostId, Post> store;

    public PostRepository() {
        this.store = new HashMap<>();
    }

    public void save(Post post) {
        store.put(post.id(), post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public Post findOne(PostId id) {
        Post post = store.get(id);

        if (post == null) {
            throw new PostNotFound();
        }

        return post;
    }

    public Post remove(PostId id) {
        Post post = store.remove(id);

        return post;
    }
}
