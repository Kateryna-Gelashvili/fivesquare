package org.k.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.k.domain.Checking;

import java.util.ArrayList;
import java.util.List;

public class CheckingDAOImpl implements CheckingDAO {
    private static volatile CheckingDAOImpl instance;
    private CheckingDAOImpl() {
    }

    public static CheckingDAOImpl getInstance (){
        if (instance == null){
            synchronized (CheckingDAOImpl.class){
                if (instance == null){
                    instance = new CheckingDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int addChecking(Checking checking) {
        Session session = HibFactory.getFactory().openSession();
        Integer id = null;
        try {
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(checking);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return id;
    }

    @Override
    public void updateChecking(Checking checking) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(checking);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public void deleteChecking(Checking checking) {
        Session session = HibFactory.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(checking);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Checking> getAllCheckings() {
        Session session = HibFactory.getFactory().openSession();
        List<Checking> list = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            list = (List<Checking>) session.createQuery("from Checking").list();
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    @Override
    public Checking getCheckingById(int id) {
        Session session = HibFactory.getFactory().openSession();
        Checking checking = null;
        try {
            Transaction transaction = session.beginTransaction();
            checking = (Checking) session.get(Checking.class, id);
            transaction.commit();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return checking;
    }
}
