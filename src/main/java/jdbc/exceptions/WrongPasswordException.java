package jdbc.exceptions;

import java.sql.SQLException;

public class WrongPasswordException extends SQLException {
    public WrongPasswordException(String massage) {
        super(massage);
    }
}
