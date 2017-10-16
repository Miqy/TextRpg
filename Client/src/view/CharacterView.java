package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import java.io.IOException;



import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Characterr;

public class CharacterView extends JPanel{
    private Characterr hero;
    
    private JPanel charPanel;
    private JPanel center;
    private JPanel crtPanel;

    
    private JLabel nickLabel;
    private JLabel levelLabel;
    private JLabel clanLabel;
    private JButton nextButton;
    private JButton goToCreationButton;
    private JButton deleteButton;
    private JButton selectButton;
    
    private JTextField nickInput;
    private JComboBox appearInput;
    private JButton createButton;
    private JButton backButton;
    private JButton logoutButton;
   
    private JPanel imgPanel;
    private ImagePanel heroImage;
    private ImagePanel heroImage1;
    private ImagePanel heroImage2;
    private ImagePanel heroImage3;
    
    public CharacterView(){
        heroImage1 = new ImagePanel(new File("hero1.png"));
        heroImage2 = new ImagePanel(new File("hero2.png"));
        heroImage3 = new ImagePanel(new File("hero3.png"));
        heroImage = heroImage1;
    }
    
    public void charsView(){
        hero = new Characterr();
        hero.setLevel(0);
        hero.setNickname("no Name");
        hero.setClan("no Clan");
        
        nickLabel = new JLabel("Nickname: " + hero.getNickname());
        levelLabel = new JLabel("Level: " + hero.getLevel());
        clanLabel = new JLabel("Clan: " + hero.getClan());
        nextButton = new JButton("Next");
        goToCreationButton = new JButton("Create character");
        deleteButton = new JButton("Delete character");
        selectButton = new JButton("Select character");    
        logoutButton = new JButton("Logout");
        
        this.setLayout(new GridLayout(1, 1));
        center = new JPanel();
        center.setLayout(new FlowLayout(FlowLayout.CENTER));
        charPanel = new JPanel();
        charPanel.setLayout(new BoxLayout(charPanel, BoxLayout.Y_AXIS));
        charPanel.add(nickLabel);
        charPanel.add(levelLabel);
        charPanel.add(clanLabel);
        charPanel.add(nextButton);
        charPanel.add(goToCreationButton);
        charPanel.add(deleteButton);
        charPanel.add(selectButton);
        charPanel.add(logoutButton);
        center.add(charPanel);
        this.add(center);
        imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.add(heroImage, BorderLayout.CENTER);
        this.add(imgPanel);
    }

   

    public void createCharView(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel nickLab = new JLabel("Nickname");
        JLabel appearLab = new JLabel("Appearance");
        
        
        nickInput = new JTextField();
        nickInput.setMaximumSize(new Dimension(150,20));
        appearInput = new JComboBox();
        appearInput.setMaximumSize(new Dimension(150,20));
        appearInput.addItem(1);
        appearInput.addItem(2);
        appearInput.addItem(3);
        
        createButton = new JButton("Create");
        backButton = new JButton("Back");
             
        this.setLayout(new GridLayout(1, 1));
        crtPanel = new JPanel();
        crtPanel.setLayout(new BoxLayout(crtPanel, BoxLayout.Y_AXIS));
        
        crtPanel.add(nickLab);
        crtPanel.add(nickInput);
        crtPanel.add(appearLab);
        crtPanel.add(appearInput);
        crtPanel.add(createButton);
        crtPanel.add(backButton);
        this.add(crtPanel);
        
        imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.add(heroImage, BorderLayout.CENTER);
        this.add(imgPanel);
    }
        

    public void setNextButton(JButton nextButton) {
        this.nextButton = nextButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public void setHero(Characterr hero) {
        this.hero = hero;
    }

    public Characterr getHero() {
        return hero;
    }

    public void setNickLabel(JLabel nickLabel) {
        this.nickLabel = nickLabel;
    }

    public JLabel getNickLabel() {
        return nickLabel;
    }

    public void setLevelLabel(JLabel levelLabel) {
        this.levelLabel = levelLabel;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    public void setClanLabel(JLabel clanLabel) {
        this.clanLabel = clanLabel;
    }

    public JLabel getClanLabel() {
        return clanLabel;
    }

    public void setNickInput(JTextField nickInput) {
        this.nickInput = nickInput;
    }

    public JTextField getNickInput() {
        return nickInput;
    }

    public void setAppearInput(JComboBox appearInput) {
        this.appearInput = appearInput;
    }

    public JComboBox getAppearInput() {
        return appearInput;
    }

    public void setCreateButton(JButton createButton) {
        this.createButton = createButton;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public void setCenter(JPanel center) {
        this.center = center;
    }

    public JPanel getCenter() {
        return center;
    }

    public void setGoToCreationButton(JButton goToCreationButton) {
        this.goToCreationButton = goToCreationButton;
    }

    public JButton getGoToCreationButton() {
        return goToCreationButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
    public JPanel getCrtPanel() {
        return crtPanel;
    }
    public JPanel getCharPanel() {
        return charPanel;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public void setCharPanel(JPanel charPanel) {
        this.charPanel = charPanel;
    }

    public void setCrtPanel(JPanel crtPanel) {
        this.crtPanel = crtPanel;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setSelectButton(JButton selectButton) {
        this.selectButton = selectButton;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public void setLogoutButton(JButton logoutButton) {
        this.logoutButton = logoutButton;
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

    public void setImgPanel(JPanel imgPanel) {
        this.imgPanel = imgPanel;
    }

    public JPanel getImgPanel() {
        return imgPanel;
    }
}
