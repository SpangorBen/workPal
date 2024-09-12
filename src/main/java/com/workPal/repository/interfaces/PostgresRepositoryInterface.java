package main.java.com.workPal.repository.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PostgresRepositoryInterface<T, D, ID> {
    List<D> findAll(Class<D> dtoClass, Connection connection);
    D save(T entity, Class<D> dtoClass, Connection connection);
    Optional<D> findById(ID id, String idColumnName, Class<D> dtoClass, Connection connection) throws SQLException;
    void deleteById(ID id, String idColumnName, Connection connection) throws SQLException;
    void update(D dtoClass, Connection connection) throws SQLException;
}
