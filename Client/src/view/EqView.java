package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EqView extends JPanel{
    private JLabel nickLabel;
    
    private JPanel equippedPanel;
    private EquippedItemView weaponView;
    private EquippedItemView helmetView;
    private EquippedItemView chestView;
    private EquippedItemView legsView;
    private EquippedItemView feetView;
    private EquippedItemView handsView;
    private EquippedItemView shoulderView;
    private EquippedItemView neckleView;
    private EquippedItemView ring1View;
    
    private JPanel equipmentPanel;
    private JPanel eqContent;
    
    private JPanel imgPanel;
    private ImagePanel heroImage;
    private ImagePanel heroImage1;
    private ImagePanel heroImage2;
    private ImagePanel heroImage3;
    
    private JButton returnButton;
    private JButton fightButton;
    
    EqView(){
        heroImage1 = new ImagePanel(new File("hero1.png"));
        heroImage2 = new ImagePanel(new File("hero2.png"));
        heroImage3 = new ImagePanel(new File("hero3.png"));
        heroImage = heroImage1;
        
        nickLabel =  new JLabel("nickname:");
        
        imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.add(heroImage, BorderLayout.CENTER);
        
        equippedPanel = new JPanel();
        generateEquippedPanel();
        
        
        equipmentPanel = new JPanel();
        eqContent = new JPanel(new GridLayout(20,5));
        equipmentPanel.add(eqContent);
        JScrollPane scrollPane = new JScrollPane(equipmentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 300, 50); 
        
        returnButton = new JButton("Back to character select");
        fightButton = new JButton("Fight");
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuPanel.add(returnButton,BorderLayout.SOUTH);
        menuPanel.add(fightButton,BorderLayout.SOUTH);
        
        this.setLayout(new BorderLayout());
        this.add(nickLabel,BorderLayout.NORTH);
        this.add(imgPanel,BorderLayout.CENTER);
        this.add(equippedPanel,BorderLayout.WEST);
        this.add(new JLabel("Equipment"),BorderLayout.EAST);
        this.add(scrollPane, BorderLayout.EAST);
        this.add(menuPanel, BorderLayout.SOUTH);
             
    }
    
    public void generateEquippedPanel(){
        equippedPanel.setLayout(new BoxLayout(equippedPanel, BoxLayout.Y_AXIS));
        weaponView = new EquippedItemView("Weapon", "none");
        helmetView = new EquippedItemView("Helmet", "none");
        chestView = new EquippedItemView("Chest", "none");
        legsView = new EquippedItemView("Legs", "none");
        feetView = new EquippedItemView("Feet", "none");
        handsView = new EquippedItemView("Hands", "none");
        shoulderView = new EquippedItemView("Shoulder", "none");
        neckleView = new EquippedItemView("Neckle", "none");
        ring1View = new EquippedItemView("Ring1", "none");
        
        equippedPanel.add(weaponView);
        equippedPanel.add(helmetView);
        equippedPanel.add(chestView);
        equippedPanel.add(legsView);
        equippedPanel.add(feetView);
        equippedPanel.add(handsView);
        equippedPanel.add(shoulderView);
        equippedPanel.add(neckleView);
        equippedPanel.add(ring1View);
        
    }
    
    public void setNickLabel(JLabel nickLabel) {
        this.nickLabel = nickLabel;
    }

    public JLabel getNickLabel() {
        return nickLabel;
    }

    public void setEquippedPanel(JPanel equippedPanel) {
        this.equippedPanel = equippedPanel;
    }

    public JPanel getEquippedPanel() {
        return equippedPanel;
    }

    public void setWeaponView(EquippedItemView weaponView) {
        this.weaponView = weaponView;
    }

    public EquippedItemView getWeaponView() {
        return weaponView;
    }

    public void setHelmetView(EquippedItemView helmetView) {
        this.helmetView = helmetView;
    }

    public EquippedItemView getHelmetView() {
        return helmetView;
    }

    public void setChestView(EquippedItemView chestView) {
        this.chestView = chestView;
    }

    public EquippedItemView getChestView() {
        return chestView;
    }

    public void setLegsView(EquippedItemView legsView) {
        this.legsView = legsView;
    }

    public EquippedItemView getLegsView() {
        return legsView;
    }

    public void setFeetView(EquippedItemView feetView) {
        this.feetView = feetView;
    }

    public EquippedItemView getFeetView() {
        return feetView;
    }

    public void setHandsView(EquippedItemView handsView) {
        this.handsView = handsView;
    }

    public EquippedItemView getHandsView() {
        return handsView;
    }

    public void setShoulderView(EquippedItemView shoulderView) {
        this.shoulderView = shoulderView;
    }

    public EquippedItemView getShoulderView() {
        return shoulderView;
    }

    public void setNeckleView(EquippedItemView neckleView) {
        this.neckleView = neckleView;
    }

    public EquippedItemView getNeckleView() {
        return neckleView;
    }

    public void setRing1View(EquippedItemView ring1View) {
        this.ring1View = ring1View;
    }

    public EquippedItemView getRing1View() {
        return ring1View;
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

    public void setEquipmentPanel(JPanel equipmentPanel) {
        this.equipmentPanel = equipmentPanel;
    }

    public JPanel getEquipmentPanel() {
        return equipmentPanel;
    }

    public void setEqContent(JPanel eqContent) {
        this.eqContent = eqContent;
    }

    public JPanel getEqContent() {
        return eqContent;
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

    public void setReturnButton(JButton returnButton) {
        this.returnButton = returnButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public void setFightButton(JButton FightButton) {
        this.fightButton = FightButton;
    }

    public JButton getFightButton() {
        return fightButton;
    }
}
