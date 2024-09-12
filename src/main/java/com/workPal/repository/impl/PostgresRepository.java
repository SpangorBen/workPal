package main.java.com.workPal.repository.impl;

import main.java.com.workPal.config.DatabaseConnection;
import main.java.com.workPal.dto.Dto;
import main.java.com.workPal.model.Admin;
import main.java.com.workPal.model.Entity;
import main.java.com.workPal.model.Manager;
import main.java.com.workPal.model.Member;
import main.java.com.workPal.repository.interfaces.PostgresRepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class PostgresRepository<T extends Entity, D extends Dto, ID> implements PostgresRepositoryInterface<T, D, ID> {

    protected String tableName;

    protected PostgresRepository(String tableName) {
        this.tableName = tableName;
    }

    protected abstract D mapRowToDto(ResultSet resultSet, Class<D> dtoClass) throws SQLException;
    protected abstract void setParametersForSaveOrUpdate(PreparedStatement statement, D dto) throws SQLException;

    protected D mapEntityToDto(T entity, Class<D> dtoClass) {
        try {
            return (D) dtoClass.getMethod("fromEntity", entity.getClass()).invoke(null, entity);
        } catch (Exception e) {
            throw new RuntimeException("Error mapping entity to DTO: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<D> findById(ID id, String idColumnName, Class<D> dtoClass, Connection connection) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(mapRowToDto(resultSet, dtoClass));
            }

        } catch (SQLException e) {
            throw new SQLException("Error finding " + tableName + " by ID: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<D> findAll(Class<D> dtoClass, Connection connection) {
        List<D> dtos = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                dtos.add(mapRowToDto(resultSet, dtoClass));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all from " + tableName + ": " + e.getMessage(), e);
        }

        return dtos;
    }

    @Override
    public D save(T entity, Class<D> dtoClass, Connection connection) {
        D dto = mapEntityToDto(entity, dtoClass);
        String[] columns = dto.getAttributes();
        String questionMarks = String.join(", ", java.util.Collections.nCopies(columns.length, "?"));
        String targetTable = getTargetTableName(entity);
        String sql = "INSERT INTO " + targetTable + " (" + String.join(", ", columns) + ") VALUES (" + questionMarks + ")";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setParametersForSaveOrUpdate(statement, dto);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating " + targetTable + " failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ID generatedId = (ID) generatedKeys.getObject(1);
                    entity.setId(generatedId);
                } else {
                    throw new SQLException("Creating " + targetTable + " failed, no ID obtained.");
                }
            }

            return dto;
        } catch (SQLException e) {
            throw new RuntimeException("Error saving " + targetTable + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void update(D dto, Connection connection) throws SQLException {
        String[] columns = dto.getAttributes();
        String setClause = String.join(" = ?, ", columns) + " = ?";
        String sql = "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";

        System.out.println("SQL: " + sql);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParametersForSaveOrUpdate(statement, dto);
            statement.setObject(columns.length + 1, dto.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating " + tableName + " failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new SQLException("Error updating " + tableName + ": " + e.getMessage(), e);
        }

    }

    @Override
    public void deleteById(ID id, String idColumnName, Connection connection) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Deleting " + tableName + " failed, no rows affected. ID: " + id);
            }

        } catch (SQLException e) {
            throw new SQLException("Error deleting " + tableName + ": " + e.getMessage(), e);
        }
    }


    private String getTargetTableName(T entity) {
        if (entity instanceof Member) {
            return "members";
        } else if (entity instanceof Admin) {
            return "admins";
        } else if (entity instanceof Manager) {
            return "managers";
        } else {
            return "users";
        }
    }


}
