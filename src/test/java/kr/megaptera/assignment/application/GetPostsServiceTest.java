package kr.megaptera.assignment.application;

import kr.megaptera.assignment.domains.dto.PostDto;
import kr.megaptera.assignment.domains.model.MultilineText;
import kr.megaptera.assignment.domains.model.Post;
import kr.megaptera.assignment.domains.model.PostAuthor;
import kr.megaptera.assignment.domains.model.PostId;
import kr.megaptera.assignment.domains.model.PostTitle;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPostsServiceTest {

    private PostRepository postRepository;

    private GetPostsService getPostsService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void list() {
        given(postRepository.findAll())
                .willReturn(
                        List.of(
                                new Post(
                                        PostId.of("1"),
                                        PostTitle.of("공지사항입니다."),
                                        PostAuthor.of("관리자"),
                                        MultilineText.of("안녕하세요.\n자유게시판 이용 부탁드립니다.\n")),
                                new Post(
                                        PostId.of("2"),
                                        PostTitle.of("내가 첫 글???"),
                                        PostAuthor.of("김종희"),
                                        MultilineText.of("신난닷!!\n너무 좋아용~~!!"))));

        List<PostDto> postDtos = getPostsService.getPosts();

        assertThat(postDtos.size()).isEqualTo(2);
        assertThat(postDtos.get(0).getTitle()).contains("공지사항");
        assertThat(postDtos.get(1).getContent()).contains("!!");
    }
}
