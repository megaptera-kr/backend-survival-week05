package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreatePostService;
import kr.megaptera.assignment.application.DeletePostService;
import kr.megaptera.assignment.application.GetPostService;
import kr.megaptera.assignment.application.GetPostsService;
import kr.megaptera.assignment.application.UpdatePostService;
import kr.megaptera.assignment.dto.PostCreateDto;
import kr.megaptera.assignment.dto.PostDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private CreatePostService createPostService;
    private GetPostsService getPostsService;
    private GetPostService getPostService;
    private UpdatePostService updatePostService;
    private DeletePostService deletePostService;

    public PostController(){
        this.createPostService = new CreatePostService();
        this.getPostsService = new GetPostsService();
        this.getPostService = new GetPostService();
        this.updatePostService = new UpdatePostService();
        this.deletePostService = new DeletePostService();
    }

    @PostMapping
    public PostDto create(@RequestBody PostCreateDto postCreateDto){
        PostDto created = createPostService.createPost(postCreateDto);
        return created;
    }

}
