package dev.c4c.data.link;

import java.io.Serializable;
import java.util.Objects;

public class LinkId implements Serializable {
    private String namespace;
    private String shortCode;

    public LinkId() {
    }

    public LinkId(String namespace, String shortCode) {
        this.namespace = namespace;
        this.shortCode = shortCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkId linkId = (LinkId) o;
        return Objects.equals(namespace, linkId.namespace) &&
            Objects.equals(shortCode, linkId.shortCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, shortCode);
    }
}
