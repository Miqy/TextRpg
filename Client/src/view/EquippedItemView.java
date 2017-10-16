package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EquippedItemView extends JPanel{
    private JLabel label;
    private JButton button;
    
    public EquippedItemView(String name, String value){
        label = new JLabel(name);
        button = new JButton(value);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(label);
        this.add(button);
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public JButton getButton() {
        return button;
    }
}
