package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidCreator;
import kr.megaptera.assignment.errors.PostIdCreationError;

import java.util.Objects;

public record PostId(String id) implements Comparable<PostId> {
    public PostId {
        if (!Tsid.isValid(Objects.requireNonNull(id))) {
            throw new PostIdCreationError();
        }
    }

    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    public static PostId of(String id) {
        return new PostId(Tsid.from(id).toString());
    }

    @Override
    public int compareTo(PostId o) {
        return this.id.compareTo(o.id);
    }
}
