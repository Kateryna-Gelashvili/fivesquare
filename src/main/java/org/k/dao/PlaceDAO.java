package org.k.dao;

import org.k.domain.*;

import java.util.List;
import java.util.Set;

public interface PlaceDAO {
    public int addPlace(Place place);
    public void updatePlace(Place place);
    public void deletePlace(Place place);
    public List<Place> getAllPlaces();
    public Place getPlaceByID(int id);
    public List<Place> getPlacesByName(String name);
    public Set<Tag> getTags(Place place);
    public Set<Checking> getCheckings(Place place);
    public Set<Comment> getComments(Place place);
}
