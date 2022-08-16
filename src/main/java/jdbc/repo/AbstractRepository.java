package jdbc.repo;

import jdbc.dataSets.DataSet;
import jdbc.repo.executor.Executor;
import jdbc.repo.querylists.QueryList;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractRepository<T extends DataSet> implements Repository<T> {
    protected Executor executor;
    protected QueryList<T> queryList;

    public AbstractRepository(Connection connection, QueryList<T> queryList) {
        executor = new Executor(connection);
        this.queryList = queryList;
    }

    @Override
    public void insert(T entity) throws SQLException {
        executor.executeUpdate(queryList.insert(entity));
    }

    @Override
    public int getEntityId(T entity) throws SQLException {
        String query = queryList.getByName(entity);
        return executor.executeQuery(query, result -> {
            result.next();
            return result.getInt("id");
        });
    }

    @Override
    public void update(int id, String updatingFieldName, String newValue) throws SQLException {
        String query = queryList.changeValue(updatingFieldName, newValue);
        executor.executeUpdate(query);
    }

    @Override
    public void dropTable() throws SQLException {
        executor.executeUpdate(queryList.dropTable());
    }

    @Override
    public T delete(int id) throws SQLException {
        T deletedEntity = read(id);
        executor.executeUpdate(queryList.delete(id));
        return deletedEntity;
    }

    @Override
    public void createTable() throws SQLException {
        executor.executeUpdate(queryList.createTable());
    }
}