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
        this.posts = new HashMap<PostId, Post>();

//        this.posts.put(PostId.of("001"), new Post(PostId.of("001"), "제목", "song", MultilineText.of("테스트입니다")));
//        this.posts.put(PostId.of("002"), new Post(PostId.of("002"), "2등", "park", MultilineText.of("2등이다!!")));
    }

    public List<Post> findAll() {
        return posts.values().stream()
                .sorted((a, b) -> a.id().compareTo(b.id()))
                .toList();
    }

    public Post find(PostId id) {
        Post post = posts.get(id);
        if (post == null) {
            throw new PostNotFound();
        }
        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(PostId id) {
        posts.remove(id);
    }
}
