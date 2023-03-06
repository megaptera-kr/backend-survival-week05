package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetPostServiceTest {

    private PostRepository postRepository = new PostRepository();

    @Test
    @DisplayName("게시물 조회")
    void detail() {

        Post test1 = new Post(
                new PostId("01TEST"),
                "테스트제목",
                "테스트작성자",
                new MultilineText("테스트내용")
        );

        postRepository.save(test1);

        Post post1 = postRepository.find(new PostId("01TEST"));

        assertThat(post1.content().toString()).isEqualTo("테스트내용");

    }
}
