package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private Map<PostId, Post> posts = new HashMap<>();

    public List<Post> findAll() {
        return posts.values().stream()
                .sorted((a, b) -> a.id().compare(b.id()))
                .toList();
    }

    public Post findById(PostId id){
        return posts.get(id);
    }

    public void createPost(Post post) {
        posts.put(post.id(), post);
    }

    public void remove(PostId id) {
        posts.remove(id);
    }
}
