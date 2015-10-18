package org.k.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.k.domain.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    private static volatile CommentDAOImpl instance;

    private CommentDAOImpl() {
    }

    public static CommentDAOImpl getInstance(){
        if (instance == null){
            synchronized (CommentDAOImpl.class){
                if (instance == null){
                    instance = new CommentDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int addComment(Comment comment) {
        Session session = HibFactory.getFactory().openSession();
        Integer id = null;
        try {
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(comment);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return  id;
    }

    @Override
    public void updateComment(Comment comment) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(comment);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public void deleteComment(Comment comment) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(comment);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Comment> getAllComments() {
        Session session = HibFactory.getFactory().openSession();
        List<Comment> list = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            list = (List<Comment>) session.createQuery("from Comment ").list();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    @Override
    public Comment getCommentById(int id) {
        Session session = HibFactory.getFactory().openSession();
        Comment comment = null;
        try {
            Transaction transaction = session.beginTransaction();
            comment = (Comment) session.get(Comment.class,id);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return comment;
    }
}
