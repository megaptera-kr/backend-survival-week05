package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<PostId, Post> posts;

    public PostRepository() {
        this.posts = new HashMap<PostId, Post>();
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post find(String postId) {
        return posts.get(PostId.of(postId));
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(String postId) {
        posts.remove(PostId.of(postId));
    }
}
