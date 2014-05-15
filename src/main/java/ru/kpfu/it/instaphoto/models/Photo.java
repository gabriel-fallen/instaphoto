package ru.kpfu.it.instaphoto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 1:02 PM
 */
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ph_id")
    private Long id;

    @Lob
    @Column(name = "ph_data",nullable = false)
    private byte[] data;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "ph_date",nullable = false)
    private Date date;

    @JoinColumn(name = "ph_author", nullable = false)
    @ManyToOne
    private User author;

    @JsonIgnore
    @OneToMany(mappedBy = "photo", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Photo(){
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

