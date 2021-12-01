/*

   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane

*/

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

public class BoardLayersListener extends JFrame {
    public static Gsystem gsys;
    public static LocationManager manager;
    public static Player curPlayer;
    public static int turnCounter;
    public static boolean turnDone;

    // JLabels
    JLabel boardlabel;
    JLabel cardlabel;
    JLabel playerlabel;
    JLabel mLabel;
    JLabel pLabel;
    JLabel pNameL;
    JLabel pCredL;
    JLabel pMoneyL;
    JLabel pLocL;
    JLabel pRankL;
    JLabel pReherL;

    //JButtons
    JButton bAct;
    JButton bRehearse;
    JButton bMove;
    JButton bUpgrade;
    JButton bEndTurn;
    JButton bmTrailer;
    JButton bmOffice;
    JButton bmTrainStation;
    JButton bmSecrectHideout;
    JButton bmChurch;
    JButton bmHotel;
    JButton bmMainStreet;
    JButton bmJail;
    JButton bmGeneralStore;
    JButton bmRanch;
    JButton bmBank;
    JButton bmSaloon;

    // JLayered Pane
    JLayeredPane bPane;

    // Constructor

    public BoardLayersListener() throws ParserConfigurationException {


        // Set the title of the JFrame
        super("Deadwood");

        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gsys = new Gsystem();
        manager = new LocationManager();
        // Create the JLayeredPane to hold the display, cards, dice and buttons
        bPane = getLayeredPane();

        // Create the deadwood board
        boardlabel = new JLabel();
        ImageIcon icon = new ImageIcon("Deadwood-Game/images/board.jpg");
        boardlabel.setIcon(icon);
        boardlabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

        // Add the board to the lowest layer
        bPane.add(boardlabel, new Integer(0));

        // Set the size of the GUI
        setSize(icon.getIconWidth() + 200, icon.getIconHeight() + 100);

        // Add a scene card to this room
        cardlabel = new JLabel();
        ImageIcon cIcon = new ImageIcon("cards/01.png");
        cardlabel.setIcon(cIcon);
        cardlabel.setBounds(20, 65, cIcon.getIconWidth() + 2, cIcon.getIconHeight());
        cardlabel.setOpaque(true);

        // Add the card to the lower layer
        bPane.add(cardlabel, new Integer(1));


        // Add a dice to represent a player.
        // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
        playerlabel = new JLabel();
        ImageIcon pIcon = new ImageIcon("r2.png");
        playerlabel.setIcon(pIcon);
        //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
        playerlabel.setBounds(114, 227, 46, 46);
        playerlabel.setVisible(false);
        bPane.add(playerlabel, new Integer(3));

        pLabel= new JLabel("PLAYER");
        pLabel.setBounds(icon.getIconWidth()+40, 200,100, 20);
        bPane.add(pLabel, new Integer(2));

        pNameL = new JLabel("Name");
        pNameL.setBounds(icon.getIconWidth() + 40, 225, 100, 20);
        bPane.add(pNameL, new Integer(2));

        pMoneyL = new JLabel("Money");
        pMoneyL.setBounds(icon.getIconWidth() + 40, 250, 100, 20);
        bPane.add(pMoneyL, new Integer(2));

        pCredL = new JLabel("Credits");
        pCredL.setBounds(icon.getIconWidth() + 40, 275, 100, 20);
        bPane.add(pCredL, new Integer(2));

        pLocL = new JLabel("Location");
        pLocL.setBounds(icon.getIconWidth() + 40, 300, 100, 20);
        bPane.add(pLocL, new Integer(2));

        pRankL = new JLabel("Rank");
        pRankL.setBounds(icon.getIconWidth() + 40, 325, 100, 20);
        bPane.add(pRankL, new Integer(2));

        pReherL = new JLabel("Rehearse level");
        pReherL.setBounds(icon.getIconWidth() + 40, 350, 100, 20);
        bPane.add(pReherL, new Integer(2));

        // Create the Menu for action buttons
        mLabel = new JLabel("MENU");
        mLabel.setBounds(icon.getIconWidth() + 40, 0, 100, 20);
        bPane.add(mLabel, new Integer(2));

        // Create Action buttons
        bAct = new JButton("ACT");
        bAct.setBackground(Color.white);
        bAct.setBounds(icon.getIconWidth() + 10, 30, 100, 20);
        bAct.addMouseListener(new boardMouseListener());

        bRehearse = new JButton("REHEARSE");
        bRehearse.setBackground(Color.white);
        bRehearse.setBounds(icon.getIconWidth() + 10, 60, 100, 20);
        bRehearse.addMouseListener(new boardMouseListener());

        bMove = new JButton("MOVE");
        bMove.setBackground(Color.white);
        bMove.setBounds(icon.getIconWidth() + 10, 90, 100, 20);
        bMove.addMouseListener(new boardMouseListener());

        bUpgrade = new JButton("UPGRADE");
        bUpgrade.setBackground(Color.white);
        bUpgrade.setBounds(icon.getIconWidth() + 10, 120, 100, 20);
        bUpgrade.addMouseListener(new boardMouseListener());

        bEndTurn = new JButton("END TURN");
        bEndTurn.setBackground(Color.white);
        bEndTurn.setBounds(icon.getIconWidth() + 10, 150, 150, 20);
        bEndTurn.addMouseListener(new boardMouseListener());

        int[] area;

        bmTrainStation = new JButton("Train Station");
        bmTrainStation.setName("Train Station");
        bmTrainStation.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Train Station")].getArea();
        bmTrainStation.setBounds(area[0], area[1], 100, 20);
        bmTrainStation.addMouseListener(new boardMouseListener());
        bmTrainStation.setVisible(false);

        bmSecrectHideout = new JButton("Secret Hideout");
        bmSecrectHideout.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Secret Hideout")].getArea();
        bmSecrectHideout.setBounds(area[0], area[1], 100, 20);
        bmSecrectHideout.addMouseListener(new boardMouseListener());
        bmSecrectHideout.setVisible(false);

        bmChurch = new JButton("Church");
        bmChurch.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Church")].getArea();
        bmChurch.setBounds(area[0], area[1], 100, 20);
        bmChurch.addMouseListener(new boardMouseListener());
        bmChurch.setVisible(false);

        bmHotel = new JButton("Hotel");
        bmHotel.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Hotel")].getArea();
        bmHotel.setBounds(area[0], area[1], 100, 20);
        bmHotel.addMouseListener(new boardMouseListener());
        bmHotel.setVisible(false);

        bmMainStreet = new JButton("Main Street");
        bmMainStreet.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Main Street")].getArea();
        bmMainStreet.setBounds(area[0], area[1], 100, 20);
        bmMainStreet.addMouseListener(new boardMouseListener());
        bmMainStreet.setVisible(false);

        bmJail = new JButton("Jail");
        bmJail.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Jail")].getArea();
        bmJail.setBounds(area[0], area[1], 100, 20);
        bmJail.addMouseListener(new boardMouseListener());
        bmJail.setVisible(false);

        bmGeneralStore = new JButton("General Store");
        bmGeneralStore.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("General Store")].getArea();
        bmGeneralStore.setBounds(area[0], area[1], 100, 20);
        bmGeneralStore.addMouseListener(new boardMouseListener());
        bmGeneralStore.setVisible(false);

