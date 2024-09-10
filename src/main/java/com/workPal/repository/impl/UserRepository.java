package main.java.com.workPal.repository.impl;

import main.java.com.workPal.config.DatabaseConnection;
import main.java.com.workPal.dto.UserDto;
import main.java.com.workPal.model.User;
import main.java.com.workPal.repository.interfaces.UserRepositoryInterface;

import java.sql.*;
import java.util.Optional;

public class UserRepository extends PostgresRepository<User, UserDto, Integer> implements UserRepositoryInterface {

    public UserRepository() {
        super("users");
    }

    @Override
    protected UserDto mapRowToDto(ResultSet resultSet, Class<UserDto> dtoClass) throws SQLException {
        UserDto dto = new UserDto();
        dto.setId(resultSet.getInt("id"));
        dto.setName(resultSet.getString("name"));
        dto.setEmail(resultSet.getString("email"));
        dto.setPassword(resultSet.getString("password"));
        dto.setEncodedSalt(resultSet.getString("encodedSalt"));
        return dto;
    }

    @Override
    protected void setParametersForSaveOrUpdate(PreparedStatement statement, UserDto dto) throws SQLException {
        statement.setString(1, dto.getName());
        statement.setString(2, dto.getPassword());
        statement.setString(3, dto.getEmail());
        statement.setString(4, dto.getEncodedSalt());
    }


    @Override
    public Optional<UserDto> findById(Integer id, String idColumnName, Class<UserDto> dtoClass, Connection connection) throws SQLException {
        return super.findById(id, idColumnName, dtoClass, connection);
    }

    @Override
    public void deleteById(Integer id, String idColumnName, Connection connection) throws SQLException {
        super.deleteById(id, idColumnName, connection);
    }

}
