package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.AssignmentApplication;
import kr.megaptera.assignment.dtos.posts.PostCreateDto;
import kr.megaptera.assignment.dtos.posts.PostReadDto;
import kr.megaptera.assignment.dtos.posts.PostUpdateDto;
import kr.megaptera.assignment.entities.PostEntity;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(
        classes = AssignmentApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("GET /posts")
    void list() throws JsonProcessingException {
        var url = "http://localhost:" + port + "/posts";
        var body = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        PostReadDto[] posts = objectMapper.readValue(body, PostReadDto[].class);

        assertThat(body).isNotEmpty();
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws JsonProcessingException {
        postRepository.add(new PostEntity("001POST", "Title", "Author", "Content"));

        var url = "http://localhost:" + port + "/posts/001POST";
        var body = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        PostReadDto post = objectMapper.readValue(body, PostReadDto.class);

        assertThat(post.getId()).isEqualTo("001POST");
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() {
        var url = "http://localhost:" + port + "/posts/001POST";
        var responseEntity = restTemplate.getForEntity(url, String.class);
        var statusCode = responseEntity.getStatusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("POST /posts")
    void create() {
        var url = "http://localhost:" + port + "/posts";
        var postCreateDto = new PostCreateDto("title", "author", "content");
        var postReadDto = restTemplate.postForObject(url, postCreateDto, PostReadDto.class);

        assertThat(postReadDto.getTitle()).isEqualTo(postCreateDto.getTitle());
        assertThat(postReadDto.getAuthor()).isEqualTo(postCreateDto.getAuthor());
        assertThat(postReadDto.getContent()).isEqualTo(postCreateDto.getContent());
    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() {

        postRepository.add(new PostEntity("002POST", "Title", "Author", "Content"));

        var url = "http://localhost:" + port + "/posts/002POST";
        var postUpdateDto = new PostUpdateDto("Updated Title", "Updated Content");
        // Patch == Put
        restTemplate.put(url, postUpdateDto, PostReadDto.class);

        var post = restTemplate.getForObject(url, PostReadDto.class);
        assertThat(post.getId()).isEqualTo("002POST");
    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() {
        postRepository.add(new PostEntity("003POST", "Title", "Author", "Content"));

        var url = "http://localhost:" + port + "/posts/002POST";
        restTemplate.delete(url);
    }
}
