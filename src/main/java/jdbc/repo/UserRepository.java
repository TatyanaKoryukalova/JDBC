package jdbc.repo;

import jdbc.dataSets.User;
import jdbc.repo.querylists.UserQueryList;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository(Connection connection) {
        super(connection, new UserQueryList());
    }

    @Override
    public User read(int id) throws SQLException {
        String query = queryList.read(id);
        return executor.executeQuery(query, resultSet -> {
            resultSet.next();
            return new User(resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"));
        });
    }
}
