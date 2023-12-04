package view;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import control.GameController;
import model.effects.*;
import model.world.*;
import model.abilities.*;
import engine.Game;
import engine.Player;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;

public class GamePage extends JPanel implements ActionListener{
	
	private static PlayerData p1Data;
	private static PlayerData p2Data;
	private static Board board;
	
	private static JPanel north;	//abilities west, next champ east
	private static JPanel left;	//player1 Data
	private static JPanel right;	//player2 Data
	private static JPanel south;	//attack panel west, move panel east, end turn center
	private static JPanel center;	//current champ south
	
	
	private static JPanel abilities;
	private static ChampAbilities abilitiesP;
	
	private JPanel nextCh;
	private JPanel attack;
	private JPanel move;
	private MovePanel moveP;
	private static JPanel endTurn;
	private static JPanel currCh;
	
	private static JPanel turns;
	private JPanel data;
	private static CurrChData currData;
	private static JPanel turnOrder;
	private static GameController control;
	
	
	private static Game g;
	
	
	public PlayerData getP1Data() {
		return p1Data;
	}



//	public JPanel getData() {
//		return data;
//	}
//
//	public void setData(JPanel data) {
//		this.data = data;
//	}

	public PlayerData getP2Data() {
		return p2Data;
	}

	
//	public void setP2Data(JPanel p2Data) {
//		this.p2Data = p2Data;
//	}

//	public JPanel getCurrData() {
//		return currData;
//	}
//
//	public void setCurrData(JPanel currData) {
//		this.currData = currData;
//	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public static Game getG() {
		return g;
	}

	public void setG(Game g) {
		this.g = g;
	}


	
	
	
	public GamePage(Game g){
		
		this.g = g;
		
		this.setLayout(new BorderLayout());
		
		this.setBackground(MainView.getC());
		
		ArrayList<Champion> champs = g.getAvailableChampions();
		
		Toolkit t = Toolkit.getDefaultToolkit();
		int x = (int) t.getScreenSize().getWidth();
		int y = (int) t.getScreenSize().getHeight();
		
		
		//-----------------bg---------------------------
		
		ImagePanel img = new ImagePanel("names.jpg", x, y);
		img.setLayout(new BorderLayout());
		this.add(img);
		
		//-----------------center----------------------
		center= new JPanel();
		//center.setBackground(new Color(0,0,0,0));
		center.setOpaque(false);
		center.setLayout(new BorderLayout());
		//center.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				
		board = new Board(g);
				
		currCh = new JPanel();
		//currCh.setBackground(new Color(0,0,0,0));
		currCh.setOpaque(false);
		currCh.setPreferredSize(new Dimension(20,100));
		//currCh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				
	//	center.add(currCh, BorderLayout.SOUTH);
		center.add(board);
				
		//--------------------------------------------------
		
		
		//----------------north----------------------
		north = new JPanel();
		//north.setBackground(new Color(0,0,0,0));
		north.setOpaque(false);
		north.setPreferredSize(new Dimension(200,200));
		north.setLayout(new BorderLayout());
	//	north.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		
//		JPanel abilitiesP = new JPanel();
		abilitiesP = new ChampAbilities(g.getCurrentChampion());
		//abilitiesP.setPreferredSize(new Dimension(500,200));
		
		north.add(abilitiesP, BorderLayout.WEST);
		
		nextCh = new JPanel();
		//nextCh.setBackground(new Color(0,0,0,0));
		nextCh.setOpaque(false);
		//nextCh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		nextCh.setPreferredSize(new Dimension(500,100));
		north.add(nextCh, BorderLayout.EAST);
		
		
		
		currCh = new JPanel();
		currCh.setOpaque(false);
		//currCh.setBackground(new Color(0,0,0,0));
		//currCh.setOpaque(false);
		currCh.setLayout(new BorderLayout());
		
		CurrChData currData = new CurrChData(g.getCurrentChampion());
		currData.setOpaque(false);
		
//		currData = new JLabel(g.getCurrentChampion().getName());
//		currData.setFont(new Font("Verdana", Font.BOLD, 30));
//		currData.setOpaque(false);
//		currData.setForeground(Color.white);
		
		currCh.add(currData);
		north.add(currCh);
		
		
		turnOrder = new JPanel();
		turnOrder.setOpaque(false);
		turnOrder.setLayout(new GridBagLayout());
		
		turns = new JPanel();
		turns.setOpaque(false);
		turns.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
		
		updatePQ();
		
		turnOrder.add(turns);
		
		north.add(turnOrder, BorderLayout.EAST);
		
		currCh.repaint();
		currCh.revalidate();
		
		
		
		//---------------------------------------------
		
		
		
		
		//-----------------right---------------------
		right = new JPanel();
		//right.setBackground(new Color(0xf57ef9));
		right.setOpaque(false);
		right.setPreferredSize(new Dimension(500,20));
		
//		ImagePanel p2Panel = new ImagePanel("panel2.png", 400,500);
//		//p2Panel.setLayout(new GridBagLayout());
//		//p2Panel.setOpaque(false);
		right.setLayout(new BorderLayout());
		
		//right.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		p2Data = new PlayerData(g.getSecondPlayer(), g);
		
	
		//p2Panel.add(p2Data);
		right.add(p2Data);
		
//		JPanel l = new JPanel();
//		l.setBackground(Color.lightGray);
//		l.setPreferredSize(new Dimension(100,20));
//		right.add(l, BorderLayout.WEST);
//		
//		JPanel r = new JPanel();
//		r.setBackground(Color.lightGray);
//		r.setPreferredSize(new Dimension(100,20));
//		right.add(r, BorderLayout.EAST);
		
		//---------------------------------------------
		
		
		
		
		//-----------------left---------------------
		left = new JPanel();
		left.setOpaque(false);
		//left.setBackground(new Color(0,0,0,0));
		left.setPreferredSize(new Dimension(500,20));
		left.setLayout(new BorderLayout());
		//left.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		p1Data = new PlayerData(g.getFirstPlayer(), g);
		left.add(p1Data);
		
//		JPanel l2 = new JPanel();
//		l2.setBackground(Color.lightGray);
//		l2.setPreferredSize(new Dimension(100,20));
//		left.add(l2, BorderLayout.WEST);
//		
//		JPanel r2= new JPanel();
//		r2.setBackground(Color.lightGray);
//		r2.setPreferredSize(new Dimension(100,20));
//		left.add(r2, BorderLayout.EAST);
		
		//---------------------------------------------
		
		
		
		
		//----------------south---------------------
		south = new JPanel();
		//south.setBackground(new Color(0,0,0,0));
		south.setOpaque(false);
		south.setPreferredSize(new Dimension(20,200));
		south.setLayout(new BorderLayout());
		//south.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		
		
		attack = new AttackPanel();
		//attack.setBackground(new Color(0,0,0,0));
		attack.setOpaque(false);
		//attack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		attack.setPreferredSize(new Dimension(500,20));
		south.add(attack, BorderLayout.WEST);
		
		endTurn = new JPanel();
		//endTurn.setBackground(new Color(0,0,0,0));
		endTurn.setOpaque(false);
		endTurn.setLayout(new GridBagLayout());
		//endTurn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		
		
		JButton endBtn = new JButton();
		//endBtn.setFont(new Font("Verdana", Font.BOLD, 20));
		endBtn.setPreferredSize(new Dimension(250,150));
		endBtn.setFocusable(false);
		
		endBtn.setBorderPainted(false);
		endBtn.setContentAreaFilled(false);
		Image endImg = new ImageIcon("end2.png").getImage();
		endBtn.setIcon(new ImageIcon(endImg.getScaledInstance(250, 150, Image.SCALE_SMOOTH)));
	
		
		
		endBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameController.endTurn();
				
			}
			
		});
		endTurn.add(endBtn);
		
		south.add(endTurn);
		
		move = new JPanel();
		moveP = new MovePanel(g, board);
		moveP.setPreferredSize(new Dimension(300,190));
		moveP.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		
		move.setLayout(new BorderLayout());
		//move.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		move.setPreferredSize(new Dimension(500,20));
		//move.setBackground(new Color(0,0,0,0));
		move.setOpaque(false);
		//move.setBackground(new Color(0xA6FAD1));
		move.add(moveP, BorderLayout.EAST);
		south.add(move, BorderLayout.EAST);
		//---------------------------------------------
		
		
		
		
		
		//-------------------data-----------------------
