package jdbc.repo.querylists;

import jdbc.dataSets.User;
import jdbc.repo.querylists.QueryList;

public class UserQueryList implements QueryList<User> {
    @Override
    public String insert(User user) {
        return String.format("insert into users (login,password) values (\"%s\",\"%s\")",user.getLogin(),user.getPassword());
    }

    @Override
    public String read(int id) {
        return "select * from users where id=" + id;
    }

    @Override
    public String getByName(User user) {
        return String.format("select * from users where login=\"%s\"", user.getLogin());
    }

    @Override
    public String dropTable() {
        return "drop table users";
    }

    @Override
    public String delete(int id) {
        return "delete from users where id=" + id;//TODO
    }

    @Override
    public String changeValue(String fieldName, String newValue) {
        return "alter table";//TODO
    }

    @Override
    public String createTable() {
        return "create table if not exists users (id int auto_increment primary key,login varchar(64) unique,password varchar(64))";
    }
}
