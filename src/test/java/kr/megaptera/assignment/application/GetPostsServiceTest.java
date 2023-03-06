package kr.megaptera.assignment.application;

import kr.megaptera.assignment.controllers.dtos.PostDto;
import kr.megaptera.assignment.models.MultiLineText;
import kr.megaptera.assignment.models.Post;
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
    void setUp(){
        postRepository = mock(PostRepository.class);
        getPostsService = new GetPostsService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        // given
        given(postRepository.findAll()).willReturn(
                List.of(
                        new Post("a1", "b1", new MultiLineText("c1")),
                        new Post("a2", "b2", new MultiLineText("c2")),
                        new Post("a3", "b3", new MultiLineText("c3")),
                        new Post("a4", "b4", new MultiLineText("c4"))
                )
        );

        // when
        List<PostDto> all = getPostsService.findAll();

        // then
        assertThat(all).hasSize(4);
        assertThat(all.get(0).getTitle()).isEqualTo("a1");
        assertThat(all.get(all.size()-1).getAuthor()).isEqualTo("b4");
    }
}
