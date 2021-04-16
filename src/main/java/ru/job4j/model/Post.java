package ru.job4j.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private String mark;

    private String bodyMark;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Post(String description, String mark, String bodyMark, String status) {
        created = new Date(System.currentTimeMillis());
        this.description = description;
        this.mark = mark;
        this.bodyMark = bodyMark;
        this.status = status;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBodyMark() {
        return bodyMark;
    }

    public void setBodyMark(String bodyMark) {
        this.bodyMark = bodyMark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", mark='" + mark + '\''
                + ", bodyMark='" + bodyMark + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
