package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Characterr;
import model.DbConn;

import view.CharacterView;
import view.MainWindow;

public class CharacterController {
    private MainWindow mainWindow;
    private CharacterView view;
    private DbConn db;
    
    private ArrayList<Characterr> characters = new ArrayList<>();
    private int currentChar;
    
    public CharacterController(MainWindow mainWindow, CharacterView view, DbConn db) {
        super();
        this.mainWindow = mainWindow;
        this.view = view;
        this.db = db;
        
        showListOfCharacters();
          
    }
    
    public void showListOfCharacters(){
        currentChar = -1;
        characters.clear();
        
        try {
            //db.getDBConnection();
            PreparedStatement stmt = db.getConn().prepareStatement("Select * from characters where user_id = ?");
            stmt.setString(1, String.format("%d",mainWindow.getLoggedUser().getUserId()));
            ResultSet set = stmt.executeQuery();
            while(set.next()){
                Characterr hero = new Characterr();
                hero.setNickname(set.getString("nickname"));
                hero.setLevel(set.getInt("Level"));
                hero.setAppearance(set.getInt("appearance"));
                hero.setCharacter_id(set.getInt("character_id"));
                hero.setClan(set.getString("clan"));
                hero.setEquipment_id(set.getInt("equipment_id"));
                hero.setEquippeditems_id(set.getInt("equippeditems_id"));
                hero.setServer(set.getInt("Server"));
                hero.setUser_id(set.getInt("User_id"));
                characters.add(hero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(characters.size() > 0){
            view.charsView();
            view.getNextButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    nextCharacter();
                }
            });
            view.getGoToCreationButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    goToCreatePanel();
                }
            });
            view.getDeleteButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    deleteChar();
                }
            });
            view.getSelectButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    selectChar();
                }
            });
            view.getLogoutButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    logout();
                }
            });
        }else{
            view.createCharView();
            view.getCreateButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    createCharacter();
                }
            });
            view.getBackButton().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    goToCharsView();
                }
            });
            view.getAppearInput().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    refreshImage();
                }
            });
        }
        nextCharacter();
    }
    
    
    public void nextCharacter(){
        if(characters.size() < 1){
            return;
        }
        currentChar  = (currentChar+1) % characters.size();
        if(characters.size() > 0){
            view.getNickLabel().setText("Nickname: " + characters.get(currentChar).getNickname());
            view.getLevelLabel().setText("Level: " + characters.get(currentChar).getLevel());
            view.getClanLabel().setText("Clan: " + characters.get(currentChar).getClan());
            
            
            int app = characters.get(currentChar).getAppearance();
            view.getImgPanel().remove(view.getHeroImage());
            view.getImgPanel().setVisible(false);
            if(app == 1){
                view.setHeroImage(view.getHeroImage1());
            }else if(app == 2){
                view.setHeroImage(view.getHeroImage2());
            }else if(app == 3){
                view.setHeroImage(view.getHeroImage3());
            }
            view.getImgPanel().setVisible(true);
            view.getImgPanel().add(view.getHeroImage());
            
                
        }
    }
    
    public void createCharacter(){
        try {
            db.getConn().setAutoCommit(false);
            Statement st = db.getConn().createStatement();
            ResultSet rs = st.executeQuery("Select * from characters");
            int counter = 0;
            while(rs.next()){
                if(counter < rs.getInt("Equipment_id")){
                    counter = rs.getInt("Equipment_id");
                }
            }
            counter++;
            System.out.println(counter);
            PreparedStatement stmt = db.getConn().prepareStatement("Insert into \"G4_MIDOB\".\"CHARACTERS\" (\"Level\", SERVER, APPEARANCE,EXP, EQUIPMENT_ID, EQUIPPEDITEMS_ID, USER_ID, NICKNAME) values(1,1,?,0,eq_id_seq.nextval,equip_id_seq.nextval,?,?)");  
            stmt.setString(1, view.getAppearInput().getSelectedItem().toString());
            //stmt.setString(2, String.format("%d",counter));
            //stmt.setString(3, String.format("%d",counter));
            stmt.setString(2,String.format("%d",mainWindow.getLoggedUser().getUserId()));
            stmt.setString(3,view.getNickInput().getText());
            stmt.execute();
            
            rs = st.executeQuery("Select * from characters");
            int createdCharId = -1;
            while(rs.next()){
                if(rs.getString("nickname").equals(view.getNickInput().getText())){
                    createdCharId = rs.getInt("character_id");
                }
            }
            System.out.println("Sukces0 " + createdCharId);
            if(createdCharId == -1) return;
            stmt = db.getConn().prepareStatement("INSERT INTO \"G4_MIDOB\".\"EQUIPMENTS\" (\"Size\", USED, MONEY, EQUIPMENT_ID, CHARACTER_ID) VALUES (100, 0, 0, ?, ?)");
            stmt.setString(1,String.format("%d",counter));
            stmt.setString(2, String.format("%d", createdCharId));
            stmt.execute();
            System.out.println("Sukces1");
            stmt = db.getConn().prepareStatement("INSERT INTO \"G4_MIDOB\".\"EQUIPPEDITEMS\" (EQUIPPEDITEMS_ID, CHARACTER_ID) VALUES (?, ?)");
            stmt.setString(1,String.format("%d",counter));
            stmt.setString(2, String.format("%d", createdCharId));
            stmt.execute();
            System.out.println("Sukces2");
            db.getConn().commit();
            db.getConn().setAutoCommit(true);
            
            JOptionPane.showMessageDialog(view, "Created character succesfully", "Character creation", JOptionPane.INFORMATION_MESSAGE);
            goToCharsView();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error occured while creating character", "Character creation", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void goToCreatePanel(){
        view.getImgPanel().setVisible(false);
        view.getImgPanel().remove(view.getHeroImage());
        view.getCharPanel().removeAll();
        view.getCharPanel().setVisible(false);
        view.getCenter().remove(view.getCharPanel());
        view.getCenter().remove(view.getImgPanel());
        view.getCenter().setVisible(false);
        view.getCenter().removeAll();
        view.removeAll();
        view.remove(view.getCenter());
        view.createCharView();
        view.getCreateButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createCharacter();
            }
        });
        view.getBackButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goToCharsView();
            }
        });
        view.getAppearInput().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshImage();
            }
        });
    }
    
    public void goToCharsView(){
        view.removeAll();
        view.getCrtPanel().setVisible(false);
        view.remove(view.getCrtPanel());
        showListOfCharacters();
        if(characters.size() == 0){
            logout();
        }
    }
    
    public void logout(){
        view.setVisible(false);
        mainWindow.remove(view);
        mainWindow.generateLoginView();
        mainWindow.setLoggedUser(null);
        this.characters.clear();
    }
    
    public void deleteChar(){
        int value = JOptionPane.showConfirmDialog(view, "Do you want delete character: " + characters.get(currentChar).getNickname());
        if(value == JOptionPane.OK_OPTION){
            System.out.println("Usuwanie" + characters.get(currentChar).getNickname());
            int hero = characters.get(currentChar).getCharacter_id();
            try {
                db.getConn().setAutoCommit(false);
                PreparedStatement stmt = db.getConn().prepareStatement("delete from equipments where character_id = ?");
                stmt.setString(1, String.format("%d",hero));
                stmt.execute();
                stmt = db.getConn().prepareStatement("delete from equippeditems where character_id = ?");
                stmt.setString(1, String.format("%d",hero));
                stmt.execute();
                stmt = db.getConn().prepareStatement("delete from characters where character_id = ?");
                stmt.setString(1, String.format("%d",hero));
                stmt.execute();
                
                db.getConn().commit();
                db.getConn().setAutoCommit(true);
                characters.remove(currentChar);
                currentChar--;
                nextCharacter();
                JOptionPane.showMessageDialog(view, "Deleted character succesfully!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                if(characters.size() == 0){
                    goToCreatePanel();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error occured while deleting character!!", "Delete", JOptionPane.ERROR_MESSAGE);
            }

        }
        
    }
    
    public void refreshImage(){
        int app = (int)view.getAppearInput().getSelectedItem();
        view.getImgPanel().remove(view.getHeroImage());
        view.getImgPanel().setVisible(false);
        if(app == 1){
            view.setHeroImage(view.getHeroImage1());
        }else if(app == 2){
            view.setHeroImage(view.getHeroImage2());
        }else if(app == 3){
            view.setHeroImage(view.getHeroImage3());
        }
        view.getImgPanel().setVisible(true);
        view.getImgPanel().add(view.getHeroImage());
    }
    
    public void selectChar(){
        mainWindow.setSelectedChar(characters.get(currentChar));
        view.setVisible(false);
        mainWindow.remove(view);
        mainWindow.generateEqView();
    }
}
