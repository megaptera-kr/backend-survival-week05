package kr.megaptera.assignment.repository;

import kr.megaptera.assignment.model.Post;
import kr.megaptera.assignment.model.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
}
