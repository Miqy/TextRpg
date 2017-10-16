package view;

import control.CharacterController;
import control.EqController;
import control.FightController;
import control.LoginController;

import control.RegisterController;

import java.sql.SQLException;

import javax.swing.JFrame;

import javax.swing.JLabel;

import model.Characterr;
import model.DbConn;
import model.User;


public class MainWindow extends JFrame {
    private DbConn db;
    private LoginView loginView;
    private CharacterView characterView;
    private RegisterView registerView;
    private EqView eqView;
    private FightView fightView;

    private LoginController loginController;
    private CharacterController characterController;
    private RegisterController registerController;
    private EqController eqController;
    private FightController fightController;

    private User loggedUser;
    private Characterr selectedChar;

    MainWindow() {
        this.setSize(1024, 768);
        this.setTitle("Rpg");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            db = new DbConn();
            db.getDBConnection();
        } catch (SQLException | RuntimeException ex) {
            ex.printStackTrace();
            this.add(new JLabel("Connection refused"));
        }
        generateLoginView();
    }

    public void generateLoginView() {
        loginView = new LoginView();
        loginController = new LoginController(this, loginView, db);
        this.add(loginView);
    }

    public void generateCharacterView() {
        characterView = new CharacterView();
        characterController = new CharacterController(this, characterView, db);
        this.add(characterView);
    }

    public void generateRegisterView() {
        registerView = new RegisterView();
        registerController = new RegisterController(this, registerView, db);
        this.add(registerView);
    }

    public void generateEqView() {
        eqView = new EqView();
        eqController = new EqController(this, eqView, db);
        this.add(eqView);
    }

    public void generateFightView() {
        fightView = new FightView();
        fightController = new FightController(this, fightView, db);
        this.add(fightView);
    }

    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }


    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setSelectedChar(Characterr selectedChar) {
        this.selectedChar = selectedChar;
    }

    public Characterr getSelectedChar() {
        return selectedChar;
    }
}
