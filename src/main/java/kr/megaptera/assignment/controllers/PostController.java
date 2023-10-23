package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.posts.*;
import kr.megaptera.assignment.dtos.posts.CreatePostDto;
import kr.megaptera.assignment.dtos.posts.PostDto;
import kr.megaptera.assignment.dtos.posts.UpdatePostDto;
import kr.megaptera.assignment.exceptions.posts.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final CreatePostService createPostService;
    private final DeletePostService deletePostService;
    private final GetPostService getPostService;
    private final GetPostsService getPostsService;
    private final UpdatePostService updatePostService;

    public PostController(CreatePostService createPostService, DeletePostService deletePostService, GetPostService getPostService, GetPostsService getPostsService, UpdatePostService updatePostService) {
        this.createPostService = createPostService;
        this.deletePostService = deletePostService;
        this.getPostService = getPostService;
        this.getPostsService = getPostsService;
        this.updatePostService = updatePostService;
    }

    // 게시글 조회
    @GetMapping("")
    public List<PostDto> getPosts() {
        return this.getPostsService.findAll();
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public PostDto getPost(@PathVariable String postId) {
        return this.getPostService.find(postId);
    }

    // 게시글 작성
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto postPost(@RequestBody CreatePostDto createPostDto) {
        return this.createPostService.create(createPostDto);
    }

    // 게시글 수정
    @PatchMapping("/{postId}")
    public PostDto patchPost(@PathVariable String postId, @RequestBody UpdatePostDto updatePostDto) {
        return this.updatePostService.update(postId, updatePostDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public PostDto deletePost(@PathVariable String postId) {
        return this.deletePostService.delete(postId);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다";
    }
}
