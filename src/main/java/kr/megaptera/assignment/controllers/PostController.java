package kr.megaptera.assignment.controllers;

import java.util.List;
import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.CreatePostRequest;
import kr.megaptera.assignment.dtos.CreatePostResponse;
import kr.megaptera.assignment.dtos.DeletePostResponse;
import kr.megaptera.assignment.dtos.GetPostListResponse;
import kr.megaptera.assignment.dtos.GetPostResponse;
import kr.megaptera.assignment.dtos.UpdatePostRequest;
import kr.megaptera.assignment.dtos.UpdatePostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8000")
@RestController
@RequestMapping("/posts")
public class PostController {
    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;


    public PostController(GetPostsService getPostsService, GetPostService getPostService,
            CreatePostService createPostService, UpdatePostService updatePostService,
            DeletePostService deletePostService) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    public List<GetPostListResponse> getPostList() {
        return getPostsService.getPosts();
    }

    @GetMapping("/{id}")
    public GetPostResponse getPost(@PathVariable Long id) {
        return getPostService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePostResponse createPost(@RequestBody CreatePostRequest request) {
        return createPostService.createPost(request.toPost());
    }

    @PatchMapping("/{id}")
    public UpdatePostResponse updatePost(@PathVariable Long id,
            @RequestBody UpdatePostRequest request) {
        return updatePostService.updatePost(id, request);
    }

    @DeleteMapping("/{id}")
    public DeletePostResponse deletePost(@PathVariable Long id) {
        return deletePostService.deletePost(id);
    }
}
