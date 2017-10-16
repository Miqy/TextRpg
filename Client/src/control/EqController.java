package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Armor;
import model.CommonItem;
import model.DbConn;

import model.Enchant;
import model.EquippedItems;

import model.Weapon;

import view.CharacterView;
import view.EqItem;
import view.EqView;
import view.MainWindow;

public class EqController {
    private MainWindow mainWindow;
    private EqView view;
    private DbConn db;

    private EquippedItems eqItems;
    private Weapon weapon;
    private Armor helmet;
    private Armor chest;
    private Armor legs;
    private Armor feet;
    private Armor hands;
    private Armor shoulder;
    private Armor neckle;
    private Armor ring1;


    //Equipment collectors
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Armor> armors = new ArrayList<>();
    private ArrayList<CommonItem> items = new ArrayList<>();

    private ArrayList<EqItem> weaponViews = new ArrayList<>();
    private ArrayList<EqItem> armorViews = new ArrayList<>();
    private ArrayList<EqItem> itemViews = new ArrayList<>();


    public EqController(MainWindow m, EqView view, DbConn db) {
        this.mainWindow = m;
        this.view = view;
        this.db = db;

        weapons.clear();
        armors.clear();
        items.clear();

        view.getNickLabel().setText("Nickname: " + mainWindow.getSelectedChar().getNickname());
        refreshEquippedItems();
        loadAppropriateImage();
        loadEqItems();
        addEqItemsToView();
        view.getReturnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                returnToCharacterSelect();
            }
        });
        view.getFightButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goToFightPanel();
            }
        });
    }

    public void returnToCharacterSelect() {
        view.setVisible(false);
        mainWindow.remove(view);
        mainWindow.generateCharacterView();
    }

    public void goToFightPanel() {
        view.setVisible(false);
        mainWindow.remove(view);
        mainWindow.generateFightView();
    }

    public void refreshEquippedItems() {
        System.out.println("Wczytywanie ubranych itemow");
        loadEquippedItems();
        loadWeapon();
        loadArmor("select * from armors where character_id = ?", eqItems.getHelmet(), 0);
        loadArmor("select * from armors where character_id = ?", eqItems.getChest(), 1);
        loadArmor("select * from armors where character_id = ?", eqItems.getLegs(), 2);
        loadArmor("select * from armors where character_id = ?", eqItems.getFeet(), 3);
        loadArmor("select * from armors where character_id = ?", eqItems.getHands(), 4);
        loadArmor("select * from armors where character_id = ?", eqItems.getShoulder(), 5);
        loadArmor("select * from armors where character_id = ?", eqItems.getNeckle(), 6);
        loadArmor("select * from armors where character_id = ?", eqItems.getRing1(), 7);
        loadArmor("select * from armors where character_id = ?", eqItems.getRing2(), 8);
        setEquippedItemsInView();
    }

    public void addEqItemsToView() {
        for (int i = 0; i < weapons.size(); i++) {
            //view.getEqContent().add(new JButton(weapons.get(i).getName()));
            EqItem eqItem = null;
            if (weapon != null) {
                if (weapons.get(i).getWeapon_id() == weapon.getWeapon_id()) {
                    eqItem = new EqItem(weapons.get(i), "remove");
                } else {
                    eqItem = new EqItem(weapons.get(i), "equip");
                }
            } else {
                eqItem = new EqItem(weapons.get(i), "equip");
            }
            //declaration inner class for handle info and equip buttons
            class HandleInfo implements ActionListener {
                private int index;

                public HandleInfo(int index) {
                    this.index = index;
                }

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    eqItemInfo(weapons.get(index));
                }
            }
            class HandleEq implements ActionListener {
                private int index;
                private EqItem eq;

                HandleEq(int index, EqItem eq) {
                    this.index = index;
                    this.eq = eq;
                }

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    equipWeapon(weapons.get(index), eq);
                }
            }
            eqItem.getInfo().addActionListener(new HandleInfo(i));
            eqItem.getEquip().addActionListener(new HandleEq(i, eqItem));
            view.getEqContent().add(eqItem);
        }
        for (int i = 0; i < armors.size(); i++) {
            //view.getEqContent().add(new JButton(armors.get(i).getName()));
            EqItem eqItem = null;
            if (armors.get(i).getType() == 0) {
                if (helmet != null) {
                    if (armors.get(i).getArmor_id() == helmet.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            } else if (armors.get(i).getType() == 1) {
                if (chest != null) {
                    if (armors.get(i).getArmor_id() == chest.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            } else if (armors.get(i).getType() == 2) {
                if (legs != null) {
                    if (armors.get(i).getArmor_id() == legs.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            } else if (armors.get(i).getType() == 3) {
                if (feet != null) {
                    if (armors.get(i).getArmor_id() == feet.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            } else if (armors.get(i).getType() == 4) {
                if (hands != null) {
                    if (armors.get(i).getArmor_id() == hands.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            } else if (armors.get(i).getType() == 5) {
                if (shoulder != null) {
                    if (armors.get(i).getArmor_id() == shoulder.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            } else if (armors.get(i).getType() == 6) {
                if (neckle != null) {
                    if (armors.get(i).getArmor_id() == neckle.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            } else if (armors.get(i).getType() == 7) {
                if (ring1 != null) {
                    if (armors.get(i).getArmor_id() == ring1.getArmor_id()) {
                        eqItem = new EqItem(armors.get(i), "remove");
                    } else {
                        eqItem = new EqItem(armors.get(i), "equip");
                    }
                } else {
                    eqItem = new EqItem(armors.get(i), "equip");
                }
            }

            class HandleInfo implements ActionListener {
                private int index;

                public HandleInfo(int index) {
                    this.index = index;
                }

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    eqItemInfo(armors.get(index));
                }
            }
            class HandleEq implements ActionListener {
                private int index;
                private EqItem eq;

                HandleEq(int index, EqItem eq) {
                    this.index = index;
                    this.eq = eq;
                }

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    equipArmor(armors.get(index), eq, armors.get(index).getType());
                }
            }
            eqItem.getInfo().addActionListener(new HandleInfo(i));
            eqItem.getEquip().addActionListener(new HandleEq(i, eqItem));
            view.getEqContent().add(eqItem);
        }
        for (int i = 0; i < items.size(); i++) {
            //view.getEqContent().add(new JButton(items.get(i).getName()));
            EqItem eqItem;
            eqItem = new EqItem(items.get(i), "equip");
            class HandleInfo implements ActionListener {
                private int index;

                public HandleInfo(int index) {
                    this.index = index;
                }

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    eqItemInfo(items.get(index));
                }
            }
            eqItem.getInfo().addActionListener(new HandleInfo(i));
            view.getEqContent().add(eqItem);
        }
    }


    public void loadEqItems() {
        try {
            PreparedStatement stmt =
                db.getConn()
                .prepareStatement("Select * from weapons,weaponfks where weapons.weapon_id = weaponfks.weapon_id and  EQUIPMENT_ID = ?");
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getEquipment_id()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Weapon w = new Weapon();
                w.setName(rs.getString("name"));
                w.setAttackspeed(rs.getInt("attackspeed"));
                w.setCritchance(rs.getDouble("critchance"));
                w.setDamage(rs.getInt("damage"));
                w.setIcon(rs.getBlob("icon"));
                w.setLevel(rs.getInt("level"));
                w.setType(rs.getInt("type"));
                w.setValue(rs.getInt("value"));
                w.setWeapon_id(rs.getInt("weapon_id"));
                w.setWeight(rs.getInt("weight"));
                weapons.add(w);
            }
            stmt =
                db.getConn()
                .prepareStatement("Select * from armors,armorfks where armors.armor_id = armorfks.armor_id and  EQUIPMENT_ID = ?");
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getEquipment_id()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                Armor a = new Armor();
                a.setName(rs.getString("name"));
                a.setDefence(rs.getInt("defence"));
                a.setIcon(rs.getBlob("icon"));
                a.setLevel(rs.getInt("level"));
                a.setType(rs.getInt("type"));
                a.setValue(rs.getInt("value"));
                a.setArmor_id(rs.getInt("armor_id"));
                a.setWeight(rs.getInt("weight"));
                a.setWeightType(rs.getInt("Weighttype"));
                armors.add(a);
            }
            stmt =
                db.getConn()
                .prepareStatement("Select * from commonitems,commonfks where commonitems.commonitem_id = commonfks.commonitem_id and  EQUIPMENT_ID = ?");
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getEquipment_id()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                CommonItem c = new CommonItem();
                c.setCommonitem_id(rs.getInt("Commonitem_id"));
                c.setIcon(rs.getBlob("Icon"));
                c.setName(rs.getString("name"));
                c.setPower(rs.getInt("Power"));
                c.setType(rs.getInt("Type"));
                c.setValue(rs.getInt("Value"));
                c.setWeight(rs.getInt("Weight"));
                items.add(c);
            }
            for (int i = 0; i < weapons.size(); i++) {
                System.out.println(weapons.get(i).getName());
            }
            for (int i = 0; i < armors.size(); i++) {
                System.out.println(armors.get(i).getName());
            }
            for (int i = 0; i < items.size(); i++) {
                System.out.println(items.get(i).getName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setEquippedItemsInView() {
        //view.getEquippedPanel().removeAll();
        for (Component c : view.getEquippedPanel().getComponents()) {
            c.setVisible(false);
            view.getEquippedPanel().remove(c);
        }
        view.generateEquippedPanel();
        if (weapon != null) {
            view.getWeaponView()
                .getButton()
                .setText(weapon.getName());
            view.getWeaponView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(weapon);
                    }
                });
        } else {
            view.getWeaponView()
                .getButton()
                .setText("none");
        }
        if (helmet != null) {
            view.getHelmetView()
                .getButton()
                .setText(helmet.getName());
            view.getHelmetView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(helmet);
                    }
                });
        }
        if (chest != null) {
            view.getChestView()
                .getButton()
                .setText(chest.getName());
            view.getChestView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(chest);
                    }
                });
        }
        if (legs != null) {
            view.getLegsView()
                .getButton()
                .setText(legs.getName());
            view.getLegsView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(legs);
                    }
                });
        }
        if (feet != null) {
            view.getFeetView()
                .getButton()
                .setText(feet.getName());
            view.getFeetView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(feet);
                    }
                });
        }
        if (hands != null) {
            view.getHandsView()
                .getButton()
                .setText(hands.getName());
            view.getHandsView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(hands);
                    }
                });
        }
        if (shoulder != null) {
            view.getShoulderView()
                .getButton()
                .setText(shoulder.getName());
            view.getShoulderView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(shoulder);
                    }
                });
        }
        if (neckle != null) {
            view.getNeckleView()
                .getButton()
                .setText(neckle.getName());
            view.getNeckleView()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(neckle);
                    }
                });
        }
        if (ring1 != null) {
            view.getRing1View()
                .getButton()
                .setText(ring1.getName());
            view.getRing1View()
                .getButton()
                .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        eqItemInfo(ring1);
                    }
                });
        }

    }

    public Enchant getEnchant(Weapon w) {
        Enchant e = null;
        try {
            PreparedStatement stmt =
                db.getConn()
                .prepareStatement("select * from enchants,wenchfks where enchants.enchant_id = wenchfks.enchant_id and wenchfks.weapon_id = ?");
            stmt.setString(1, String.format("%d", w.getWeapon_id()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                e = new Enchant();
                e.setEnchant_id(rs.getInt("enchant_id"));
                e.setName(rs.getString("name"));
                e.setPower(rs.getInt("Power"));
                e.setType(rs.getInt("type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public Enchant getEnchant(Armor a) {
        Enchant e = null;
        try {
            PreparedStatement stmt =
                db.getConn()
                .prepareStatement("select * from enchants,aenchfks where enchants.enchant_id = aenchfks.enchant_id and aenchfks.armor_id = ?");
            stmt.setString(1, String.format("%d", a.getArmor_id()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                e = new Enchant();
                e.setEnchant_id(rs.getInt("enchant_id"));
                e.setName(rs.getString("name"));
                e.setPower(rs.getInt("Power"));
                e.setType(rs.getInt("type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public void eqItemInfo(Weapon w) {
        String info =
            "Name: " + w.getName() + "\n" + "Damage: " + w.getDamage() + "\n" + "Attack Speed: " + w.getAttackspeed() +
            "\n" + "Crit chance: " + w.getCritchance() * 100 + "%" + "\n" + "Value: " + w.getValue() + "\n" +
            "Level: " + w.getLevel() + "\n";
        Enchant e = getEnchant(w);
        String enchant = "";
        if (e != null) {
            enchant = "Obrazenia od " + e.getName() + " : " + e.getPower();
        }
        JOptionPane.showMessageDialog(view, info + enchant, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void eqItemInfo(Armor a) {
        String type = "light";
        if (a.getWeightType() == 0) {
            type = "light";
        } else if (a.getWeightType() == 1) {
            type = "medium";
        } else if (a.getWeightType() == 2) {
            type = "heavy";
        }
        String info =
            "Name: " + a.getName() + "\n" + "Defence: " + a.getDefence() + "\n" + "Type: " + type + "\n" + "Value: " +
            a.getValue() + "\n" + "Level: " + a.getLevel() + "\n";
        Enchant e = getEnchant(a);
        String enchant = "";
        if (e != null) {
            enchant = "Ochrona przed " + e.getName() + " : " + e.getPower();
        }
        JOptionPane.showMessageDialog(view, info + enchant, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void eqItemInfo(CommonItem w) {
        String info = "Name: " + w.getName() + "\n" + "Power: " + w.getPower() + "\n" + "Value: " + w.getValue() + "\n";

        JOptionPane.showMessageDialog(view, info, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void loadEquippedItems() {
        try {
            PreparedStatement stmt =
                db.getConn().prepareStatement("Select * from Equippeditems where character_id = ?");
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getCharacter_id()));
            ResultSet rs = stmt.executeQuery();
            if (!rs.next())
                return;
            //rs.next();
            eqItems = new EquippedItems();
            eqItems.setChar_id(rs.getInt("character_id"));
            eqItems.setChest(rs.getInt("Chest"));
            eqItems.setEq_id(rs.getInt("Equippeditems_id"));
            eqItems.setFeet(rs.getInt("Feet"));
            eqItems.setHands(rs.getInt("Hands"));
            eqItems.setHelmet(rs.getInt("Helmet"));
            eqItems.setLegs(rs.getInt("Legs"));
            eqItems.setNeckle(rs.getInt("Neckle"));
            eqItems.setOffhand(rs.getInt("Shield/Off-hand"));
            eqItems.setRing1(rs.getInt("Ring1"));
            eqItems.setShoulder(rs.getInt("Shoulder"));
            eqItems.setWeapon(rs.getInt("Weapon"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadWeapon() {
        try {
            PreparedStatement stmt = db.getConn().prepareStatement("select * from weapons where weapon_id = ?");
            stmt.setString(1, String.format("%d", eqItems.getWeapon()));
            ResultSet rs = stmt.executeQuery();
            if (!rs.next())
                return;
            weapon = new Weapon();
            weapon.setName(rs.getString("name"));
            weapon.setAttackspeed(rs.getInt("attackspeed"));
            weapon.setCritchance(rs.getDouble("critchance"));
            weapon.setDamage(rs.getInt("damage"));
            weapon.setIcon(rs.getBlob("icon"));
            weapon.setLevel(rs.getInt("level"));
            weapon.setType(rs.getInt("type"));
            weapon.setValue(rs.getInt("value"));
            weapon.setWeapon_id(rs.getInt("weapon_id"));
            weapon.setWeight(rs.getInt("weight"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadArmor(String query, int armor_id, int type) {
        try {
            PreparedStatement stmt = db.getConn().prepareStatement("select * from armors where armor_id = ?");
            stmt.setString(1, String.format("%d", armor_id));
            ResultSet rs = stmt.executeQuery();
            if (!rs.next())
                return;
            Armor armor = new Armor();
            if (type == 0) {
                helmet = new Armor();
                armor = helmet;
            } else if (type == 1) {
                chest = new Armor();
                armor = chest;
            } else if (type == 2) {
                legs = new Armor();
                armor = legs;
            } else if (type == 3) {
                feet = new Armor();
                armor = feet;
            } else if (type == 4) {
                hands = new Armor();
                armor = hands;
            } else if (type == 5) {
                shoulder = new Armor();
                armor = shoulder;
            } else if (type == 6) {
                neckle = new Armor();
                armor = neckle;
            } else if (type == 7) {
                ring1 = new Armor();
                armor = ring1;
            }
            System.out.println(armor_id);
            armor.setName(rs.getString("name"));
            armor.setDefence(rs.getInt("defence"));
            armor.setIcon(rs.getBlob("icon"));
            armor.setLevel(rs.getInt("level"));
            armor.setType(rs.getInt("type"));
            armor.setValue(rs.getInt("value"));
            armor.setArmor_id(rs.getInt("armor_id"));
            armor.setWeight(rs.getInt("weight"));
            armor.setWeightType(rs.getInt("Weighttype"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadAppropriateImage() {
        System.out.println(mainWindow.getSelectedChar().getAppearance());
        view.remove(view.getHeroImage());
        if (mainWindow.getSelectedChar().getAppearance() == 1) {
            view.setHeroImage(view.getHeroImage1());
        } else if (mainWindow.getSelectedChar().getAppearance() == 2) {
            view.setHeroImage(view.getHeroImage2());
        } else if (mainWindow.getSelectedChar().getAppearance() == 3) {
            view.setHeroImage(view.getHeroImage3());
        }
        view.add(view.getHeroImage());
        view.getHeroImage().setVisible(false);
        view.getHeroImage().setVisible(true);
    }

    public void equipWeapon(Weapon w, EqItem eq) {
        try {
            PreparedStatement stmt;
            if (w.getLevel() > mainWindow.getSelectedChar().getLevel()) {
                JOptionPane.showMessageDialog(view, "You have too low level!");
                return;
            }
            stmt = db.getConn().prepareStatement("update equippeditems set weapon = ? where equippeditems_id = ?");
            if (weapon == null) {
                stmt.setString(1, String.format("%d", w.getWeapon_id()));
                stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getEquippeditems_id()));
                weapon = w;
                eq.getEquip().setText("remove");
            } else if (weapon.getWeapon_id() == w.getWeapon_id()) {
                stmt.setString(1, "");
                stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getEquippeditems_id()));
                weapon = null;
                eq.getEquip().setText("equip");
            } else {
                JOptionPane.showMessageDialog(view, "First remove item then equip it");
                return;
            }
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshEquippedItems();

    }

    public void equipArmor(Armor a, EqItem eq, int type) {
        Armor armor = null;
        String query = "";
        if (type == 0) {
            armor = helmet;
            query = "update equippeditems set helmet = ? where equippeditems_id = ?";
        } else if (type == 1) {
            armor = chest;
            query = "update equippeditems set chest = ? where equippeditems_id = ?";
        } else if (type == 2) {
            armor = legs;
            query = "update equippeditems set legs = ? where equippeditems_id = ?";

        } else if (type == 3) {
            armor = feet;
            query = "update equippeditems set feet = ? where equippeditems_id = ?";

        } else if (type == 4) {
            armor = hands;
            query = "update equippeditems set hands = ? where equippeditems_id = ?";

        } else if (type == 5) {
            armor = shoulder;
            query = "update equippeditems set shoulder = ? where equippeditems_id = ?";

        } else if (type == 6) {
            armor = neckle;
            query = "update equippeditems set neckle = ? where equippeditems_id = ?";

        } else if (type == 7) {
            armor = ring1;
            query = "update equippeditems set ring1 = ? where equippeditems_id = ?";
        }

        if (a.getLevel() > mainWindow.getSelectedChar().getLevel()) {
            JOptionPane.showMessageDialog(view, "You have too low level!");
            return;
        }


        try {
            PreparedStatement stmt;
            stmt = db.getConn().prepareStatement(query);
            if (armor == null) {
                stmt.setString(1, String.format("%d", a.getArmor_id()));
                stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getEquippeditems_id()));
                if (type == 0) {
                    helmet = a;
                } else if (type == 1) {
                    chest = a;
                } else if (type == 2) {
                    legs = a;
                } else if (type == 3) {
                    feet = a;
                } else if (type == 4) {
                    hands = a;
                } else if (type == 5) {
                    shoulder = a;
                } else if (type == 6) {
                    neckle = a;
                } else if (type == 7) {
                    ring1 = a;
                }
                eq.getEquip().setText("remove");
            } else if (armor.getArmor_id() == a.getArmor_id()) {
                stmt.setString(1, "");
                stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getEquippeditems_id()));
                helmet = null;
                if (type == 0) {
                    helmet = null;
                } else if (type == 1) {
                    chest = null;
                } else if (type == 2) {
                    legs = null;
                } else if (type == 3) {
                    feet = null;
                } else if (type == 4) {
                    hands = null;
                } else if (type == 5) {
                    shoulder = null;
                } else if (type == 6) {
                    neckle = null;
                } else if (type == 7) {
                    ring1 = null;
                }
                eq.getEquip().setText("equip");
            } else {
                JOptionPane.showMessageDialog(view, "First remove item then equip it");
                return;
            }
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshEquippedItems();
    }
}
