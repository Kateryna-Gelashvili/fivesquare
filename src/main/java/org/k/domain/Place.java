package org.k.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 1024, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "place_tag", joinColumns = @JoinColumn (name = "place",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "tag",referencedColumnName = "id"),
    foreignKey = @ForeignKey(name = "FK_PLACE_TAG_PLACE"), inverseForeignKey = @ForeignKey(name = "FK_PLACE_TAG_TAG"))
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    private Set<Checking> checkings;

    public Place() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Checking> getCheckings() {
        return checkings;
    }

    public void setCheckings(Set<Checking> checkings) {
        this.checkings = checkings;
    }

    @Override
    public String toString() {
        return name;
    }
}