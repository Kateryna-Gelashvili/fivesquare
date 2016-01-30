package org.k.domain;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "checking")
public class Checking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false, foreignKey = @ForeignKey(name = "FK_CHEKING_USER"))
    private User user;


    @ManyToOne
    @JoinColumn(name = "place", nullable = false, foreignKey = @ForeignKey(name = "FK_CHECKING_PLACE"))
    private Place place;

    @Column(name = "date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

//    @PrePersist
//    protected void onCreate(){
//        date = new Date();
//    }

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
