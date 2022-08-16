package jdbc.repo;

import jdbc.dataSets.DataSet;

import java.sql.SQLException;

/**
 * Репозиторий для хранения, записи, чтения, удаления и изменения сущностей заданного типа
 * Представляется в виде конкретной таблицы в БД
 *
 * @param <T> - тип записываемых сущностей
 */
public interface Repository<T extends DataSet> {
    /**
     * Вставляет новую запись в таблицу с информацией о сущности
     * @param entity - указанная сущность
     * @throws SQLException
     */
    void insert(T entity) throws SQLException;

    /**
     * Возвращает прочитанную из БД сущность с заданным id
     *
     * @param id - id искомой сущности
     * @return - искомая сущность и аданным id
     * @throws SQLException
     */
    T read(int id) throws SQLException;

    /**
     * Изменяет указанные поля заданной сущности
     *
     * @param id - id заданной сущности
     * @param updatingFieldName - метка поля, которое необходимо изменить
     * @param newValue - новое значение изменяемого поля
     * @throws SQLException
     */
    void update(int id,String updatingFieldName, String newValue) throws SQLException;

    /**
     * Удаляет из БД сущность с заданным id
     *
     * @param id - id указанной сущности
     * @return - удаленная сущность
     * @throws SQLException
     */
    T delete(int id) throws SQLException;

    /**
     * Создает таблицу с заданным названием для каждой сущности
     * только в том случае, если таблица не существует
     *
     * @throws SQLException
     */
    void createTable() throws SQLException;

    /**
     * Удаляет таблицу
     *
      * @throws SQLException
     */
    void dropTable() throws SQLException;

    /**
     * Возвращает id в БД заданной сущности
     *
     * @param entity - объект, id которого возвращается
     * @return - id заданной сущности
     * @throws SQLException
     */
    int getEntityId(T entity) throws SQLException;
}
