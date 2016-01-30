package org.k.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "UK_USERNAME", columnNames = "name"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Checking> checkings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Comment> comments;

//    @Column(name = "created_date", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    private Date createdDate;
//
//    @Column(name = "updated_date", nullable = true)
//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
//    private Date updatedDate;

//    @PrePersist
//    protected void onCreate(){
//        createdDate = new Date();
//        updatedDate = new Date();
//    }
//
//    @PreUpdate
//    protected void onUpdate(){
//        updatedDate = new Date();
//    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Checking> getCheckings() {
        return checkings;
    }

    public void setCheckings(Set<Checking> checkings) {
        this.checkings = checkings;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return name;
    }
}
