package ru.kpfu.it.instaphoto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Alexander Tchitchigin
 *         Date: 3/28/14
 *         Time: 12:52 PM
 */
@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="usr_id")
    private Long id;

    @Column(name="usr_login",nullable = false,length = 30)
    @NotNull
    @NotEmpty
    @Size(min = 5,max = 30,message = "size must be between 5 and 30")
    private String login;

    @Column(name = "usr_email",nullable = false,length = 50)
    @NotNull
    @NotEmpty
    @Size(min = 5,max = 30)
    @Email
    private String email;

    @JsonIgnore
    @Transient
    private String password;

    @JsonIgnore
    @Lob
    @Column(name = "usr_hash",nullable = false)
    private byte[] hash;

    @JsonIgnore
    @Lob
    @Column(name="usr_salt",nullable = false)
    private byte [] salt;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Photo> photos = new ArrayList<Photo>();

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
