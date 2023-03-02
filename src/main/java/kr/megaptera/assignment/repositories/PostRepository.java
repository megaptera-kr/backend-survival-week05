package kr.megaptera.assignment.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.megaptera.assignment.models.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    private final Map<Long, Post> posts;
    private Long id;

    public PostRepository() {
        this.posts = new HashMap<>();
        this.id = 0L;
    }

    private void generateId() {
        this.id++;
    }

    private void degenerateId() {
        if (this.id > 0) {
            this.id--;
        }
    }


    public Post save(Post post) {
        generateId();
        post.generateId(id);
        posts.put(id, post);
        return post;
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post findById(Long id) {
        return posts.get(id);
    }

    public Post delete(Long id) {
        Post removed = posts.remove(id);
        degenerateId();

        return removed;
    }

}
