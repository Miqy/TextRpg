package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class LoginView extends JPanel{
    
    private JButton login;
    private JTextField loginInput;
    private JPasswordField passwordInput;
    private JButton regButton;
    
    LoginView(){
        this.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel loginLabel = new JLabel("Login");
        JLabel passLabel = new JLabel("Password");
        
        loginInput = new JTextField();
        loginInput.setMaximumSize(new Dimension(300,100));
        loginInput.setToolTipText("Enter your username here");
        
        passwordInput = new JPasswordField(2);
        passwordInput.setMaximumSize(new Dimension(300,100));
        passwordInput.setToolTipText("Enter your password here");
        
        login = new JButton("Login");
    
        panel.add(loginLabel);
        panel.add(loginInput);
        panel.add(passLabel);
        panel.add(passwordInput);
        panel.add(login);
        
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logPanel.add(panel);
        
        this.add(logPanel, BorderLayout.CENTER);
                
        JPanel regPanel = new JPanel();
        regPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel regLabel = new JLabel("if you want to create account click register");
        regButton = new JButton("Register");
        regPanel.add(regLabel);
        regPanel.add(regButton);
        this.add(regPanel, BorderLayout.SOUTH);    
    }


    public void setLogin(JButton login) {
        this.login = login;
    }

    public JButton getLogin() {
        return login;
    }

    public void setLoginInput(JTextField loginInput) {
        this.loginInput = loginInput;
    }

    public JTextField getLoginInput() {
        return loginInput;
    }

    public void setPasswordInput(JPasswordField passwordInput) {
        this.passwordInput = passwordInput;
    }

    public JPasswordField getPasswordInput() {
        return passwordInput;
    }

    public void setRegButton(JButton regButton) {
        this.regButton = regButton;
    }

    public JButton getRegButton() {
        return regButton;
    }
}
