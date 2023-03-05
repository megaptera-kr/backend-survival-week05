package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.MultilineText;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeletePostServiceTest {

    private PostRepository postRepository = new PostRepository();

    @Test
    @DisplayName("게시물 삭제")
    void delete() {

        Post test1 = new Post(
                new PostId("01TEST"),
                "테스트제목",
                "테스트작성자",
                new MultilineText("테스트내용")
        );

        postRepository.save(test1);

        Post test2 = new Post(
                new PostId("02TEST"),
                "테스트제목",
                "테스트작성자",
                new MultilineText("테스트내용")
        );

        postRepository.save(test2);

        postRepository.delete(new PostId("01TEST"));

        List<Post> posts = postRepository.findAll();

        assertThat(posts.size()).isEqualTo(1);

    }
}
