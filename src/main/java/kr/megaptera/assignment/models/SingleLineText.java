package kr.megaptera.assignment.models;

public record SingleLineText(String text) {
    public static SingleLineText of(String text) {
        return new SingleLineText(text);
    }
}
