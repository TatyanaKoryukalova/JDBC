package jdbc.repo;

import jdbc.dataSets.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRepository extends AbstractRepository<User> {
    public UserRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String insertQuery(User user) {
        return String.format("insert into users (login,password) values (\"%s\",\"%s\")", user.getLogin(), user.getPassword());
    }

    @Override
    protected String tableName() {
        return "users";
    }

    @Override
    protected String queryGetId(User user) {
        return "select * from users where login=\"" + user.getLogin() + "\"";
    }

    //"select * from users where user_name='" + name + "'"

    @Override
    protected User getEntity(int id, String name, String password) {
        return new User(id, name, password);
    }

    public String getPassword(int id) throws SQLException {
        String query = String.format("select * from users where id=%s", id);
        String password = executor.executeQuery(query, resultSet -> {
            resultSet.next();
            return resultSet.getString("password");
        });
        return password;
    }

    @Override
    public void createTable() throws SQLException {
        String query = "create table if not exists users (id int auto_increment primary key,login varchar(64) unique,password varchar(64))";
        executor.executeUpdate(query);
    }
}
