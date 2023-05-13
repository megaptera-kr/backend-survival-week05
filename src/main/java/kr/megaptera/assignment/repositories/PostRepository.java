package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.PostTextContent;
import kr.megaptera.assignment.models.PostTitle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    public PostRepository() {
        this.posts = new HashMap<PostId, Post>();
        
//        this.posts.put(PostId.of("1"), new Post(PostId.of("1"), PostTitle.of("제목"), "작성자", PostTextContent.of("글 내용")));
//        this.posts.put(PostId.of("2"), new Post(PostId.of("2"), PostTitle.of("2등"), "작성자", PostTextContent.of("2빠지롱")));
    }

    Map<PostId, Post> posts;
    public List<Post> getPosts() {
        return new ArrayList<Post>(posts.values());
    }

    public void save(Post dto) {
        posts.put(dto.id(),dto);
    }

    public Post getPost(PostId postId) {
        Post post = posts.get(postId);
        if(post == null) {
            throw new PostNotFound();
        }
        return post;
    }

    public Post delete(PostId postId) {
        Post post = posts.remove(postId);
        return post;
    }

    public void update(PostId postId, Post post){
        posts.replace(postId, post);
    }
}
