package jdbc.dbservice;

import jdbc.dataSets.User;
import jdbc.exceptions.WrongPasswordException;
import jdbc.repo.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService implements DBService<User> {
    private final Connection connection;
    private final UserRepository repository;

    public UserService() throws SQLException {
        connection = MySQLConnection.getConnection();
        repository = new UserRepository(connection);
        repository.createTable();
    }

    @Override
    public int create(User user) throws SQLException {
        connection.setAutoCommit(false);
        repository.insert(user);
        connection.commit();
        return repository.getEntityId(user);
    }

    @Override
    public User read(User user) throws SQLException {
        int id = repository.getEntityId(user);
        if (!user.getPassword().equals(repository.getPassword(id))) {
            throw new WrongPasswordException("Wrong password");
        }
        return repository.read(id);
    }

    @Override
    public User update(User user, String password) {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public void cleanUp() throws SQLException {
        repository.dropTable();
    }
}
