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

public class BoardLayersListener extends JFrame {

    // JLabels
    JLabel boardlabel;
    JLabel cardlabel;
    JLabel playerlabel;
    JLabel mLabel;

    //JButtons
    JButton bAct;
    JButton bRehearse;
    JButton bMove;

    // JLayered Pane
    JLayeredPane bPane;

    // Constructor

    public BoardLayersListener() {

        // Set the title of the JFrame
        super("Deadwood");
        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        // Place the action buttons in the top layer
        bPane.add(bAct, new Integer(2));
        bPane.add(bRehearse, new Integer(2));
        bPane.add(bMove, new Integer(2));
    }

    // This class implements Mouse Events

    class boardMouseListener implements MouseListener {

        // Code for the different button clicks
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == bAct) {
                playerlabel.setVisible(true);
                System.out.println("Acting is Selected\n");
            } else if (e.getSource() == bRehearse) {
                System.out.println("Rehearse is Selected\n");
            } else if (e.getSource() == bMove) {
                System.out.println("Move is Selected\n");
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


    public static void main(String[] args) throws ParserConfigurationException {

        BoardLayersListener board = new BoardLayersListener();
        board.setVisible(true);

        // Take input from the user about number of players
        int playerCount = Integer.parseInt(JOptionPane.showInputDialog(board, "How many players?"));

        Player[] Gplayers = new Player[playerCount];
        // Choose the correct game format based on playercount
        if (playerCount == 2) {
            Player pOne = new Player(0, 0, "name", 1, 1);
            Player pTwo = new Player(0, 0, "name", 2, 1);
            Gplayers = new Player[]{pOne, pTwo};
        } else if (playerCount == 3) {
            Player pOne = new Player(0, 0, "name", 1, 1);
            Player pTwo = new Player(0, 0, "name", 2, 1);
            Player pThree = new Player(0, 0, "name", 3, 1);
            Gplayers = new Player[]{pOne, pTwo, pThree};
        } else if (playerCount == 4) {
            Player pOne = new Player(0, 0, "name", 1, 1);
            Player pTwo = new Player(0, 0, "name", 2, 1);
            Player pThree = new Player(0, 0, "name", 3, 1);
            Player pFour = new Player(0, 0, "name", 4, 1);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour};
        } else if (playerCount == 5) {
            Player pOne = new Player(0, 2, "name", 1, 1);
            Player pTwo = new Player(0, 2, "name", 2, 1);
            Player pThree = new Player(0, 2, "name", 3, 1);
            Player pFour = new Player(0, 2, "name", 4, 1);
            Player pFive = new Player(0, 2, "name", 5, 1);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour, pFive};
        } else if (playerCount == 6) {
            Player pOne = new Player(0, 4, "name", 1, 1);
            Player pTwo = new Player(0, 4, "name", 2, 1);
            Player pThree = new Player(0, 4, "name", 3, 1);
            Player pFour = new Player(0, 4, "name", 4, 1);
            Player pFive = new Player(0, 4, "name", 5, 1);
            Player pSix = new Player(0, 4, "name", 6, 1);
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour, pFive, pSix};
        } else if (playerCount == 7) {
            Player pOne = new Player(0, 0, "name", 1, 2);
            Player pTwo = new Player(0, 0, "name", 2, 2);
            Player pThree = new Player(0, 0, "name", 3, 2);
            Player pFour = new Player(0, 0, "name", 4, 2);
            Player pFive = new Player(0, 0, "name", 5, 2);
            Player pSix = new Player(0, 0, "name", 6, 2);
            Player pSeven = new Player(0, 0, "name", 7, 2);
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
            Gplayers = new Player[]{pOne, pTwo, pThree, pFour, pFive, pSix, pSeven, pEight};
        }

        // create a variable to keep track of the total turns, we will also use this as a mechanism to determine whos turn it is by using modular arithmetic!
        int turnCounter = 0;

        for (int i = 0; i < playerCount; i++) {
            Gplayers[i].name = JOptionPane.showInputDialog(board, "What is this players name?");
        }

        Gsystem gsys = new Gsystem(playerCount);
        LocationManager manager = new LocationManager(playerCount, gsys.scenesArr, Gplayers);
        manager.listOfPlayers = Gplayers;
        manager.playerLocationList = gsys.scenesArr;
        boolean gameEnded = false;
        while (!gameEnded) {
            int playerTurn = (turnCounter % playerCount) + 1;
            char playerSelection = 0;
            Player curPlayer = Gplayers[playerTurn - 1];
            if (curPlayer.location == "trailer" || curPlayer.location == "office" || gsys.scenesArr[manager.getInd(curPlayer.location)].numRoles == 0) {
                if (curPlayer.location == "office") {
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or upgrade \'u\'");
                    playerSelection = scan.nextLine().charAt(0);
                    // prompt again if player selection is not valid
                    while (!(playerSelection == 'm' || playerSelection == 'u')) {
                        System.out.println("Invalid selection, please choose again!");
                        System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move or upgrade \'u\'");
                        playerSelection = scan.next().charAt(0);
                    }
                } else {
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\'");
                    playerSelection = scan.nextLine().charAt(0);
                    // prompt again if player selection is not valid
                    while (!(playerSelection == 'm')) {
                        System.out.println("Invalid selection, please choose again!");
                        System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move");
                        playerSelection = scan.next().charAt(0);
                    }
                }
            } else {
                System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                playerSelection = scan.nextLine().charAt(0);
                // prompt again if player selection is not valid
                while (!(playerSelection == 'm' || playerSelection == 'w' || playerSelection == 'a' || playerSelection == 'r')) {
                    System.out.println("Invalid selection, please choose again!");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                // the current player object can be referenced by theLocationManager.listOfPlayers[playerTurn - 1]
                while (playerSelection == 'm' && !manager.listOfPlayers[playerTurn - 1].role.equals("no")) {
                    System.out.println("You cannot move while you currently have a role.");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                while (playerSelection == 'w' && (curPlayer.role.equals("yes")) || curPlayer.location == "trailer" || curPlayer.location == "office") {
                    System.out.println("You cannot work while you already have a role!");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                while (playerSelection == 'a' && manager.listOfPlayers[playerTurn - 1].role.equals("no")) {
                    System.out.println("You need to have a role in order to act");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                while (playerSelection == 'r' && manager.listOfPlayers[playerTurn - 1].role.equals("no")) {
                    System.out.println("You need to have a role in order to rehearse");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
            }
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
                        int done = gsys.awardRank(curPlayer, choiceInt, currChoice);
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
                String[] neighbors = gsys.scenesArr[indScene].neighbors;
                System.out.println("You could move to the following locations:");
                for (int j = 0; j < neighbors.length; j++) {
                    System.out.print(j + ". " + neighbors[j] + "  ");
                    if (gsys.scenesArr[manager.getInd(neighbors[j])].numRoles == 0) {
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
                    Scene curScene = gsys.scenesArr[locind];
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
                Card card = gsys.scenesArr[ind].curCard;
                int budget = card.budget;
                int range = 6;
                Random rn = new Random();
                int n = range + 1;
                int r = rn.nextInt() % n;
                if (r >= budget) {
                    if (curPlayer.getLevel() == 2) {
                        curPlayer.setCredits(curPlayer.getCredits() + 2);
                        gsys.scenesArr[ind].takes -= 1;
                    }
                    if (curPlayer.getLevel() == 1) {
                        gsys.scenesArr[ind].takes -= 1;
                        curPlayer.setCredits(curPlayer.getCredits() + 1);
                        curPlayer.setMoney(curPlayer.getMoney() + 1);
                    }
                } else {
                    if (curPlayer.getLevel() == 1) {
                        curPlayer.setMoney(curPlayer.getMoney() + 1);
                    }
                }
                if (gsys.scenesArr[ind].takes == 0) {
                    gsys.scenesArr[ind].wrap();
                    for (int i = 0; i < gsys.scenesArr[ind].filledRoles.length; i++) {
                        Gplayers[gsys.scenesArr[ind].filledRoles[i + 1]].role = "no";
                        Gplayers[gsys.scenesArr[ind].filledRoles[i + 1]].setLevel(0);
                        Gplayers[gsys.scenesArr[ind].filledRoles[i + 1]].setRehearseLvl(0);
                        Gplayers[gsys.scenesArr[ind].filledRoles[i + 1]].part = null;
                    }
                }

            }
            // code for successful selections
            //if(playerSelection = )

            turnCounter++;

        }


    }
}