package org.k.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.k.domain.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {
    private static volatile TagDAOImpl instance;

    private TagDAOImpl() {
    }

    public static TagDAOImpl getInstance(){
        if (instance == null){
            synchronized (TagDAOImpl.class){
                if (instance == null){
                    instance = new TagDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int addTag(Tag tag) {
        Session session = HibFactory.getFactory().openSession();
        Integer id = null;
        try {
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(tag);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return id;
    }

    @Override
    public void updateTag(Tag tag) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(tag);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public void deleteTag(Tag tag) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(tag);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Tag> getAllTags() {
        Session session = HibFactory.getFactory().openSession();
        List<Tag> list = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            list = (List<Tag>) session.createQuery("from Tag ").list();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    @Override
    public Tag getTagById(int id) {
        Session session = HibFactory.getFactory().openSession();
        Tag tag = null;
        try {
            Transaction transaction = session.beginTransaction();
            tag = (Tag) session.get(Tag.class,id);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return tag;
    }
}
