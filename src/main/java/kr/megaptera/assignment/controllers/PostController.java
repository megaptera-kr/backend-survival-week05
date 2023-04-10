package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.*;
import kr.megaptera.assignment.exceptions.*;
import org.springframework.aop.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

    private final GetPostsService getPostsService;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final DeletePostService deletePostService;
    private final UpdatePostService updatePostService;

    public PostController(GetPostsService getPostsService, GetPostService getPostService, CreatePostService createPostService, DeletePostService deletePostService, UpdatePostService updatePostService) {
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.deletePostService = deletePostService;
        this.updatePostService = updatePostService;
    }


    @GetMapping
    public List<PostDto> list(){
        List<PostDto> postDtos = getPostsService.getPostDtos();

        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id){
        PostDto postDto = getPostService.getPostDto(id);

        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostCreateDto postcreateDto){
        PostDto created = createPostService.createPost(postcreateDto);

        return created;
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable String id,
                          @RequestBody PostUpdateDto postUpdateDto)
    {
        PostDto updated = updatePostService.update(id,postUpdateDto);

        return updated;
    }

    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable String id)
    {
        PostDto deleted = deletePostService.delete(id);
        return deleted;
    }
    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }

}
