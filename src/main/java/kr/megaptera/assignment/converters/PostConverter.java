package kr.megaptera.assignment.converters;

import kr.megaptera.assignment.dtos.PostCreateDto;
import kr.megaptera.assignment.dtos.PostDetailDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.models.SingleLineText;

public class PostConverter {
    public static Post toPost(PostDetailDto dto) {
        return Post.of(
                PostId.of(dto.getId()),
                SingleLineText.of(dto.getTitle()),
                SingleLineText.of(dto.getAuthor()),
                MultiLineText.of(dto.getContent())
        );
    }

    public static Post toPost(PostCreateDto dto) {
        return Post.createNew(
                SingleLineText.of(dto.getTitle()),
                SingleLineText.of(dto.getAuthor()),
                MultiLineText.of(dto.getContent())
        );
    }

    public static PostDetailDto toPostDetailDto(Post post) {
        if (post == null) {
            return null;
        }
        return new PostDetailDto(
                post.getId().id(),
                post.getTitle().text(),
                post.getAuthor().text(),
                post.getContent().toString()
        );
    }
}
