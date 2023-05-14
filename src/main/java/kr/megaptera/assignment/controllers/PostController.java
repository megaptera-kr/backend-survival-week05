package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    CreatePostService createPostService;

    @Autowired
    DeletePostService deletePostService;

    @Autowired
    GetPostsService getPostsService;

    @Autowired
    GetPostService getPostService;

    @Autowired
    UpdatePostService updatePostService;

    @CrossOrigin(origins = "*")
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> list(){
        return getPostsService.findPostList();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto detail(@PathVariable String id) {
        return getPostService.findPost(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostCreateDto postCreateDto) {
        return createPostService.createPost(postCreateDto);
    }

    @CrossOrigin(origins = "*")

    @PatchMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody PostUpdateDto postUpdateDto) {
        updatePostService.updatePost(id,postUpdateDto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deletePostService.deletePost(id);
    }
}
