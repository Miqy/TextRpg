package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.DbConn;

import view.FightView;
import view.MainWindow;

public class FightController {
    private MainWindow mainWindow;
    private DbConn db;
    private FightView view;

    private int health;
    private int attack;
    private int defence;
    private int crit;

    private int mHeal;
    private int mLvl;
    private int mAt;
    private int mDef;

    //mnoznik do expa
    private int step;

    public FightController(MainWindow mw, FightView fightView, DbConn db) {
        mainWindow = mw;
        this.db = db;
        view = fightView;

        loadAppropImage();
        loadCharStats();
        view.getReturnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                backToCharacter();
            }
        });
        view.getFightButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goToFight();
            }
        });
        view.getAttackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AttackMonster();
            }
        });
        view.getRunButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                health= -1;
                AttackMonster();
                returnFromFight();
            }
        });
    }


    public void updateTableCharacter() {
        try {
            PreparedStatement stmt =
                db.getConn()
                .prepareStatement("UPDATE \"G4_MIDOB\".\"CHARACTERS\" SET \"Level\" = ?, EXP = ? WHERE character_id = ?");
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getLevel()));
            stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getExp()));
            stmt.setString(3, String.format("%d", mainWindow.getSelectedChar().getCharacter_id()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AttackMonster() {
        int exp = 0;

        int charAttack = attack;
        int critica = (int) (Math.random() * 100);
        if (critica <= crit) {
            charAttack *= 2;
        }
        int dmgC = (int) (1 + 10 * (double) charAttack / mDef);
        int dmgP = (int) (1 + 10 * (double) mAt / defence);

        mHeal = (int) (mHeal - dmgC);
        health = (int) (health - dmgP);

        refreshStats();
        if (health <= 0) {
            mainWindow.getSelectedChar().setExp(0);
            JOptionPane.showMessageDialog(view, "You are dead!\n You have losted all your exp!", "Rpg",
                                          JOptionPane.INFORMATION_MESSAGE);
            returnFromFight();
            loadCharStats();
            updateTableCharacter();
            refreshStats();
            return;
        }
        if (mHeal <= 0) {
            exp = 100 * mLvl * step;
            mainWindow.getSelectedChar().setExp(mainWindow.getSelectedChar().getExp() + exp);
            //warunek levelowania
            if (mainWindow.getSelectedChar().getExp() >
                100 * mainWindow.getSelectedChar().getLevel() * mainWindow.getSelectedChar().getLevel()) {
                int e =
                    mainWindow.getSelectedChar().getExp() -
                    100 * mainWindow.getSelectedChar().getLevel() * mainWindow.getSelectedChar().getLevel();
                mainWindow.getSelectedChar().setLevel(mainWindow.getSelectedChar().getLevel() + 1);
                mainWindow.getSelectedChar().setExp(e);
                JOptionPane.showMessageDialog(view, "Level up!", "Rpg", JOptionPane.INFORMATION_MESSAGE);
            }
            dropItem();
            int choice =
                JOptionPane.showConfirmDialog(view,
                                              "You defeated monster!\n You get " + exp +
                                              " exp.\nDo you want to fight another one?", "Rpg",
                                              JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                getMonster();
            } else if (choice == JOptionPane.NO_OPTION) {
                returnFromFight();
            }
            updateTableCharacter();
            refreshStats();
            return;
        }
        

    }

    public void dropItem() {
        int digit = (int) (Math.random() * 4);
        PreparedStatement stmt;
        if (digit == 0) {
            try {
                stmt = db.getConn().prepareStatement("select * from weapons");
                ResultSet rs = stmt.executeQuery();
                ArrayList w = new ArrayList<Integer>();
                ArrayList names = new ArrayList<String>();
                while (rs.next()) {
                    w.add(rs.getInt("weapon_id"));
                    names.add(rs.getString("Name"));
                }
                int rand = (int) (Math.random() * w.size());

                stmt = db.getConn().prepareStatement("insert into weaponfks values(?,?)");
                stmt.setString(1, String.format("%d", w.get(rand)));
                stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getEquipment_id()));
                stmt.execute();
                JOptionPane.showMessageDialog(view, "You get " + names.get(rand), "Rpg",
                                              JOptionPane.INFORMATION_MESSAGE);
                stmt.close();
            } catch (SQLException e) {
            }

        } else if (digit == 1) {
            try {
                stmt = db.getConn().prepareStatement("select * from armors");
                ResultSet rs = stmt.executeQuery();
                ArrayList w = new ArrayList<Integer>();
                ArrayList names = new ArrayList<String>();
                while (rs.next()) {
                    w.add(rs.getInt("armor_id"));
                    names.add(rs.getString("Name"));
                }
                int rand = (int) (Math.random() * w.size());

                stmt = db.getConn().prepareStatement("insert into armorfks values(?,?)");
                stmt.setString(1, String.format("%d", w.get(rand)));
                stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getEquipment_id()));
                stmt.execute();
                JOptionPane.showMessageDialog(view, "You get " + names.get(rand), "Rpg",
                                              JOptionPane.INFORMATION_MESSAGE);
                stmt.close();
            } catch (SQLException e) {
            }
        }else if(digit == 2){
            try {
                stmt = db.getConn().prepareStatement("select * from commonitems");
                ResultSet rs = stmt.executeQuery();
                ArrayList w = new ArrayList<Integer>();
                ArrayList names = new ArrayList<String>();
                while (rs.next()) {
                    w.add(rs.getInt("commonitem_id"));
                    names.add(rs.getString("Name"));
                }
                int rand = (int) (Math.random() * w.size());

                stmt = db.getConn().prepareStatement("insert into commonfks values(?,?)");
                stmt.setString(1, String.format("%d", w.get(rand)));
                stmt.setString(2, String.format("%d", mainWindow.getSelectedChar().getEquipment_id()));
                stmt.execute();
                JOptionPane.showMessageDialog(view, "You get " + names.get(rand), "Rpg",
                                              JOptionPane.INFORMATION_MESSAGE);
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            stmt =
                db.getConn()
                .prepareStatement("select eqUser(?) from dual");
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getEquipment_id()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goToFight() {
        step = 0;
        view.getReturnButton().setVisible(false);
        view.getReturnButton().setEnabled(false);
        view.getFightButton().setVisible(false);
        getMonster();
        view.getAttackButton().setVisible(true);
        view.getRunButton().setVisible(true);
        view.getMonsterPanel().setVisible(true);
    }

    public void returnFromFight() {
        view.getReturnButton().setVisible(true);
        view.getReturnButton().setEnabled(true);
        view.getFightButton().setVisible(true);
        view.getAttackButton().setVisible(false);
        view.getRunButton().setVisible(false);
        view.getMonsterPanel().setVisible(false);
        health = 100 * mainWindow.getSelectedChar().getLevel();
    }

    public void getMonster() {
        JOptionPane.showMessageDialog(view, "Monster attacks!", "Rpg", JOptionPane.INFORMATION_MESSAGE);
        step++;
        String[] names = { "Dog","Rat", "Wolf", "Boar", "Goblin", "Bear", "Orc", "Dragon",};
        int[] levels = { 1, 1, 3, 5, 5, 10, 20, 50 };
        int healths[] = { 10, 5, 10, 50, 50, 100, 200, 2000 };
        int attacks[] = { 5, 10, 10, 20, 50, 100, 250, 1000 };
        int defences[] = { 1, 2, 5, 30, 50, 100, 150, 1000 };
        int index = (int) (Math.random() * levels.length);

        mAt = attacks[index];
        mDef = defences[index];
        mHeal = healths[index];
        mLvl = levels[index];

        view.getMonName().setText("Name: " + names[index] + "    ");
        view.getMonAtt().setText("Attack: " + mAt);
        view.getMonDef().setText("Defence: " + mDef);
        view.getMonHealth().setText("Health: " + mHeal);
        view.getMonLevel().setText("Level: " + mLvl);

        view.generateMonsterView();
    }

    public void refreshStats() {
        view.getMonAtt().setText("Attack: " + mAt);
        view.getMonDef().setText("Defence: " + mDef);
        view.getMonHealth().setText("Health: " + mHeal);
        view.getMonLevel().setText("Level: " + mLvl);

        view.getLevelLabel().setText("Level: " + mainWindow.getSelectedChar().getLevel());
        view.getHealthLabel().setText("Health: " + health);
        view.getAttLabel().setText("Attack: " + attack);
        view.getDefLabel().setText("Defence: " + defence);
        view.getCritLabel().setText("Crit Chance: " + crit + "%");
    }

    public void backToCharacter() {
        view.setVisible(false);
        mainWindow.remove(view);
        mainWindow.generateEqView();
    }

    public void loadCharStats() {
        health = 100 * mainWindow.getSelectedChar().getLevel();
        attack = 1;
        crit = 0;
        defence = 1;
        try {
            PreparedStatement stmt =
                db.getConn()
                .prepareStatement("select weapons.damage, weapons.critchance from weapons, equippedItems where weapons.weapon_id = equippedItems.WEAPON and equippedItems.EQUIPPEDITEMS_ID = ?");
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getEquippeditems_id()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                attack += rs.getInt("Damage");
                crit = (int) (rs.getDouble("Critchance") * 100);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.Helmet and equippedItems.EQUIPPEDITEMS_ID = ?");
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.Chest and equippedItems.EQUIPPEDITEMS_ID = ?");
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.legs and equippedItems.EQUIPPEDITEMS_ID = ?");
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.hands and equippedItems.EQUIPPEDITEMS_ID = ?");
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.feet and equippedItems.EQUIPPEDITEMS_ID = ?");
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.shoulder and equippedItems.EQUIPPEDITEMS_ID = ?");
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.neckle and equippedItems.EQUIPPEDITEMS_ID = ?");
        loadArmorToStats("select armors.defence from armors, equippedItems where armors.armor_id = equippedItems.ring1 and equippedItems.EQUIPPEDITEMS_ID = ?");

        attack = (int) (attack * (1.0 + mainWindow.getSelectedChar().getLevel() / 5.0));
        defence = (int) (defence * (1.0 + mainWindow.getSelectedChar().getLevel() / 5.0));

        view.getNickLabel().setText("Nickname: " + mainWindow.getSelectedChar().getNickname());
        view.getLevelLabel().setText("Level: " + mainWindow.getSelectedChar().getLevel());
        view.getHealthLabel().setText("Health: " + health);
        view.getAttLabel().setText("Attack: " + attack);
        view.getDefLabel().setText("Defence: " + defence);
        view.getCritLabel().setText("Crit Chance: " + crit + "%");
        System.out.println(health + " " + attack + " " + crit + " " + defence);

    }

    public void loadArmorToStats(String query) {
        try {
            PreparedStatement stmt = db.getConn().prepareStatement(query);
            stmt.setString(1, String.format("%d", mainWindow.getSelectedChar().getEquippeditems_id()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                defence += rs.getInt("defence");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadAppropImage() {
        view.getImgPanel().remove(view.getHeroImage());
        if (mainWindow.getSelectedChar().getAppearance() == 1) {
            view.setHeroImage(view.getHeroImage1());
        } else if (mainWindow.getSelectedChar().getAppearance() == 2) {
            view.setHeroImage(view.getHeroImage2());
        } else if (mainWindow.getSelectedChar().getAppearance() == 3) {
            view.setHeroImage(view.getHeroImage3());
        }
        view.getImgPanel().add(view.getHeroImage());
        view.getHeroImage().setVisible(false);
        view.getHeroImage().setVisible(true);
    }
}
