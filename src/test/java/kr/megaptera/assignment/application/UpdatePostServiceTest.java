package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.dto.PostUpdateDto;
import kr.megaptera.assignment.domains.model.MultilineText;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostAuthor;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.domains.model.PostTitle;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdatePostServiceTest {

    private PostRepository postRepository;

    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        given(postRepository.find("2"))
                .willReturn(
                        new Post(
                                PostId.of("2"),
                                PostTitle.of("내가 첫 글???"),
                                PostAuthor.of("김종희"),
                                MultilineText.of("신난닷!!\n너무 좋아용~~!!")));

        PostUpdateDto postUpdateDto = new PostUpdateDto("새로 글 쓰기", "새로 글을\n쓰는 중입니다.\n\n좋아용");
        
        PostDto postDto = updatePostService.updatePost("2", postUpdateDto);

        verify(postRepository).save(any(Post.class));
        assertThat(postDto).isNotNull();
        assertThat(postDto.getTitle()).contains("새로");
        assertThrows(PostNotFound.class, () -> updatePostService.updatePost("3", postUpdateDto));
    }
}
