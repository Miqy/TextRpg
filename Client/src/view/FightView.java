package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FightView extends JPanel {

    private JPanel menuPanel;
    private JButton returnButton;
    private JButton fightButton;

    private JPanel statPanel;
    private JLabel nickLabel;
    private JLabel healthLabel;
    private JLabel levelLabel;
    private JLabel attLabel;
    private JLabel defLabel;
    private JLabel critLabel;

    private JPanel atPanel;
    private JButton attackButton;
    private JButton runButton;

    private JPanel monsterPanel;
    private JLabel monName;
    private JLabel monHealth;
    private JLabel monLevel;
    private JLabel monAtt;
    private JLabel monDef;
    private ImagePanel monImg;

    private JPanel imgPanel;
    private ImagePanel heroImage;
    private ImagePanel heroImage1;
    private ImagePanel heroImage2;
    private ImagePanel heroImage3;

    public FightView() {
        monImg = new ImagePanel(new File("monster.png"));
        heroImage1 = new ImagePanel(new File("hero1.png"));
        heroImage2 = new ImagePanel(new File("hero2.png"));
        heroImage3 = new ImagePanel(new File("hero3.png"));
        heroImage = heroImage1;

        this.setLayout(new BorderLayout());

        menuPanel = new JPanel(new FlowLayout());
        returnButton = new JButton("Back");
        fightButton = new JButton("Fight");
        menuPanel.add(fightButton, BorderLayout.NORTH);
        menuPanel.add(returnButton, BorderLayout.CENTER);

        statPanel = new JPanel(new GridLayout(15, 1));
        nickLabel = new JLabel("Nickname: " + "null");
        healthLabel = new JLabel("Health: 100");
        levelLabel = new JLabel("Level: null");
        attLabel = new JLabel("Attack: 0");
        defLabel = new JLabel("Defence: 0");
        critLabel = new JLabel("Crit chance: 0%");


        statPanel.add(nickLabel);
        statPanel.add(healthLabel);
        statPanel.add(levelLabel);
        statPanel.add(attLabel);
        statPanel.add(defLabel);
        statPanel.add(critLabel);

        imgPanel = new JPanel(new BorderLayout());
        atPanel = new JPanel(new FlowLayout());
        attackButton = new JButton("Attack");
        runButton = new JButton("Skip");
        atPanel.add(attackButton);
        atPanel.add(runButton);
        attackButton.setVisible(false);
        runButton.setVisible(false);

        imgPanel.add(heroImage, BorderLayout.CENTER);
        imgPanel.add(statPanel, BorderLayout.WEST);
        imgPanel.add(atPanel, BorderLayout.SOUTH);

        //monster view
        monName = new JLabel("NAME");
        monHealth = new JLabel("HEALTH: 100");
        monLevel = new JLabel("LEVEL");
        monAtt = new JLabel("ATTACK: 0");
        monDef = new JLabel("DEFENCE: 0");
        generateMonsterView();
        monsterPanel.setVisible(false);


        this.add(menuPanel, BorderLayout.NORTH);
        this.add(imgPanel, BorderLayout.CENTER);

        //this.add(monImg, BorderLayout.CENTER);

    }


    public void setRunButton(JButton runButton) {
        this.runButton = runButton;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public void generateMonsterView() {
        if (monsterPanel != null) {
            this.remove(monsterPanel);
        }
        monsterPanel = new JPanel(new GridLayout(15, 1));
        monsterPanel.add(new JLabel("Monster"));
        monsterPanel.add(monName);
        monsterPanel.add(monHealth);
        monsterPanel.add(monLevel);
        monsterPanel.add(monAtt);
        monsterPanel.add(monDef);
        this.add(monsterPanel, BorderLayout.EAST);
    }

    public void setAtPanel(JPanel atPanel) {
        this.atPanel = atPanel;
    }

    public JPanel getAtPanel() {
        return atPanel;
    }

    public void setAttackButton(JButton attackButton) {
        this.attackButton = attackButton;
    }

    public JButton getAttackButton() {
        return attackButton;
    }

    public void setMonsterPanel(JPanel monsterPanel) {
        this.monsterPanel = monsterPanel;
    }

    public JPanel getMonsterPanel() {
        return monsterPanel;
    }

    public void setMonName(JLabel monName) {
        this.monName = monName;
    }

    public JLabel getMonName() {
        return monName;
    }

    public void setMonHealth(JLabel monHealth) {
        this.monHealth = monHealth;
    }

    public JLabel getMonHealth() {
        return monHealth;
    }

    public void setMonLevel(JLabel monLevel) {
        this.monLevel = monLevel;
    }

    public JLabel getMonLevel() {
        return monLevel;
    }

    public void setMonAtt(JLabel monAtt) {
        this.monAtt = monAtt;
    }

    public JLabel getMonAtt() {
        return monAtt;
    }

    public void setMonDef(JLabel monDef) {
        this.monDef = monDef;
    }

    public JLabel getMonDef() {
        return monDef;
    }

    public void setMonImg(ImagePanel monImg) {
        this.monImg = monImg;
    }

    public ImagePanel getMonImg() {
        return monImg;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setStatPanel(JPanel statPanel) {
        this.statPanel = statPanel;
    }

    public JPanel getStatPanel() {
        return statPanel;
    }

    public void setNickLabel(JLabel nickLabel) {
        this.nickLabel = nickLabel;
    }

    public JLabel getNickLabel() {
        return nickLabel;
    }

    public void setHealthLabel(JLabel healthLabel) {
        this.healthLabel = healthLabel;
    }

    public JLabel getHealthLabel() {
        return healthLabel;
    }

    public void setLevelLabel(JLabel levelLabel) {
        this.levelLabel = levelLabel;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    public void setAttLabel(JLabel attLabel) {
        this.attLabel = attLabel;
    }

    public JLabel getAttLabel() {
        return attLabel;
    }

    public void setDefLabel(JLabel defLabel) {
        this.defLabel = defLabel;
    }

    public JLabel getDefLabel() {
        return defLabel;
    }

    public void setCritLabel(JLabel critLabel) {
        this.critLabel = critLabel;
    }

    public JLabel getCritLabel() {
        return critLabel;
    }

    public void setReturnButton(JButton returnButton) {
        this.returnButton = returnButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public void setFightButton(JButton fightButton) {
        this.fightButton = fightButton;
    }

    public JButton getFightButton() {
        return fightButton;
    }

    public void setImgPanel(JPanel imgPanel) {
        this.imgPanel = imgPanel;
    }

    public JPanel getImgPanel() {
        return imgPanel;
    }

    public void setHeroImage(ImagePanel heroImage) {
        this.heroImage = heroImage;
    }

    public ImagePanel getHeroImage() {
        return heroImage;
    }

    public void setHeroImage1(ImagePanel heroImage1) {
        this.heroImage1 = heroImage1;
    }

    public ImagePanel getHeroImage1() {
        return heroImage1;
    }

    public void setHeroImage2(ImagePanel heroImage2) {
        this.heroImage2 = heroImage2;
    }

    public ImagePanel getHeroImage2() {
        return heroImage2;
    }

    public void setHeroImage3(ImagePanel heroImage3) {
        this.heroImage3 = heroImage3;
    }

    public ImagePanel getHeroImage3() {
        return heroImage3;
    }
}
