package model;

public class User {
    private DbConn connect;
    
    private int UserId;
    private String Login;
    private String Password;
    private String SecretAnswer;
    private String Email;


    public void setConnect(DbConn connect) {
        this.connect = connect;
    }

    public DbConn getConnect() {
        return connect;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getLogin() {
        return Login;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }

    public void setSecretAnswer(String SecretAnswer) {
        this.SecretAnswer = SecretAnswer;
    }

    public String getSecretAnswer() {
        return SecretAnswer;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    /**
     * @return
     */
    public @Override
    @SuppressWarnings("oracle.jdeveloper.java.unconventional-method-modifier-order")
    String toString() {
        return Login;
    }
}
