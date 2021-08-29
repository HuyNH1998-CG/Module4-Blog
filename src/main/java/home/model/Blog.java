package home.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String name;
    private Date date;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER)
    private List<Comment> comments;
    private int likes;
    private int views;

    public Blog() {
    }

    public Blog(String author, String name, Date date, String content, Category category) {
        this.author = author;
        this.name = name;
        this.date = date;
        this.content = content;
        this.category = category;
    }

    public Blog(String author, String name, Date date, String content, Category category, List<Comment> comments) {
        this.author = author;
        this.name = name;
        this.date = date;
        this.content = content;
        this.category = category;
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