//		
//		data = new JPanel();
//		//data.setLayout(new GridLayout(2,0));
//		data.setPreferredSize(new Dimension(400,300));
//		data.setBackground(Color.lightGray);
//		
		
		
		
		//data.add(p1Data);
		//data.add(p2Data);
		
		//-------------------------------------------------
		
		
		//---------------------current Champion Data---------------------
		
//		currData = new JPanel();
//		currData.setPreferredSize(new Dimension(300,200));
//		currData.setBackground(Color.lightGray);
//		currData.setBorder(BorderFactory.createLineBorder(Color.black, 2));
//		
//		
		
		
		//------------------------------------------
		
		//----------------board ------------------
//		board = new Board(g);
//		board.setBounds(550, 150, 700, 700);
//		
//		
		
		//---------------------------------------
		
		img.add(center, BorderLayout.CENTER);
		img.add(left, BorderLayout.WEST);
		img.add(right, BorderLayout.EAST);
		img.add(north, BorderLayout.NORTH);
		img.add(south, BorderLayout.SOUTH);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
		
		
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1600,900);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		Game g = new Game(new Player("Rowaida"), new Player("Zeina"));
		try {
			g.loadAbilities("Abilities.csv");
			g.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(0));
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(1));
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(2));
		
		g.getFirstPlayer().setLeader(g.getFirstPlayer().getTeam().get(0));
		
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(3));
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(4));
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(5));
		
		g.getSecondPlayer().setLeader(g.getSecondPlayer().getTeam().get(0));
		
		
		Game g2 = new Game(g.getFirstPlayer(), g.getSecondPlayer());
		
		GamePage x = new GamePage(g2);
		x.getControl().setGame(g2);
	//	x.getControl().setView((GameView2)f);
		//x.getControl().getView().setGamePage(x);
		
		f.add(new GamePage(g2));
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			Champion c = g.getCurrentChampion();
			int x = (int) c.getLocation().getX();
			int y = (int) c.getLocation().getY();
			
			int newX;
			int newY;
			Cell cell;
			
			if(e.getSource() == moveP.getUpBtn()){
				g.move(Direction.UP);
				newX = x-1;
				newY = y;
			}
			
			else if (e.getSource() == moveP.getRightBtn()){
				g.move(Direction.RIGHT);
				newX = x;
				newY = y+1;
			}
			
			else if (e.getSource() == moveP.getLeftBtn()){
				g.move(Direction.LEFT);
				newX = x;
				newY = y-1;
			}
			
			else {
				g.move(Direction.DOWN);
				newX = x+1;
				newY = y;
			}
			
			
			board.getMap()[x][y].clear();
			board.getMap()[newX][newY].addChampion(c);
			
			
		} catch (NotEnoughResourcesException e1) {
			JOptionPane.showMessageDialog(null, "You don't have enough action points to move", "Not Enough Resources", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (UnallowedMovementException e2) {
			JOptionPane.showMessageDialog(null, "You are not in a condition to move", "Unallowed Movement", JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}
		
	}

	
	public static void updatePlayerData(){
//		PlayerData data;
//		if(p == g.getFirstPlayer())
//			data = p1Data;
//		else
//			data = p2Data;
		
		p1Data.updateData();
		p2Data.updateData();
	}
	
	public static void updateCurrData(){
		
		currCh.removeAll();
		currCh.add(new CurrChData(g.getCurrentChampion()));
		currCh.repaint();
		currCh.revalidate();
		
	}

	
	public static void updateChAbilities(){
		
		north.remove(abilitiesP);
		setAbilitiesP(new ChampAbilities(g.getCurrentChampion()));
		north.add(abilitiesP, BorderLayout.WEST);
		north.repaint();
		north.revalidate();
		
		
		//north.add(new ChampAbilities(g.getCurrentChampion()));
	}
	
	public static void updatePQ(){
		
		turns.removeAll();
		
		Object[] pq = g.getTurnOrder().getElements();
		
		
		for(int i = pq.length-1; i >= 0; i--){
			Champion c = (Champion) pq[i];
			
			
			JLabel champ = new JLabel();

			int x;
			int y;
			
			if(c == g.getCurrentChampion()){
				x = 150; y = 150;
				champ.setBackground(new Color(0x1abec1));
			}
			else{
				x = 100; y = 100;
				champ.setBackground(Color.GRAY);
			}
			champ.setOpaque(true);
			
			champ.setPreferredSize(new Dimension(x,y));
			ImageIcon ChImg = new ImageIcon(c.getIcon().getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
			champ.setIcon(ChImg);
			
			turns.add(champ);
		}
	}

	public static GameController getControl() {
		return control;
	}
	
//	public static void updateCurr(Champion c){
//		t;
//	}



	public static ChampAbilities getAbilitiesP() {
		return abilitiesP;
	}



	public static void setAbilitiesP(ChampAbilities abilitiesP) {
		GamePage.abilitiesP = abilitiesP;
	}



	public static CurrChData getCurrData() {
		return currData;
	}



	public static void setCurrData(CurrChData currData) {
		GamePage.currData = currData;
	}



	public void setNorth(JPanel north) {
		this.north = north;
	}



	public void setLeft(JPanel left) {
		this.left = left;
	}



	public void setRight(JPanel right) {
		this.right = right;
	}



	public void setSouth(JPanel south) {
		this.south = south;
	}



	public void setCenter(JPanel center) {
		this.center = center;
	}



	public void setAbilities(JPanel abilities) {
		this.abilities = abilities;
	}



	public void setNextCh(JPanel nextCh) {
		this.nextCh = nextCh;
	}



	public void setAttack(JPanel attack) {
		this.attack = attack;
	}



	public void setMove(JPanel move) {
		this.move = move;
	}



	public void setMoveP(MovePanel moveP) {
		this.moveP = moveP;
	}



	public void setEndTurn(JPanel endTurn) {
		this.endTurn = endTurn;
	}



	public void setCurrCh(JPanel currCh) {
		this.currCh = currCh;
	}



	public void setData(JPanel data) {
		this.data = data;
	}



	public void setTurnOrder(JPanel turnOrder) {
		this.turnOrder = turnOrder;
	}
	

	

	
}
