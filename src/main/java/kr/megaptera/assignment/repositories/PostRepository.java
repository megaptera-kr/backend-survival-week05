package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<PostId, Post> posts;

    public PostRepository() {
        this.posts = new HashMap<>();
    }

    public List<Post> findAll() {
        return posts.values().stream().toList();
    }

    public Post findPost(PostId id) {
        Post post = posts.get(id);
        if (post == null) {
            throw new PostNotFound();
        }
        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(Post post) {
        posts.remove(post.id());
    }
}
