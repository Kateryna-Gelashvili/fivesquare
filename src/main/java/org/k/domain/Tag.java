package org.k.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the tag object
 */
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "place_tag", joinColumns = @JoinColumn (name = "tag",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "place",referencedColumnName = "id"),
    foreignKey = @ForeignKey(name = "FK_PLACE_TAG_TAG"), inverseForeignKey = @ForeignKey(name = "FK_PLACE_TAG_PLACE"))
    private Set<Place> places = new HashSet<Place>();

    public Tag() {
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

    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }
}
