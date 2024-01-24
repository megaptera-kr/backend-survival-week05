package kr.megaptera.assignment.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultilineTextTest {
    @Test
    void create() {
        MultilineText multilineText = MultilineText.from("content\ncontent2\ncontent3");
        // test equals()
        MultilineText multilineText2 = MultilineText.from("content\ncontent2\ncontent3");
        assertThat(multilineText).isEqualTo(multilineText2);
        // test toString()
        assertThat(multilineText.toString()).isEqualTo("content\ncontent2\ncontent3");
    }
}
