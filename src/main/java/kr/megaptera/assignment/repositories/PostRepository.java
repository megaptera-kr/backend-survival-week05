package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<String, Post> posts;

    public PostRepository() {
        this.posts = new HashMap<String,Post>();
    }

    public Post find(String id) {
        Post post = posts.get(id);
        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }


    public void delete(String id) {
        posts.remove(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }
}
