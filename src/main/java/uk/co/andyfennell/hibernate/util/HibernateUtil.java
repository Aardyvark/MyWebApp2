package uk.co.andyfennell.hibernate.util;

import java.io.File;

import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            //return new Configuration().configure().buildSessionFactory();
            
// Hibernate 4            
            
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("eventtest-hibernate.cfg.xml");
  
            // Create ServiceRegistry and populate it with the configuration settings
            
            // Hibernate 4.0, 4.1, 4.2
            ServiceRegistry serviceRegistry =
                    new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            
            // Hibernate 4.3
            //ServiceRegistry serviceRegistry =
            //        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
            
            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}