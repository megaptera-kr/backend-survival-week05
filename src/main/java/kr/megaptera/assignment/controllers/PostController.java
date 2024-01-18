package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.Dtos.PostDto;
import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;

    public PostController(GetPostsService getPostsService, GetPostService getPostService, CreatePostService createPostService) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> list() {
        return getPostsService.list();
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public PostDto detail(@RequestParam String id) {
        return getPostService.detail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto postDto) {
        return createPostService.create(postDto);
    }
}
