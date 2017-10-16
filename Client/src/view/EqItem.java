package view;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Armor;
import model.CommonItem;
import model.Weapon;

public class EqItem extends JPanel{
    private JLabel name;
    private JButton info;
    private JButton equip;
    
    private Object ob;
    private Weapon weapon;
    private Armor armor;
    private CommonItem item;
    
    public EqItem(Object ob, String e){
        this.setMaximumSize(new Dimension(300,50));
        this.setMinimumSize(new Dimension(300,50));
        this.ob = ob;
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        name = new JLabel();
        info = new JButton("info");
        equip = new JButton(e);
        name.setMinimumSize(new Dimension(100,50));
        info.setMinimumSize(new Dimension(100,50));
        equip.setMinimumSize(new Dimension(100,50));
        name.setMaximumSize(new Dimension(100,50));
        info.setMaximumSize(new Dimension(100,50));
        equip.setMaximumSize(new Dimension(100,50));
        
        if(ob instanceof Weapon){
            weapon = (Weapon)ob;
            name.setText(weapon.getName());
            this.add(name);
            this.add(info);
            this.add(equip);
        }else if(ob instanceof Armor){
            armor = (Armor)ob;
            name.setText(armor.getName());
            this.add(name);
            this.add(info);
            this.add(equip);
        }else if(ob instanceof CommonItem){
            item = (CommonItem)ob;
            name.setText(item.getName());
            this.add(name);
            this.add(info);
        }
     
    }

    public void setName(JLabel name) {
        this.name = name;
    }

    public JLabel getNaame() {
        return name;
    }

    public void setInfo(JButton info) {
        this.info = info;
    }

    public JButton getInfo() {
        return info;
    }

    public void setEquip(JButton equip) {
        this.equip = equip;
    }

    public JButton getEquip() {
        return equip;
    }

    public void setOb(Object ob) {
        this.ob = ob;
    }

    public Object getOb() {
        return ob;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setItem(CommonItem item) {
        this.item = item;
    }

    public CommonItem getItem() {
        return item;
    }
}
