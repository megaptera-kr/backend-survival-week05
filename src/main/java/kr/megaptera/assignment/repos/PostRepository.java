package kr.megaptera.assignment.repos;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private List<Post> posts = new ArrayList<>();

    public List<PostDto> getPosts() {
        return posts.stream()
                .map(post ->{
                    return new PostDto(post);
                }).collect(Collectors.toList());
    }

    public PostDto getPost(String id) {
        return new PostDto(posts.stream()
                .filter(post -> post.id().equals(id))
                .findFirst()
                .get());
    }

    public PostDto createPost(PostDto post) {
        Post createPost = new Post(post.getTitle(), post.getAuthor(), post.getContent());
        posts.add(createPost);
        return new PostDto(createPost);
    }

    public void updatePost(PostDto dto) {
        posts = posts.stream().map(post -> {
            if(post.id().equals(dto.getId())) return new Post(dto);
            else return post;
        }).collect(Collectors.toList());
    }

    public void deletePost(PostDto dto) {
        posts = posts.stream()
                .filter(post -> !post.id().equals(dto.getId()))
                .collect(Collectors.toList());
    }
}
