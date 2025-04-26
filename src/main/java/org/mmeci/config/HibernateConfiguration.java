package org.mmeci.config;

import org.mmeci.entity.Booking;
import org.mmeci.entity.Room;
import org.mmeci.entity.Hotel;
import org.mmeci.entity.Client;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateConfiguration {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            try (InputStream input = HibernateConfiguration.class.getClassLoader()
                    .getResourceAsStream("hibernate.properties")) {
                if (input == null) {
                    throw new RuntimeException("Unable to find hibernate.properties");
                }
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load hibernate.properties", e);
            }

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Hotel.class);
            configuration.addAnnotatedClass(Booking.class);
            configuration.addAnnotatedClass(Room.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}