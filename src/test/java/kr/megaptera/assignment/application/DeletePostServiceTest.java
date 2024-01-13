package kr.megaptera.assignment.application;

import kr.megaptera.assignment.models.*;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DeletePostServiceTest {
    @SpyBean
    private PostRepository postRepository;

    @Autowired
    private DeletePostService deletePostService;

    private final List<PostId> postIdList = IntStream.range(0, 10).mapToObj(i -> PostId.generate()).toList();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < postIdList.size(); i++) {
            PostId postId = postIdList.get(i);
            Post post = Post.of(
                    postId,
                    SingleLineText.of("the title " + i),
                    SingleLineText.of("the author " + i),
                    MultiLineText.of("the content " + i + " " + postId.id())
            );
            postRepository.save(post);
        }
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        deletePostService.deletePost(postIdList.get(0).id());
        assertThat(postRepository.findAll()).hasSize(9);
    }
}
