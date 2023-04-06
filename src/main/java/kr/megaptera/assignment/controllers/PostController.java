package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.post.*;
import kr.megaptera.assignment.dtos.post.*;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {
    private CreatePostService createPostService;
    private DeletePostService deletePostService;
    private GetPostService getPostService;
    private GetPostsService getPostsService;
    private UpdatePostService updatePostService;

    public PostController(CreatePostService createPostService,
                          DeletePostService deletePostService,
                          GetPostService getPostService,
                          GetPostsService getPostsService,
                          UpdatePostService updatePostService) {
        this.createPostService = createPostService;
        this.deletePostService = deletePostService;
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
        this.updatePostService = updatePostService;
    }

    @GetMapping
    public List<PostDto> list() {
        List<PostDto> postDtos = getPostsService.getPostDtos();
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(
            @PathVariable String id
    ) {
        PostDto postDto = getPostService.getPostDto(id);
        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(
            @RequestBody PostCreateDto postCreateDto
    ) {
        PostDto postDto = createPostService.create(postCreateDto);
        return postDto;
    }

    @PatchMapping("/{id}")
    public PostDto update(
            @PathVariable String id,
            @RequestBody PostUpdateDto postUpdateDto
    ) {
        PostDto updated = updatePostService.update(id, postUpdateDto);
        return updated;
    }

    @DeleteMapping("/{id}")
    public PostDto delete(
            @PathVariable String id
    ) {
        PostDto deleted = deletePostService.delete(id);
        return deleted;
    }

    @ExceptionHandler(postNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }
}
