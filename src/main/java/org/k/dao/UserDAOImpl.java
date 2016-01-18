package org.k.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.k.domain.Checking;
import org.k.domain.Comment;
import org.k.domain.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAOImpl implements UserDAO {
    private static volatile UserDAOImpl instance;

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance(){
        if (instance == null){
            synchronized (UserDAOImpl.class){
                if (instance == null){
                    instance = new UserDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public User addUser(User user) {
        Session session = HibFactory.getFactory().openSession();
        User userDB = null;
        try {
            Transaction transaction = session.beginTransaction();
            userDB = (User) session.merge(user);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return userDB;
    }

    @Override
    public void updateUser(User user) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibFactory.getFactory().openSession();
        List<User> list = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            list = (List<User>) session.createQuery("from User ").list();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    @Override
    public User getUserByID(int id) {
        Session session = HibFactory.getFactory().openSession();
        User user = null;
        try {
            Transaction transaction = session.beginTransaction();
            user = (User) session.get(User.class,id);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        Session session = HibFactory.getFactory().openSession();
        List<User> users = null;
        try {
            Transaction transaction = session.beginTransaction();
            users = (List<User>) session.createQuery("from User where name = :name").setParameter("name",name).list();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Set<Checking> getCheckings(User user) {
        Session session = HibFactory.getFactory().openSession();
        Set<Checking> set = new HashSet<>();
        try {
            Transaction transaction = session.beginTransaction();
            User u = (User) session.get(User.class, user.getId());
            set = (Set<Checking>) u.getCheckings();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return set;
    }

    @Override
    public Set<Comment> getComments(User user) {
        Session session = HibFactory.getFactory().openSession();
        Set<Comment> set = new HashSet<>();
        try {
            Transaction transaction = session.beginTransaction();
            User u = (User) session.get(User.class,user.getId());
            set = (Set<Comment>) u.getComments();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return set;
    }
}
