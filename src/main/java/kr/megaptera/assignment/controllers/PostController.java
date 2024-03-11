package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private CreatePostService createPostService;
    @Autowired
    private GetPostsService getPostsService;
    @Autowired
    private GetPostService getPostService;
    @Autowired
    private UpdatePostService updatePostService;
    @Autowired
    private DeletePostService deletePostService;
    private final ObjectMapper objectMapper;

    public PostController() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping
    public List<PostDto> getPosts(){
        return getPostsService.getPosts();
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable("id") String id){
        return getPostService.getPost(id);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto post){
        return createPostService.createPost(post);
    }

    @PatchMapping("/{id}")
    public PostDto updatePost(@PathVariable("id") String id, @RequestBody PostDto post){
        post.setId(id);
        return updatePostService.updatePost(post);
    }

    @DeleteMapping("/{id}")
    public PostDto deletePost(@PathVariable("id") String id){
        PostDto dto = new PostDto(id);
        return deletePostService.deletePost(dto);
    }
}
