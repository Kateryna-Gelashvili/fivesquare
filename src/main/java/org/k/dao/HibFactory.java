package org.k.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class HibFactory {
    private static volatile SessionFactory factory;

    public static SessionFactory getFactory(){
        if (factory == null) {
            synchronized (SessionFactory.class) {
                if (factory == null) {
                    factory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return factory;
    }
    public static void closeSessionFactory(){
        if (factory != null){
            factory.close();
        }
    }
}
