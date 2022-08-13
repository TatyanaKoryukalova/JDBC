package jdbc.dbservice;

import jdbc.dataSets.DataSet;

import java.sql.SQLException;

public interface DBService<T extends DataSet> {
    int create(T entity) throws SQLException;

    T read(T entity) throws SQLException;

    T update(T entity, String value);

    void delete();

    /**
     * Drop table
     */
    void cleanUp() throws SQLException;
}
