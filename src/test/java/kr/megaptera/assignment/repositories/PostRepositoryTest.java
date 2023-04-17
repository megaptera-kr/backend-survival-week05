package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.entities.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostRepositoryTest {
    private PostRepository repository;
    private List<PostEntity> initEntities;

    @BeforeEach
    void beforeEach() {
        repository = new PostRepository();

        initEntities = List.of(
                new PostEntity("1", "title1", "author1", "content1"),
                new PostEntity("2", "title2", "author2", "content2")
        );

        initEntities.stream().forEach(entity -> repository.add(entity));
    }

    @Test
    @DisplayName("게시물 DB 에서 가져오기")
    void findAll() {
        var entities = repository.findAll();
        var expectCount = initEntities.size();

        assertEquals(expectCount, entities.size());

        var expectEntity1 = initEntities.get(0);
        var actualEntity1 = entities.get(0);

        assertEquals(expectEntity1.getId(), actualEntity1.getId());
        assertEquals(expectEntity1.getTitle(), actualEntity1.getTitle());
        assertEquals(expectEntity1.getAuthor(), actualEntity1.getAuthor());
        assertEquals(expectEntity1.getContent(), actualEntity1.getContent());

        var expectEntity2 = initEntities.get(1);
        var actualEntity2 = entities.get(1);;

        assertEquals(expectEntity2.getId(), actualEntity2.getId());
        assertEquals(expectEntity2.getTitle(), actualEntity2.getTitle());
        assertEquals(expectEntity2.getAuthor(), actualEntity2.getAuthor());
        assertEquals(expectEntity2.getContent(), actualEntity2.getContent());
    }

    @Test
    void find() {
        var expectEntity = initEntities.get(0);
        var actualEntity = repository.find("1");

        assertEquals(expectEntity.getId(), actualEntity.getId());
        assertEquals(expectEntity.getTitle(), actualEntity.getTitle());
        assertEquals(expectEntity.getAuthor(), actualEntity.getAuthor());
        assertEquals(expectEntity.getContent(), actualEntity.getContent());
    }

    @Test
    void add() {
        var expectEntity = new PostEntity("3", "title3", "author3", "content3");

        repository.add(expectEntity);

        var actualEntity = repository.find("3");

        assertEquals(expectEntity.getId(), actualEntity.getId());
        assertEquals(expectEntity.getTitle(), actualEntity.getTitle());
        assertEquals(expectEntity.getAuthor(), actualEntity.getAuthor());
        assertEquals(expectEntity.getContent(), actualEntity.getContent());
    }

    @Test
    void update() {
        var expectEntity = repository.find("1");

        expectEntity.setTitle("author-updated");
        expectEntity.setAuthor("author-updated");
        expectEntity.setContent("author-updated");

        repository.update(expectEntity.getId(), expectEntity);
        var actualEntity = repository.find("1");

        assertEquals(expectEntity.getId(), actualEntity.getId());
        assertEquals(expectEntity.getTitle(), actualEntity.getTitle());
        assertEquals(expectEntity.getAuthor(), actualEntity.getAuthor());
        assertEquals(expectEntity.getContent(), actualEntity.getContent());
    }

    @Test
    void remove() {
        repository.remove("1");
        repository.remove("2");

        var entities = repository.findAll();
        assertEquals(0, entities.size());
    }
}