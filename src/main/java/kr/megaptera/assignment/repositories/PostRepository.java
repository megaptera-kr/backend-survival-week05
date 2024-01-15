package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private final Map<PostId, Post> posts = new HashMap<>();

    public List<Post> findAll() {
        return posts.values().stream().sorted(Comparator.comparing(Post::getId)).toList();
    }

    public Post find(PostId id) {
        return posts.get(id);
    }

    public Post save(Post post) {
        posts.put(post.getId(), post);
        return post;
    }

    public Post delete(PostId postID) {
        return posts.remove(postID);
    }
}
