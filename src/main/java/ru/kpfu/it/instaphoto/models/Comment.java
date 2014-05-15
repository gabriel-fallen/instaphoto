package ru.kpfu.it.instaphoto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 1:10 PM
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "com_id")
    private Long id;

    @Column(name = "com_text")
    private String text;

    @JoinColumn(name = "com_author")
    @ManyToOne
    private User author;

    @JsonIgnore
    @JoinColumn(name = "com_photo")
    @ManyToOne
    private Photo photo;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "com_date",nullable = false)
    private Date date;

    public Comment() {
        date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
