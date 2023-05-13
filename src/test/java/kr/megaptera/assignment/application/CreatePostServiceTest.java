package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostTextContent;
import kr.megaptera.assignment.models.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {
    private CreatePostService createPostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        createPostService = new CreatePostService(postRepository);
    }
    @Test
    @DisplayName("게시물 생성")
    void create() {
        PostDto create = new PostDto("제목", "작성자", "내용");
        createPostService.create(create);
        verify(postRepository).save(any(Post.class));

    }
}
