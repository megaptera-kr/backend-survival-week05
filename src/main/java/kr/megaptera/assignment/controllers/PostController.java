package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dtos.CreatePostDto;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.dtos.UpdatePostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    public PostController(GetPostsService getPostsService,
                          GetPostService getPostService,
                          CreatePostService createPostService,
                          UpdatePostService updatePostService,
                          DeletePostService deletePostService) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @GetMapping
    private List<PostDto> list() {
        return getPostsService.posts();
    }

    @GetMapping("/{id}")
    private PostDto detail(@PathVariable String id) {
        return getPostService.post(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private PostDto create(
        @RequestBody CreatePostDto createPostDto
    ) {
        return createPostService.createPost(createPostDto);
    }

    @PatchMapping("/{id}")
    private PostDto update(
        @PathVariable String id,
        @RequestBody UpdatePostDto updatePostDto
    ) {
        return updatePostService.updatePost(id, updatePostDto);
    }

    @DeleteMapping("/{id}")
    private PostDto delete(
        @PathVariable String id
    ) {
        return deletePostService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private PostNotFound postNotFound() {
        return new PostNotFound();
    }
}
