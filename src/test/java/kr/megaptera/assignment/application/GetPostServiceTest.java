package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostServiceTest {

    private PostRepository postRepository;

    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        
        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("게시물 조회")
    void detail() {
        given(postRepository.find("1"))
                .willReturn(
                        new Post(
                                PostId.of("1"),
                                PostTitle.of("공지사항입니다."),
                                PostAuthor.of("관리자"),
                                MultilineText.of("안녕하세요.\n자유게시판 이용 부탁드립니다.\n")));
        given(postRepository.find("2"))
                .willReturn(
                        new Post(
                                PostId.of("2"),
                                PostTitle.of("내가 첫 글???"),
                                PostAuthor.of("김종희"),
                                MultilineText.of("신난닷!!\n너무 좋아용~~!!")));

        PostDto postDto = getPostService.getPost("2");
        assertThat(postDto).isNotNull();
        assertThat(postDto.getAuthor()).contains("종희");
        assertThat(postDto.getContent()).contains("좋아용");

        postDto = getPostService.getPost("1");
        assertThat(postDto).isNotNull();
        assertThat(postDto.getTitle()).contains("공지사항");

        assertThrows(PostNotFound.class, () -> getPostService.getPost("3"));
    }
}
