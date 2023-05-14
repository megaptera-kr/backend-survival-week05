package kr.megaptera.assignment.domains.model;

import kr.megaptera.assignment.domains.dto.PostCreateDto;
import kr.megaptera.assignment.domains.dto.PostUpdateDto;

public class Post {

    private PostId id;

    private PostTitle title;

    private PostAuthor author;

    private MultilineText content;

    public PostId id() {
        return id;
    }

    public PostTitle title() {
        return title;
    }

    public PostAuthor author() {
        return author;
    }

    public MultilineText content() {
        return content;
    }

    public Post() {
    }

    public Post(PostId postId, PostTitle title, PostAuthor author, MultilineText content) {
        this.id = postId;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Post(PostCreateDto postCreateDto) {
        this.id = new PostId();
        this.author = PostAuthor.of(postCreateDto.getAuthor());
        this.title = PostTitle.of(postCreateDto.getTitle());
        this.content = MultilineText.of(postCreateDto.getContent());
    }

    public void updatePost(PostUpdateDto postUpdateDto) {
        this.title = PostTitle.of(postUpdateDto.getTitle());
        this.content = MultilineText.of(postUpdateDto.getContent());
    }
}
