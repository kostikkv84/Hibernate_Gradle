package db;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;

/**
 * Сервис взаимодействия с БД - пока не использую
 */
public abstract class JpaService {

    protected final Session session;

    protected JpaService(Session session) {
        this.session = session;
    }

    /**
     * Добавляет новую сущность
     * @param entity
     * @param <T>
     */
    public <T> void persist(T entity) {
        transaction(session -> session.persist(entity));
    }


    private void transaction(Consumer<Session> action) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            action.accept(session);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }


}
