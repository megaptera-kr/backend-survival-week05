package kr.megaptera.assignment.model;

import kr.megaptera.assignment.dto.PostCreateDto;

import java.util.UUID;

public class Post {
    private PostId id;
    private SingleLineText title;
    private SingleLineText author;
    private MultiLineText comment;

    public Post(PostCreateDto postCreateDto){
        this.id = new PostId(UUID.randomUUID().toString());
        this.title = new SingleLineText(postCreateDto.getTitle());
        this.author = new SingleLineText(postCreateDto.getAuthor());
        this.comment = new MultiLineText(postCreateDto.getComment());
    }

    public PostId id(){
        return id;
    }

    public SingleLineText title(){
        return title;
    }

    public SingleLineText author(){
        return author;
    }

    public MultiLineText comment(){
        return comment;
    }

}