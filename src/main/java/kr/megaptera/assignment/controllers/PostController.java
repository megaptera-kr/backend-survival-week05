package kr.megaptera.assignment.controllers;

import jakarta.websocket.server.PathParam;
import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dto.PostCreateDto;
import kr.megaptera.assignment.dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private CreatePostService createPostService;
    private GetPostsService getPostsService;
    private GetPostService getPostService;
    private UpdatePostService updatePostService;
    private DeletePostService deletePostService;

    public PostController(CreatePostService createPostService,
                          GetPostsService getPostsService,
                          GetPostService getPostService
//                          UpdatePostService updatePostService,
//                          DeletePostService deletePostService
    ) {
        this.createPostService = createPostService;
        this.getPostsService = getPostsService;
        this.getPostService = getPostService;
//        this.updatePostService = updatePostService;
//        this.deletePostService = deletePostService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostCreateDto postCreateDto){
        PostDto created = createPostService.createPost(postCreateDto);
        return created;
    }

    @GetMapping
    public List<PostDto> getLists(){
        List<PostDto> postDtos = getPostsService.getPostDtos();
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto getDetail(@PathVariable String id){
        PostDto postDto = getPostService.getPostDto(id);
        return postDto;
    }

}
