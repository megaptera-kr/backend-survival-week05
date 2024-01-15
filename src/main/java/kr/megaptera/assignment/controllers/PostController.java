package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.dtos.PostUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(
            GetPostsService getPostsService,
            GetPostService getPostService,
            CreatePostService createPostService,
            UpdatePostService updatePostService,
            DeletePostService deletePostService
    ) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<PostDetailDto> list() {
        return getPostsService.listPostDtos();
    }

    @GetMapping("/{id}")
    public PostDetailDto detail(@PathVariable String id) {
        return getPostService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDetailDto create(@RequestBody PostCreateDto dto) {
        return createPostService.createPost(dto);
    }

    @PatchMapping("/{id}")
    public PostDetailDto update(@PathVariable String id, @RequestBody PostUpdateDto dto) {
        return updatePostService.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public PostDetailDto delete(@PathVariable String id) {
        return deletePostService.deletePost(id);
    }
}
