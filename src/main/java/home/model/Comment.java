package home.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @ManyToOne(targetEntity = Blog.class)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public Comment(String content) {
        this.content = content;
    }

    public Comment() {
    }


    public Comment(Integer id, String content, Blog blog) {
        this.id = id;
        this.content = content;
        this.blog = blog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
