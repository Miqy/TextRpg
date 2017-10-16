package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

import model.DbConn;

import model.User;

import view.LoginView;
import view.MainWindow;

public class LoginController {
    private MainWindow mainWindow;
    private LoginView view;
    private DbConn db;
    private User loggedUser;
    
    public LoginController(MainWindow mainWindow, LoginView view ,DbConn db){
        loggedUser = null;
        this.mainWindow = mainWindow;
        this.view = view;
        this.db = db;
        view.getLogin().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                login();
            }
        });
        view.getRegButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                register();
            }
        });
    }
    
    public void login(){
        Statement stmt;
        try {
            stmt = db.getConn().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                if(resultSet.getString("login").equals(view.getLoginInput().getText()) && resultSet.getString("password").equals(new String(view.getPasswordInput().getPassword()))){
                    loggedUser = new User();
                    loggedUser.setLogin(resultSet.getString("login"));
                    loggedUser.setUserId(resultSet.getInt("User_Id"));
                    loggedUser.setPassword(resultSet.getString("password"));
                    loggedUser.setEmail(resultSet.getString("Email"));
                    loggedUser.setSecretAnswer(resultSet.getString("Answer"));
                    mainWindow.setLoggedUser(loggedUser);
                    
                    System.out.println("Logged as " + loggedUser.getLogin());
                    view.setVisible(false);
                    mainWindow.remove(view);
                    mainWindow.generateCharacterView();
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Database error", "Error", JOptionPane.ERROR_MESSAGE);                     
        }
        JOptionPane.showMessageDialog(view, "Wrong username/password", "Login", JOptionPane.ERROR_MESSAGE);
        view.getLoginInput().setText("");
        view.getPasswordInput().setText("");
        
    }
    
    public void register(){
        mainWindow.generateRegisterView();
        view.setVisible(false);
        mainWindow.remove(view);
    }
}
