package quynh.java.webapp.imagestore.support.db;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.model.ImageFile;

public class HibernateConnection {
    private final static SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        try {
            props.load(HibernateConnection.class.getClassLoader().getResourceAsStream("db-info.properties"));
        } catch (IOException e) {

            e.printStackTrace();
        }
        conf.setProperty(Environment.DIALECT, props.getProperty("dialect"));
        conf.setProperty(Environment.DRIVER, props.getProperty("driver"));
        conf.setProperty(Environment.URL, props.getProperty("url"));
        conf.setProperty(Environment.USER, props.getProperty("user"));
        conf.setProperty(Environment.PASS, props.getProperty("password"));
        conf.setProperty(Environment.SHOW_SQL, "true");

        //Entity
        conf.addAnnotatedClass(ImageGroup.class);
        conf.addAnnotatedClass(ImageFile.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
