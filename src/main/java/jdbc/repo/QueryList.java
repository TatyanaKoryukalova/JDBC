package jdbc.repo;

import jdbc.dataSets.DataSet;

public interface QueryList<T extends DataSet> {
    String insert(T entity);

    String read(int id);

    String getId(T entity);

    String dropTable();

    String delete();
}
