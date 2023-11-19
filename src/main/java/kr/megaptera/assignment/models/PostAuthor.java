package kr.megaptera.assignment.models;

public class PostAuthor extends Author{

    public PostAuthor(String author) {
        super(author);
    }


    public static PostAuthor of(String author) {
        return new PostAuthor(author);
    }
}
