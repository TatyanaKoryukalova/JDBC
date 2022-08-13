package jdbc.dataSets;

public class User implements DataSet {
    private int id;
    private String login;
    private String password;

    public User(int id, String name, String password) {
        this.id = id;
        this.login = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.login = name;
        this.password = password;
    }

    @Override
    public String dbFieldsNames() {
        return "login,password";
    }

    @Override
    public String dbFields() {
        return String.format("\"%s\",\"%s\"", login, password);
    }

    //region getters-setters
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
