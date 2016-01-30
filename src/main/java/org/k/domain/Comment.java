package org.k.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "comment", nullable = false, length = 2000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
    private User user;

    @Column(name = "date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "place", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENT_PLACE"))
    private Place place;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
