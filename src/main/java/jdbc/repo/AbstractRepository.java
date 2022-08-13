package jdbc.repo;

import jdbc.dataSets.DataSet;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractRepository<T extends DataSet> implements Repository<T> {
    protected Executor executor;
    protected QueryList<T> queryList;

    public AbstractRepository(Connection connection) {
        executor = new Executor(connection);
    }

    @Override
    public void insert(T entity) throws SQLException {
//        String query = insertQuery(entity);
        String query = queryList.insert(entity);
        executor.executeUpdate(query);
    }

//    protected abstract String insertQuery(T entity);

    protected abstract String tableName();

//    @Override
//    public T read(int id) throws SQLException {
////        String query = String.format("select * from %s where id=%s", tableName(), id);
//        String query = queryList.readQuery(id);
//        T entity = executor.executeQuery(query, resultSet -> {
//            resultSet.next();
//            return getEntity(resultSet.getInt("id"),
//                    resultSet.getString("login"),
//                    resultSet.getString("password"));
//        });
//        return null;
//    }

    @Override
    public int getEntityId(T entity) throws SQLException {
//        String query = queryGetId(entity);
        String query = queryList.getId(entity);
        return executor.executeQuery(query, result -> {
            result.next();
            return result.getInt("id");
        });
    }

//    protected abstract String queryGetId(T entity);

    @Override
    public void update(int id) {

    }

    @Override
    public void dropTable() throws SQLException {
//        String query = "drop table " + tableName();
        String query = queryList.dropTable();
        executor.executeUpdate(query);
    }

    @Override
    public T delete(int id) throws SQLException {
//        String query = String.format("delete from %s where id=%s", tableName(), id);
        T deletedEntity  = read(id);
        String query = queryList.delete();
        return deletedEntity;
    }

//    protected abstract T getEntity(int id, String name, String password);
}