package jdbc.repo.querylists;

import jdbc.dataSets.DataSet;

public interface QueryList<T extends DataSet> {
    String insert(T entity);

    String read(int id);

    String getByName(T entity);

    String dropTable();

    String delete(int id);

    String changeValue(String fieldName, String newValue);

    String createTable();
}
