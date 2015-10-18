package org.k.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checking")
public class Checking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place")
    private Place place;

    @Column(name = "date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    public Checking() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Checking at " + place + " by " + user + " " + date.toString();
    }
}
