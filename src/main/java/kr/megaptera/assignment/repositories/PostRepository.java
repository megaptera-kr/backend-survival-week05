package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
  Map<String, PostEntity> posts;

  public PostRepository() {
    this.posts = new HashMap<>();
  }

  public List<PostEntity> findAll() {
    return new ArrayList<>(posts.values());
  }

  public PostEntity find(String id) {
    PostEntity post = posts.get(id);

    if (post == null) {
      throw new PostNotFound();
    }

    return post;
  }

  public void save(PostEntity post) {
    posts.put(post.getId(), post);
  }

  public void delete(String id) {
    posts.remove(id);
  }
}
