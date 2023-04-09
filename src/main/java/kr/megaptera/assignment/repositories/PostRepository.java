package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private Map<String, PostEntity> posts;

    public PostRepository() {
            posts = new HashMap<>();
        }

    public List<PostEntity> findAll() {
        List<PostEntity> copiedPosts = posts.values()
                .stream()
                .map(row -> row.clone())
                .collect(Collectors.toList());

        return copiedPosts;
    }

    public PostEntity find(String id) {
        return posts.get(id);
    }

    public void add(PostEntity post){
        posts.put(post.getId(), post);
    }

    public void update(String id, PostEntity post){
        posts.replace(id, post);
    }

    public void remove(String id){
        posts.remove(id);
    }
}
