package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.exceptions.PostNotFound;
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

    public Post find(String id) {
        Post post = posts.get(PostId.of(id));
        if (post == null)
            throw new PostNotFound();
        return post;
    }

    public Post save(Post post) {
        posts.put(post.id(), post);
        return post;
    }

    public Post delete(String id) {
        Post post = posts.get(PostId.of(id));
        posts.remove(PostId.of(id));
        return post;
    }
}
