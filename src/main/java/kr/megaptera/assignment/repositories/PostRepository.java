package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PostRepository {

    private final Map<PostId, Post> repository = new HashMap<>();

    public List<Post> findAll() {
        return repository.values().stream()
                .sorted((a, b) -> a.id().compareTo(b.id()))
                .toList();
    }

    public Optional<Post> findById(PostId postId) {
        return Optional.ofNullable(repository.get(postId));
    }

    public void save(Post post) {
        repository.put(post.id(), post);
    }

    public void delete(PostId id) {
        repository.remove(id);
    }

}
