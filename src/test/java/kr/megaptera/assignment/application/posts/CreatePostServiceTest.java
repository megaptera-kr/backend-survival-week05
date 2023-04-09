package kr.megaptera.assignment.application.posts;

import kr.megaptera.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreatePostServiceTest {
    private PostRepository postRepository;
    private CreatePostService createPostService;

    @BeforeEach
    void setUp() {
        postRepository = new PostRepository();
        createPostService = new CreatePostService(postRepository);
    }

    // TODO : (dh) 이 부분은 어떻게 Mock 으로 대체 할 수 있을까?
    @Test
    @DisplayName("게시물 생성")
    void create() {
        var createDto = new PostCreateDto("제목", "작성자", "내용");
        var readDto = createPostService.execute(createDto);

        assertThat(createDto.getTitle()).isEqualTo(readDto.getTitle());
        assertThat(createDto.getAuthor()).isEqualTo(readDto.getAuthor());
        assertThat(createDto.getContent()).isEqualTo(readDto.getContent());

        var entities = postRepository.findAll();
        assertThat(entities.size()).isEqualTo(1);
    }
}
