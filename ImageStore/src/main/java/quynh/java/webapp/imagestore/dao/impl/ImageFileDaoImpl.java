package quynh.java.webapp.imagestore.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import quynh.java.webapp.imagestore.dao.ImageFileDao;
import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.model.ImageFile;
import quynh.java.webapp.imagestore.support.db.HibernateConnection;


public class ImageFileDaoImpl implements ImageFileDao {
    private SessionFactory sessionFactory = HibernateConnection.getSessionFactory();
    
    public List<ImageFile> getAll(ImageGroup imageGroup) {
        Session session = sessionFactory.openSession();
        Query<ImageFile> query = session.createQuery("FROM ImageFile where imageGroup=:imageGroup");
        query.setParameter("imageGroup", imageGroup);
        List<ImageFile> imageInfos = query.list();
        session.close();
        return imageInfos;
    }
    public ImageFile getById(int id) {
        Session session = sessionFactory.openSession();
        ImageFile imageInfo = session.get(ImageFile.class, id);
        session.close();
        return imageInfo;
    }
    public void add(ImageFile imageInfo) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(imageInfo);
        session.getTransaction().commit();
        session.close();
    }
    public void update(ImageFile imageInfo) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(imageInfo);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(ImageFile imageInfo) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(imageInfo);
        session.getTransaction().commit();
        session.close();   
    }
}
