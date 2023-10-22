package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.posts.Post;
import kr.megaptera.assignment.models.posts.PostId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<PostId, Post> posts = new HashMap<>();

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post find(String postId) {
        return this.posts.get(PostId.of(postId));
    }

    public void save(Post post) {
        this.posts.put(post.getId(), post);
    }

    public void remove(String postId) {
        this.posts.remove(PostId.of(postId));
    }
}
