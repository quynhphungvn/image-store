package quynh.java.webapp.imagestore.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.imagestore.dao.ImageGroupDao;
import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.support.db.HibernateConnection;

public class ImageGroupDaoImpl implements ImageGroupDao {
    private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<ImageGroup> getAll() {
        Session session = sessionFactory.openSession();
        Query<ImageGroup> query = session.createQuery("FROM ImageGroup");
        List<ImageGroup> imageGroups = query.list();
        session.close();
        return imageGroups;
    }
    public ImageGroup getById(int id) {
        Session session = sessionFactory.openSession();
        ImageGroup imageGroup = session.get(ImageGroup.class, id);
        session.close();
        return imageGroup;
    }
    public void add(ImageGroup imageGroup) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(imageGroup);
        session.getTransaction().commit();
        session.close();
    }
    public void update(ImageGroup imageGroup) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(imageGroup);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(ImageGroup imageGroup) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(imageGroup);
        session.getTransaction().commit();
        session.close();   
    }
}
