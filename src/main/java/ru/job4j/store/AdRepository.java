package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Post;

import java.util.List;
import java.util.function.Function;

public class AdRepository {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory factory = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public List<Post> getPostsForLastDay() {
        return transaction(session ->
                session.createQuery("from Post "
                        + "where extract(day from current_date) - extract(day from created) = 1")
                        .list());
    }

    public List<Post> getPostsByMark(String mark) {
        return (List<Post>) transaction(session ->
                session.createQuery("from Post where mark =: mark")
                    .setParameter("mark", mark).list());
    }

    private <T> T transaction(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
