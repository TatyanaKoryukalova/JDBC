package jdbc.repo;

import jdbc.dataSets.DataSet;

import java.sql.SQLException;

/**
 * По сути репо предполагается как уже что-то готовое, то есть тут у нас именно действия с сущностями
 *
 * @param <T>
 */
public interface Repository<T extends DataSet> {
    /*
    TODO change "void" to return thmth
    Каким образом уже будет формироваться этот датасет?
    Ну например поля в форме для заполнения, на каждую сущность своя страничка
    Возвращаемое значение - айди созданного объекта. Оно ж автоинкрементное будет
     */
    void insert(T entity) throws SQLException;

    T read(int id) throws SQLException;

    /*
    TODO ? what we updating, which field? reloaded?
     */
    void update(int id);

    T delete(int id) throws SQLException;

    /**
     * Создает таблицу с заданным названием для каждой сущности (например как поле класса храниться будет)
     * Ну или создает только в том случае если данной таблицы не существует...
     * <p>
     * throws какой нибудь exception если имя занято, к примеру
     */
    void createTable() throws SQLException;

    void dropTable() throws SQLException;

    int getEntityId(T entity) throws SQLException;
}
