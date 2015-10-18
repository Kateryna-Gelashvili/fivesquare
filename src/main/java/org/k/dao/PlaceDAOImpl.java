package org.k.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.k.domain.Checking;
import org.k.domain.Comment;
import org.k.domain.Place;
import org.k.domain.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaceDAOImpl implements PlaceDAO {
    private static volatile PlaceDAOImpl instance;

    private PlaceDAOImpl() {
    }

    public static PlaceDAOImpl getInstance(){
        if (instance == null){
            synchronized (PlaceDAOImpl.class){
                if (instance == null){
                    instance = new PlaceDAOImpl();
                }
            }
        }
        return instance;
    }

    public int addPlace(Place place) {
        Session session = HibFactory.getFactory().openSession();
        Transaction transaction = null;
        Integer id = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer)session.save(place);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void updatePlace(Place place) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(place);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deletePlace(Place place) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(place);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Place> getAllPlaces() {
        Session session = HibFactory.getFactory().openSession();
        List<Place> list = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            list =(List<Place>) session.createQuery("from Place").list();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    public Place getPlaceByID(int id) {
        Session session = HibFactory.getFactory().openSession();
        Place place = null;
        try {
            Transaction transaction = session.beginTransaction();
            place = (Place) session.get(Place.class,id);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return place;
    }

    public List<Place> getPlacesByName(String name) {
        Session session = HibFactory.getFactory().openSession();
        List<Place> list = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            list = (List<Place>) session.createQuery("from Place where name like '%" + name + "%'").list();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    public Set<Tag> getTags(Place place) {
        Session session = HibFactory.getFactory().openSession();
        Set<Tag> set = new HashSet<>();
        try {
            Transaction transaction = session.beginTransaction();
            Place p =(Place) session.get(Place.class,place.getId());
            set = (Set<Tag>) p.getTags();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return set;
    }

    public Set<Checking> getCheckings(Place place) {
        Session session = HibFactory.getFactory().openSession();
        Set<Checking> set = new HashSet<>();
        try {
            Transaction transaction = session.beginTransaction();
            Place  p =(Place) session.get(Place.class,place.getId());
            set = (Set<Checking>) p.getCheckings();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return set;
    }

    @Override
    public Set<Comment> getComments(Place place) {
        Session session = HibFactory.getFactory().openSession();
        Set<Comment> set = new HashSet<>();
        try {
            Transaction transaction = session.beginTransaction();
            Place p = (Place) session.get(Place.class, place.getId());
            set = (Set<Comment>) p.getComments();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return set;
    }
}
