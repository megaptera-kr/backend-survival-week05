package kr.megaptera.assignment.controllers;


import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;


    public PostController(GetPostService getPostService,
                          GetPostsService getPostsService,
                          CreatePostService createPostService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService) {
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDto> list() {
        return getPostsService.list();
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {

        return getPostService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostCreateDto postCreateDto) {

        return createPostService.create(postCreateDto);
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable String id,
                          @RequestBody PostUpdateDto postUpdateDto) {
        return updatePostService.update(id, postUpdateDto);
    }

    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable String id) {

        return deletePostService.delete(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String postNotFound() {
        return "Not found post.";
    }
}