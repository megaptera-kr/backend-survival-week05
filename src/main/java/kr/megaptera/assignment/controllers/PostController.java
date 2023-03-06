package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.controllers.dtos.PostCreateDto;
import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.controllers.dtos.PostUpdateDto;
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

    private PostController(GetPostService getPostService,
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
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> findAll(){
        return getPostsService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto findById(@PathVariable String id){
        return getPostService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostCreateDto createDto){
        return createPostService.createPost(createDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@PathVariable String id, @RequestBody PostUpdateDto updateDto){
        return updatePostService.updatePost(id, updateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto deletePost(@PathVariable String id){
        return deletePostService.deletePost(id);
    }
}
