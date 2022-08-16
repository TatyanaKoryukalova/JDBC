package jdbc.repo.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Исполнитель запросов к базе данных
 */
public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    /**
     * Выполняет запрос на получение каких-либо данных из БД
     *
     * @param query - sql-запрос к БД
     * @param handler - обработчик полученного в результате запроса ResultSet,
     *                прописывается в виде лямбда-выращения в месте использования
     * @return - запрашиваемые данные
     * @param <T> - тип запрашиваемых данных
     * @throws SQLException
     */
    public <T> T executeQuery(String query, ResultHandler<T> handler) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        T value = handler.handle(resultSet);
        statement.close();
        return value;
    }

    /**
     * Выполняет запрос на обновление БД
     * Например создание таблицы, вставка, удаление элемента, изменение полей и т.д.
     * То есть случаи, когда не требуется получение уже записанных данных
     *
     * @param query - sql-запрос к БД
     * @throws SQLException
     */
    public void executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
    }

}
