package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.MultilineText;
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
        posts = new HashMap<>();

        posts.put(new PostId("0001POST"), new Post(new PostId("0001POST"), "제목", "작성자", new MultilineText("내용")));
    }

    public List<Post> findAll() {
        return posts.values().stream().sorted((a, b) -> a.id().compareTo(b.id())).toList();
    }

    public Post find(PostId postId) {
        Post post = posts.get(postId);

        if (post == null) {
            throw new PostNotFound();
        }

        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(PostId postId) {
        posts.remove(postId);
    }
}
