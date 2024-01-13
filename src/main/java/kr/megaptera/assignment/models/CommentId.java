package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.assignment.errors.CommentIdCreationError;

import java.util.Objects;

public record CommentId(
        String id
) implements Comparable<CommentId> {
    public CommentId {
        if (!
                Tsid.isValid(Objects.requireNonNull(id))) {
            throw new CommentIdCreationError();
        }
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    @Override
    public int compareTo(CommentId o) {
        return this.id().compareTo(o.id());
    }
}
