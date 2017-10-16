package view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterView extends JPanel{
    private JTextField loginInput;
    private JPasswordField passInput;
    private JTextField secretInput;
    private JTextField mailInput;
    private JButton regButton;
    private JButton backButton;
    
    public RegisterView() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel loginLab = new JLabel("Login");
        JLabel passLab = new JLabel("Password");
        JLabel secretLab = new JLabel("Secret Answer");
        JLabel mailLab = new JLabel("Email");
        
        loginInput = new JTextField();
        passInput = new JPasswordField();
        secretInput = new JTextField();
        mailInput = new JTextField();
        
        regButton = new JButton("Register");
        backButton = new JButton("Back");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(loginLab);
        panel.add(loginInput);
        panel.add(passLab);
        panel.add(passInput);
        panel.add(secretLab);
        panel.add(secretInput);
        panel.add(mailLab);
        panel.add(mailInput);
        panel.add(regButton);
        panel.add(backButton);
        
        this.add(panel);
    }

    public void setLoginInput(JTextField loginInput) {
        this.loginInput = loginInput;
    }

    public JTextField getLoginInput() {
        return loginInput;
    }

    public void setPassInput(JPasswordField passInput) {
        this.passInput = passInput;
    }

    public JPasswordField getPassInput() {
        return passInput;
    }

    public void setSecretInput(JTextField secretInput) {
        this.secretInput = secretInput;
    }

    public JTextField getSecretInput() {
        return secretInput;
    }

    public void setMailInput(JTextField mailInput) {
        this.mailInput = mailInput;
    }

    public JTextField getMailInput() {
        return mailInput;
    }

    public void setRegButton(JButton regButton) {
        this.regButton = regButton;
    }

    public JButton getRegButton() {
        return regButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