        bmRanch = new JButton("Ranch");
        bmRanch.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Ranch")].getArea();
        bmRanch.setBounds(area[0], area[1], 100, 20);
        bmRanch.addMouseListener(new boardMouseListener());
        bmRanch.setVisible(false);

        bmBank = new JButton("Bank");
        bmBank.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Bank")].getArea();
        bmBank.setBounds(area[0], area[1], 100, 20);
        bmBank.addMouseListener(new boardMouseListener());
        bmBank.setVisible(false);

        bmSaloon = new JButton("Saloon");
        bmSaloon.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("Saloon")].getArea();
        bmSaloon.setBounds(area[0], area[1], 100, 20);
        bmSaloon.addMouseListener(new boardMouseListener());
        bmSaloon.setVisible(false);

        bmTrailer = new JButton("trailer");
        bmTrailer.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("trailer")].getArea();
        bmTrailer.setBounds(area[0], area[1], 100, 20);
        bmTrailer.addMouseListener(new boardMouseListener());
        bmTrailer.setVisible(false);

        bmOffice = new JButton("office");
        bmOffice.setBackground(Color.white);
        area = gsys.scenesArr[gsys.getInd("office")].getArea();
        bmOffice.setBounds(area[0], area[1], 100, 20);
        bmOffice.addMouseListener(new boardMouseListener());
        bmOffice.setVisible(false);

        // Place the action buttons in the top layer
        bPane.add(bAct, new Integer(2));
        bPane.add(bRehearse, new Integer(2));
        bPane.add(bMove, new Integer(2));
        bPane.add(bUpgrade, new Integer(2));
        bPane.add(bEndTurn, new Integer(2));
        bPane.add(bmTrainStation, new Integer(2));
        bPane.add(bmSecrectHideout, new Integer(2));
        bPane.add(bmChurch, new Integer(2));
        bPane.add(bmHotel, new Integer(2));
        bPane.add(bmMainStreet, new Integer(2));
        bPane.add(bmJail, new Integer(2));
        bPane.add(bmGeneralStore, new Integer(2));
        bPane.add(bmRanch, new Integer(2));
        bPane.add(bmBank, new Integer(2));
        bPane.add(bmSaloon, new Integer(2));
        bPane.add(bmOffice, new Integer(2));
        bPane.add(bmTrailer, new Integer(2));
        turnCounter = 0;
    }

    // This class implements Mouse Events

    class boardMouseListener implements MouseListener {

        // Code for the different button clicks
        public void mouseClicked(MouseEvent e) {
            boolean playerMoved = false;
            Player p = curPlayer;
            setPlayerInfo(p);

            if(e.getSource() == bEndTurn){

                System.out.println("Turn shoudl have ended");
                turnCounter++;
                if (manager.shoots == 1){
                    try {
                        gsys = new Gsystem();
                    } catch (ParserConfigurationException parserConfigurationException) {
                        parserConfigurationException.printStackTrace();
                    }
                    manager.days--;
                }
                if(manager.days==0){
                    //game over
                }
                enableButtons();

            }
            if (e.getSource() == bAct) {
                playerlabel.setVisible(true);
                Scene curScene = manager.scenes[manager.getInd(curPlayer.location)];
                int shotNum = curScene.takes-1;
                int budget = curScene.curCard.budget;
                int rank = curPlayer.getRank();
                int roll = (int) (Math.random() * 6 + 1);
                if (curPlayer.role.equals("card")) {
                    if (roll+rank >= budget) {
                        curScene.takes--;
                        curPlayer.setCredits(curPlayer.getCredits()+2);
                    }
                }
                else{
                    if (roll+rank >= budget) {

                        JLabel token = new JLabel();
                        ImageIcon tokIcon = new ImageIcon("Deadwood-game/shot.png");
                        int[] takeLocCur = curScene.takeLoc[shotNum];
                        token.setBounds(takeLocCur[0], takeLocCur[1], takeLocCur[2], takeLocCur[3]);
                        bPane.add(token);
                        curScene.takes--;
                        curPlayer.setCredits(curPlayer.getCredits()+1);
                        curPlayer.setMoney(curPlayer.getMoney()+1);
                    }
                    else{
                        curPlayer.setMoney(curPlayer.getMoney()+1);
                    }

                }
                if(curScene.takes == 0){
                    manager.sceneWrap(curScene);
                }
                disableButtons();
            }
            else if (e.getSource() == bRehearse) {
                curPlayer.setRehearseLvl(curPlayer.getRehearseLvl()+1);
                if(curPlayer.getRehearseLvl()>=6){
                    curPlayer.setRehearseLvl(6);
                }
                disableButtons();
            }
            else if(e.getSource() == bUpgrade){
                upgradeInfo();
            }
            else if (e.getSource() == bMove) {

                System.out.println("Move is Selected\n");
                // initialize JFrame object for the list of move choices

                // get currentLocation of player
                Scene curScene = manager.scenes[manager.getInd(p.getLocation())];

                int neighborLen = curScene.neighbors.length;
                String[] neighbors = curScene.getNeighbors();

                for (int g = 0; g < neighborLen; g++) {
                    if (neighbors[g].equals("Train Station")) {
                        bmTrainStation.setVisible(true);
                    }
                    if (neighbors[g].equals("Secret Hideout")) {
                        bmSecrectHideout.setVisible(true);
                    }
                    if (neighbors[g].equals("Church")) {
                        bmChurch.setVisible(true);
                    }
                    if (neighbors[g].equals("Hotel")) {
                        bmHotel.setVisible(true);
                    }
                    if (neighbors[g].equals("Main Street")) {
                        bmMainStreet.setVisible(true);
                    }
                    if (neighbors[g].equals("Jail")) {
                        bmJail.setVisible(true);
                    }
                    if (neighbors[g].equals("General Store")) {
                        bmGeneralStore.setVisible(true);
                    }
                    if (neighbors[g].equals("Ranch")) {
                        bmRanch.setVisible(true);
                    }
                    if (neighbors[g].equals("Bank")) {
                        bmBank.setVisible(true);
                    }
                    if (neighbors[g].equals("Saloon")) {
                        bmSaloon.setVisible(true);
                    }
                    if (neighbors[g].equals("trailer")) {
                        bmTrailer.setVisible(true);
                    }
                    if (neighbors[g].equals("office")) {
                        bmOffice.setVisible(true);
                    }

                }

            }
            else if (e.getSource() == bmTrainStation) {
                manager.movePlayer(p, "Train Station");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmSecrectHideout) {
                manager.movePlayer(p, "Secret Hideout");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmChurch) {
                manager.movePlayer(p, "Church");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmHotel) {
                manager.movePlayer(p, "Hotel");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmMainStreet) {
                manager.movePlayer(p, "Main Street");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmJail) {
                manager.movePlayer(p, "Jail");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmGeneralStore) {
                manager.movePlayer(p, "General Store");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmRanch) {
                manager.movePlayer(p, "Ranch");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmBank) {
                manager.movePlayer(p, "Bank");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmSaloon) {
                manager.movePlayer(p, "Saloon");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmTrailer) {
                manager.movePlayer(p, "trailer");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            else if (e.getSource() == bmOffice) {
                manager.movePlayer(p, "Office");
                hideButtons();
                disableButtons();
                playerMoved = true;
            }
            if(manager.scenes[manager.getInd(curPlayer.location)].numRoles > 0 && playerMoved == true){
                boolean moreOpts = true;
                int result = JOptionPane.showConfirmDialog(null, "Do you want to pick a role, if theres one possible?");
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        moreOpts = true;
                        break;
                    case JOptionPane.NO_OPTION:
                        moreOpts = false;
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        moreOpts = false;
                        break;
                }
                if(moreOpts){
                    moreOptions(manager.scenes[manager.getInd(curPlayer.getLocation())]);
                }

            }
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }
    private void setPlayerInfo(Player curPlayer){
        pNameL.setText(curPlayer.name);
        pReherL.setText(String.valueOf(curPlayer.getRehearseLvl()));
        pRankL.setText(String.valueOf(curPlayer.getRank()));
        pCredL.setText(String.valueOf(curPlayer.getCredits()));
        pMoneyL.setText(String.valueOf(curPlayer.getMoney()));
        pLocL.setText(curPlayer.getLocation());
    }
    private void moreOptions(Scene curScene){
        int numPartScene = curScene.numRolesInt;
        int numPartCard = curScene.curCard.numParts;
        String[] parts = new String[numPartCard+numPartScene];
        Part[] trueParts = new Part[numPartCard+numPartScene];
        int ind = 0;
        int i = 0;
        int k = 0;
        for (i = 0; i < numPartScene; i++) {
            trueParts[ind] = curScene.parts[i];
            if (!curScene.parts[i].taken && curScene.parts[i].level <= curPlayer.getRank()) {
                parts[ind] = curScene.parts[i].name;
                ind++;
            }
            else{
                parts[ind] = "Invalid: "+curScene.parts[i].name;
                ind++;
            }
        }
        for (k = 0; k < numPartCard; k++) {
            if (!curScene.curCard.parts[k].taken && curScene.curCard.parts[k].level <= curPlayer.getRank()) {
                trueParts[ind] = curScene.curCard.parts[k];
                parts[ind] = curScene.curCard.parts[k].name;
                ind++;
            }
            else {
                parts[ind] = "Invalid: " + curScene.curCard.parts[k].name;
                ind++;
            }
        }
        String choiceInd = (String) JOptionPane.showInputDialog(
                null,
                "What role would you like? Invalid roles are prohibited?",
                "Choose Role",
                JOptionPane.QUESTION_MESSAGE,
                null,
                parts,
                parts[parts.length-1]);
        int indCheck = 0;
        for(int h = 0; h<parts.length; h++){
            if(parts[h].equals(choiceInd)){
                indCheck = h;
            }
        }
        curScene.fillRole(indCheck, curPlayer.num);
        String opts;
        if(indCheck > numPartScene){
            opts = "card";
        }
        else{
            opts = "board";
        }
        curPlayer.fillRole(trueParts[indCheck], opts);
        curPlayer.setTokenLoc(trueParts[indCheck].area);
    }

    private void upgradeInfo() {
        int curRank = curPlayer.getRank();
        int curMon = curPlayer.getMoney();
        int curCred = curPlayer.getCredits();
        int[][] upgradeOpts = {{4, 6}, {10, 10}, {18, 15}, {28, 20}, {40, 25}};
        String[] ranks = new String[5];
        int ind = 0;
        for (int i = 0; i < 5; i++) {
            if (upgradeOpts[i][0] <= curMon || upgradeOpts[i][1] <= curCred) {
                ranks[ind] = String.valueOf(i);
                ind++;
            }
        }
        String choiceInd = (String) JOptionPane.showInputDialog(
                null,
                "Select upgrade level",
                "Upgrade Levels",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ranks,
                ranks[ranks.length - 1]);
        String[] options = {"",""};
        int choiceInt = Integer.parseInt(choiceInd);
        if (upgradeOpts[choiceInt][0] < curMon) {
            options[1] = "Credits";
        }
        else {
            options[1] = "Not enough";
        }
        if (upgradeOpts[choiceInt][1] < curMon) {
            options[0] = "Money";
        }
        else{
            options[0] = "Not enough!";
        }

        int result = JOptionPane.showOptionDialog(null, "Do you want to Use credits or cash?", "Upgrade choice", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        if(result == 0){
            curPlayer.setRank(choiceInt);
            curPlayer.setMoney(curPlayer.getMoney()-upgradeOpts[choiceInt][0]);
        }
        if(result == 0){
            curPlayer.setRank(choiceInt);
            curPlayer.setCredits(curPlayer.getCredits()-upgradeOpts[choiceInt][1]);
        }
    }
    private void hideButtons() {
        bmTrainStation.setVisible(false);
        bmSecrectHideout.setVisible(false);
        bmChurch.setVisible(false);
        bmHotel.setVisible(false);
        bmMainStreet.setVisible(false);
        bmJail.setVisible(false);
        bmGeneralStore.setVisible(false);
        bmRanch.setVisible(false);
        bmBank.setVisible(false);
        bmSaloon.setVisible(false);
        bmTrailer.setVisible(false);
        bmOffice.setVisible(false);
    }

    private void disableButtons(){
        bMove.setEnabled(false);
        bUpgrade.setEnabled(false);
        bAct.setEnabled(false);
        bRehearse.setEnabled(false);
        setPlayerInfo(curPlayer);
    }

    private void enableButtons(){
        bMove.setEnabled(true);
        bUpgrade.setEnabled(true);
        bAct.setEnabled(true);
        bRehearse.setEnabled(true);
        if(!curPlayer.location.equals("office")){
            bUpgrade.setEnabled(false);
        }
        if (curPlayer.role.equals("no")){
            bAct.setEnabled(false);
            bRehearse.setEnabled(false);
        }
        else{
            bMove.setEnabled(false);
        }
    }



    public static void main(String[] args) throws ParserConfigurationException {

        BoardLayersListener board = new BoardLayersListener();
        board.setVisible(true);

        // Take input from the user about number of players
        int playerCount = Integer.parseInt(JOptionPane.showInputDialog(board, "How many players? (2-8)"));
        while (playerCount < 2 || playerCount > 8) {
            playerCount = Integer.parseInt(JOptionPane.showInputDialog(board, "invalid choice, please choose a valid amount! (2-8)"));
        }


        Player[] Gplayers = new Player[playerCount];
        // Choose the correct game format based on playercount
        int[] area = {991, 248, 45, 45};
        if (playerCount == 2) {
            Player pOne = new Player(0, 0, "name", 1, 1);
            Player pTwo = new Player(0, 0, "name", 2, 1);
            pOne.setImage("Deadwood-game/images/dice/b1.png");
            pTwo.setImage("Deadwood-game/images/dice/r1.png");
            pOne.setTokenLoc(area);
            pTwo.setTokenLoc(area);
            Gplayers = new Player[]{pOne, pTwo};
        } else if (playerCount == 3) {
            Player pOne = new Player(0, 0, "name", 1, 1);
            Player pTwo = new Player(0, 0, "name", 2, 1);
            Player pThree = new Player(0, 0, "name", 3, 1);
            pOne.setTokenLoc(area);
            pTwo.setTokenLoc(area);
            pThree.setTokenLoc(area);
            Gplayers = new Player[]{pOne, pTwo, pThree};
        } else if (playerCount == 4) {
            Player pOne = new Player(0, 0, "name", 1, 1);
            Player pTwo = new Player(0, 0, "name", 2, 1);
            Player pThree = new Player(0, 0, "name", 3, 1);
            Player pFour = new Player(0, 0, "name", 4, 1);
            pOne.setTokenLoc(area);
            pTwo.setTokenLoc(area);
            pThree.setTokenLoc(area);
            pFour.setTokenLoc(area);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour};
        } else if (playerCount == 5) {
            Player pOne = new Player(0, 2, "name", 1, 1);
            Player pTwo = new Player(0, 2, "name", 2, 1);
            Player pThree = new Player(0, 2, "name", 3, 1);
            Player pFour = new Player(0, 2, "name", 4, 1);
            Player pFive = new Player(0, 2, "name", 5, 1);
            pOne.setTokenLoc(area);
            pTwo.setTokenLoc(area);
            pThree.setTokenLoc(area);
            pFour.setTokenLoc(area);
            pFive.setTokenLoc(area);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour, pFive};
        } else if (playerCount == 6) {
            Player pOne = new Player(0, 4, "name", 1, 1);
            Player pTwo = new Player(0, 4, "name", 2, 1);
            Player pThree = new Player(0, 4, "name", 3, 1);
            Player pFour = new Player(0, 4, "name", 4, 1);
            Player pFive = new Player(0, 4, "name", 5, 1);
            Player pSix = new Player(0, 4, "name", 6, 1);
            pOne.setTokenLoc(area);
            pTwo.setTokenLoc(area);
            pThree.setTokenLoc(area);
            pFour.setTokenLoc(area);
            pFive.setTokenLoc(area);
            pSix.setTokenLoc(area);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour, pFive, pSix};
        } else if (playerCount == 7) {
            Player pOne = new Player(0, 0, "name", 1, 2);
            Player pTwo = new Player(0, 0, "name", 2, 2);
            Player pThree = new Player(0, 0, "name", 3, 2);
            Player pFour = new Player(0, 0, "name", 4, 2);
            Player pFive = new Player(0, 0, "name", 5, 2);
            Player pSix = new Player(0, 0, "name", 6, 2);
            Player pSeven = new Player(0, 0, "name", 7, 2);
            pOne.setTokenLoc(area);
            pTwo.setTokenLoc(area);
            pThree.setTokenLoc(area);
            pFour.setTokenLoc(area);
            pFive.setTokenLoc(area);
            pSix.setTokenLoc(area);
            pSeven.setTokenLoc(area);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour, pFive, pSix, pSeven};
        } else {
            Player pOne = new Player(0, 0, "name", 1, 2);
            Player pTwo = new Player(0, 0, "name", 2, 2);
            Player pThree = new Player(0, 0, "name", 3, 2);
            Player pFour = new Player(0, 0, "name", 4, 2);
            Player pFive = new Player(0, 0, "name", 5, 2);
            Player pSix = new Player(0, 0, "name", 6, 2);
            Player pSeven = new Player(0, 0, "name", 7, 2);
            Player pEight = new Player(0, 0, "name", 8, 2);
            pOne.setTokenLoc(area);
            pTwo.setTokenLoc(area);
            pThree.setTokenLoc(area);
            pFour.setTokenLoc(area);
            pFive.setTokenLoc(area);
            pSix.setTokenLoc(area);
            pSeven.setTokenLoc(area);
            pEight.setTokenLoc(area);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour, pFive, pSix, pSeven, pEight};
        }

        // create a variable to keep track of the total turns, we will also use this as a mechanism to determine whos turn it is by using modular arithmetic!

        for (int i = 0; i < playerCount; i++) {
            Gplayers[i].name = JOptionPane.showInputDialog(board, "What is this players name?");
        }
        manager = new LocationManager(playerCount, board.gsys.scenesArr, Gplayers);
        manager.listOfPlayers = Gplayers;
        gsys = new Gsystem();
        boolean gameEnded = false;
        while (!gameEnded) {

            int playerTurn = (turnCounter % playerCount);
            curPlayer = manager.listOfPlayers[playerTurn];
            ImageIcon icon = new ImageIcon(curPlayer.getImage());
            board.playerlabel.setIcon(icon);
            board.playerlabel.setBounds(curPlayer.getTokenLoc()[0], curPlayer.getTokenLoc()[1], curPlayer.getTokenLoc()[2], curPlayer.getTokenLoc()[3]);
            board.playerlabel.setVisible(true);
            board.setPlayerInfo(curPlayer);
            String location = curPlayer.getLocation();




            /*
            if (playerSelection == 'u') {
                System.out.println("Welcome to the office! we hear you'd like to upgrade your player!");
                System.out.println("|Rank    |Dollars |Credits |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|2       |4       |5       |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|3       |10      |10      |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|4       |18      |15      |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|5       |28      |20      |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|6       |40      |25      |");
                System.out.println("Please choose rank or 10 to cancel:");
                int choiceInt = scan.nextInt();
                System.out.println("Please choose weather to use dollars or credits (c/d)");
                String currChoice = scan.nextLine();
                boolean upgradeDone = false;
                while (!upgradeDone) {
                    if (choiceInt == 10) {
                        upgradeDone = true;
                    } else {
                        int done = board.gsys.awardRank(curPlayer, choiceInt, currChoice);
                        if (done == 1) {
                            upgradeDone = true;
                        }
                    }
                }

            }
            if (playerSelection == 'r') {
                if (curPlayer.getRehearseLvl() < 6) {
                    curPlayer.setRehearseLvl(curPlayer.getRehearseLvl() + 1);
                } else {
                    System.out.println("Already rehearsed to teh maximum");
                }
            }
            if (playerSelection == 'm') {
                String location = curPlayer.location;
                int indScene = manager.getInd(location);
                String[] neighbors = board.gsys.scenesArr[indScene].neighbors;
                System.out.println("You could move to the following locations:");
                for (int j = 0; j < neighbors.length; j++) {
                    System.out.print(j + ". " + neighbors[j] + "  ");
                    if (board.gsys.scenesArr[manager.getInd(neighbors[j])].numRoles == 0) {
                        System.out.print("(no roles or wrapped)");
                    }
                }
                System.out.println("\n pick a number to make the corisponding selection.");
                int moveInt = Integer.parseInt(scan.nextLine());
                manager.movePlayer(curPlayer, neighbors[moveInt]);
                System.out.println("Pick a role? y/n");
                String confirm = scan.nextLine();
                if (confirm.equals("y")) {
                    System.out.println("Pick a role to fill:");
                    int locind = manager.getInd(curPlayer.location);
                    Scene curScene = board.gsys.scenesArr[locind];
                    Card curCard = curScene.curCard;
                    int numPartCard = curCard.numParts;
                    int numPartScene = curScene.numRoles;
                    int numParts = curScene.numRoles + curCard.numParts;
                    int ind = 0;
                    System.out.print("Roles available on the board: ");
                    int i;
                    int k;
                    for (i = 0; i < numPartScene; i++) {
                        if (!curScene.parts[i].taken && curScene.parts[i].level <= curPlayer.getRank()) {
                            System.out.print(ind + ".  " + curScene.parts[i].name + "  ");
                            ind++;
                        }
                    }
                    System.out.print("\nRoles available on the card: ");
                    for (k = 0; k < numPartCard; k++) {
                        if (!curCard.parts[k].taken && curCard.parts[k].level <= curPlayer.getRank()) {
                            System.out.print(ind + ".  " + curCard.parts[k].name + "  ");
                            ind++;
                        }
                    }
                    System.out.println("Please make selection by entering number next to part\n");
                    int roleInt = Integer.parseInt(scan.nextLine());
                    int permInt = roleInt;
                    if (roleInt > i) {
                        roleInt -= numPartScene;
                        curCard.parts[roleInt].taken = true;
                        curPlayer.role = curCard.parts[roleInt].name;
                        curPlayer.part = curCard.parts[roleInt];
                        curPlayer.setLevel(2);

                    } else {
                        curScene.parts[roleInt].taken = true;
                        curPlayer.role = curScene.parts[roleInt].name;
                        curPlayer.part = curScene.parts[roleInt];
                        curPlayer.setLevel(1);
                    }
                    curScene.filledRoles[roleInt] = curPlayer.getNum();
                }
            }

            if (playerSelection == 'w') {
                Part p = curPlayer.part;
                int ind = manager.getInd(curPlayer.location);
                Card card = board.gsys.scenesArr[ind].curCard;
                int budget = card.budget;
                int range = 6;
                Random rn = new Random();
                int n = range + 1;
                int r = rn.nextInt() % n;
                if (r >= budget) {
                    if (curPlayer.getLevel() == 2) {
                        curPlayer.setCredits(curPlayer.getCredits() + 2);
                        board.gsys.scenesArr[ind].takes -= 1;
                    }
                    if (curPlayer.getLevel() == 1) {
                        board.gsys.scenesArr[ind].takes -= 1;
                        curPlayer.setCredits(curPlayer.getCredits() + 1);
                        curPlayer.setMoney(curPlayer.getMoney() + 1);
                    }
                } else {
                    if (curPlayer.getLevel() == 1) {
                        curPlayer.setMoney(curPlayer.getMoney() + 1);
                    }
                }
                if (board.gsys.scenesArr[ind].takes == 0) {
                    board.gsys.scenesArr[ind].wrap();
                    for (int i = 0; i < board.gsys.scenesArr[ind].filledRoles.length; i++) {
                        Gplayers[board.gsys.scenesArr[ind].filledRoles[i + 1]].role = "no";
                        Gplayers[board.gsys.scenesArr[ind].filledRoles[i + 1]].setLevel(0);
                        Gplayers[board.gsys.scenesArr[ind].filledRoles[i + 1]].setRehearseLvl(0);
                        Gplayers[board.gsys.scenesArr[ind].filledRoles[i + 1]].part = null;
                    }
                }

             */
        }

    }


}
