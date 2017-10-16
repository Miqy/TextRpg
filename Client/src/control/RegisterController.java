package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.DbConn;

import view.CharacterView;
import view.MainWindow;
import view.RegisterView;

public class RegisterController {
    
    private MainWindow mainWindow;
    private RegisterView view;
    private DbConn db;
    
    public RegisterController(MainWindow mainWindow, RegisterView view, DbConn db) {
        super();
        this.mainWindow = mainWindow;
        this.view = view;
        this.db = db;
        
        view.getRegButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                register();
            }
        });
        view.getBackButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                backToLoginPage();
            }
        });
    }
    
    public void register(){
        String login = view.getLoginInput().getText();
        String password = new String(view.getPassInput().getPassword());
        String secret = view.getSecretInput().getText();
        String mail = view.getMailInput().getText();
        System.out.println(login);
        if(login.length() == 0 || password.length() == 0 || secret.length() == 0 || mail.length() == 0){
            JOptionPane.showMessageDialog(view, "Fill all fields", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(password.length() < 5){
            JOptionPane.showMessageDialog(view, "Password too short", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            PreparedStatement stmt =
                db.getConn()
                .prepareStatement("insert into \"G4_MIDOB\".\"USERS\" (LOGIN, PASSWORD, ANSWER, EMAIL) VALUES (?, ?, ?, ?)");
            stmt.setString(1, login);    
            stmt.setString(2, password);
            stmt.setString(3, secret);
            stmt.setString(4, mail);
            stmt.execute();
            JOptionPane.showMessageDialog(view, "Register succesfull", "Register", JOptionPane.INFORMATION_MESSAGE);
            backToLoginPage();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void backToLoginPage(){
        view.setVisible(false);
        mainWindow.remove(view);
        mainWindow.generateLoginView();
    }
}
