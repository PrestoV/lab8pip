package com.presto.lab8Pip;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PointDAO {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public void save(Point point) {
        session.beginTransaction();
        session.save(point);
        session.getTransaction().commit();
        session.close();
    }

    public List<Point> getAll () {
        List<Point> results = session.createQuery("From Point").list();
        session.close();
        return results;
    }

    public void incrementPoints() {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update Point set xParam=xParam+1, yParam=yParam+1, rParam=rParam+1");
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}