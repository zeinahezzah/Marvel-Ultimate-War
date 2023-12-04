package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.abilities.*;
import model.effects.*;
import model.world.*;
import exceptions.*;

public class Game  {
	private static ArrayList<Champion> availableChampions;
	private static ArrayList<Ability> availableAbilities;
	private static Player firstPlayer;
	private static Player secondPlayer;
	private Object[][] board;
	private PriorityQueue turnOrder;
	private boolean firstLeaderAbilityUsed;
	private boolean secondLeaderAbilityUsed;
	private final static int BOARDWIDTH = 5;
	private final static int BOARDHEIGHT = 5;
	//private Damageable attackTarget;


	public Game(Player first, Player second) {
		firstPlayer = first;

		secondPlayer = second;
		availableChampions = new ArrayList<Champion>();
		availableAbilities = new ArrayList<Ability>();
		board = new Object[BOARDWIDTH][BOARDHEIGHT];
		turnOrder = new PriorityQueue(6);
		
		//firstplayer turns
		for(int i = 0; i<firstPlayer.getTeam().size(); i++)
			turnOrder.insert(firstPlayer.getTeam().get(i));
		//secondplayer turns
		for(int i = 0; i<secondPlayer.getTeam().size(); i++)
			turnOrder.insert(secondPlayer.getTeam().get(i));
		
		//System.out.println(((Champion)turnOrder.peekMin()).getName());
		
		placeChampions();
		placeCovers();
	}

	public static void loadAbilities(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Ability a = null;
			AreaOfEffect ar = null;
			switch (content[5]) {
			case "SINGLETARGET":
				ar = AreaOfEffect.SINGLETARGET;
				break;
			case "TEAMTARGET":
				ar = AreaOfEffect.TEAMTARGET;
				break;
			case "SURROUND":
				ar = AreaOfEffect.SURROUND;
				break;
			case "DIRECTIONAL":
				ar = AreaOfEffect.DIRECTIONAL;
				break;
			case "SELFTARGET":
				ar = AreaOfEffect.SELFTARGET;
				break;

			}
			Effect e = null;
			if (content[0].equals("CC")) {
				switch (content[7]) {
				case "Disarm":
					e = new Disarm(Integer.parseInt(content[8]));
					break;
				case "Dodge":
					e = new Dodge(Integer.parseInt(content[8]));
					break;
				case "Embrace":
					e = new Embrace(Integer.parseInt(content[8]));
					break;
				case "PowerUp":
					e = new PowerUp(Integer.parseInt(content[8]));
					break;
				case "Root":
					e = new Root(Integer.parseInt(content[8]));
					break;
				case "Shield":
					e = new Shield(Integer.parseInt(content[8]));
					break;
				case "Shock":
					e = new Shock(Integer.parseInt(content[8]));
					break;
				case "Silence":
					e = new Silence(Integer.parseInt(content[8]));
					break;
				case "SpeedUp":
					e = new SpeedUp(Integer.parseInt(content[8]));
					break;
				case "Stun":
					e = new Stun(Integer.parseInt(content[8]));
					break;
				}
			}
			switch (content[0]) {
			case "CC":
				a = new CrowdControlAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), e);
				break;
			case "DMG":
				a = new DamagingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
				break;
			case "HEL":
				a = new HealingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
				break;
			}
			availableAbilities.add(a);
			line = br.readLine();
		}
		br.close();
	}

	public static void loadChampions(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Champion c = null;
			switch (content[0]) {
			case "A":
				c = new AntiHero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;

			case "H":
				c = new Hero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;
			case "V":
				c = new Villain(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;
			}

			c.getAbilities().add(findAbilityByName(content[8]));
			c.getAbilities().add(findAbilityByName(content[9]));
			c.getAbilities().add(findAbilityByName(content[10]));
			
			//System.out.println(c.getAbilities());
			
			availableChampions.add(c);
			line = br.readLine();
		}
		br.close();
	}

	private static Ability findAbilityByName(String name) {
		for (Ability a : availableAbilities) {
			if (a.getName().equals(name))
				return a;
		}
		return null;
	}

	public void placeCovers() {
		int i = 0;
		while (i < 5) {
			int x = ((int) (Math.random() * (BOARDWIDTH - 2))) + 1;
			int y = (int) (Math.random() * BOARDHEIGHT);

			if (board[x][y] == null) {
				board[x][y] = new Cover(x, y);
				i++;
			}
		}

	}

	public void placeChampions() {
		int i = 1;
		for (Champion c : firstPlayer.getTeam()) {
			board[0][i] = c;
			c.setLocation(new Point(0, i));
			i++;
		}
		i = 1;
		for (Champion c : secondPlayer.getTeam()) {
			board[BOARDHEIGHT - 1][i] = c;
			c.setLocation(new Point(BOARDHEIGHT - 1, i));
			i++;
		}
	
	}

	public static ArrayList<Champion> getAvailableChampions() {
		return availableChampions;
	}

	public static ArrayList<Ability> getAvailableAbilities() {
		return availableAbilities;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public Object[][] getBoard() {
		return board;
	}

	public PriorityQueue getTurnOrder() {
		return turnOrder;
	}

	public boolean isFirstLeaderAbilityUsed() {
		return firstLeaderAbilityUsed;
	}

	public boolean isSecondLeaderAbilityUsed() {
		return secondLeaderAbilityUsed;
	}

	public static int getBoardwidth() {
		return BOARDWIDTH;
	}

	public static int getBoardheight() {
		return BOARDHEIGHT;
	}
	
	
	
	
	//M2
	
	public Champion getCurrentChampion(){
		return(Champion) turnOrder.peekMin();
	}
	
	/*public Player checkGameOver(){
		if(turnOrder.size() == 1){
			Champion c = (Champion)turnOrder.peekMin();
			return getPlayer(c);
		}
		return null;
	}*/	
	
	public Player checkGameOver(){
		if(firstPlayer.getTeam().size() == 0)
			return secondPlayer;
		
		if(secondPlayer.getTeam().size() == 0)
			return firstPlayer;
		
		return null;
	}
	
	public void move(Direction d) throws NotEnoughResourcesException, UnallowedMovementException{
		Champion c = getCurrentChampion();	//champion who's playing now
		Point p = c.getLocation();
		
		if(c.getCondition().equals(Condition.ROOTED))	//rooted effect cant move
			throw new UnallowedMovementException("You can not move while being rooted");
		
		else if(c.getCurrentActionPoints() < 1)		//not enough action points to move (costs 1 point)
			throw new NotEnoughResourcesException("You need at least one action point to move");
		
		Point newPoint = null;	//stores the location of the cell he'll move to 
		int x = (int)p.getX();	
		int y = (int)p.getY();
		
		switch(d){	//determine newPoint's x and y values based on direction
			//NB: x -> vertical (up/down), y -> horizontal (right/left)!!
			case UP: newPoint = new Point(x-1, y); break;
			case DOWN: newPoint = new Point(x+1, y); break;
			case RIGHT: newPoint = new Point(x, y+1); break;
			case LEFT: newPoint = new Point(x, y-1); break;
		}
		
		
		int x2 = (int)newPoint.getX();
		int y2 = (int)newPoint.getY();
		
		//EXCEPTIONS
		if(x2 < 0 || x2 > 4 || y2 < 0 || y2 > 4)	//trying to move out of board
			throw new UnallowedMovementException("Can not move out of the board");
		
		if(board[x2][y2] != null)	//move to nonempty cell
			throw new UnallowedMovementException("Target cell is not empty");
		
		
		else{	//movement allowed
			board[x2][y2] = c;		//place champion on new point
			c.setLocation(newPoint);	//update champion's location attribute
			board[x][y] = null;		//remove champion from old point
			c.setCurrentActionPoints(c.getCurrentActionPoints() - 1);	//deduct action point
			//listener.BoardUpdated(x,y);
		}
	}
		
	
	
	public void attack(Direction d) throws  NotEnoughResourcesException, ChampionDisarmedException{
		Champion c = getCurrentChampion();
		Damageable x = getFirstCell(d, c);	//get target that will be attacked
		
		//System.out.println(x);
		
		if(checkEffect(c,"Disarm"))		//Disarmed can't attack
			throw new ChampionDisarmedException("You can't attack while disarmed");
	
		else if(c.getCurrentActionPoints() < 2)		//normal attacks cost 2 action points
			throw new NotEnoughResourcesException("You need at least 2 action points to attack");

	
		//x!= null -> check that there is a possible target in that direction
		//distance <=c.getAttackRange() -> make sure that the found target is in range
		if(x != null && distance(c.getLocation(), x.getLocation()) <= c.getAttackRange()){
			
			//attackTarget = x;
		
			if(x instanceof Cover)	//cover HP deducted normally
				x.setCurrentHP(x.getCurrentHP() - c.getAttackDamage());

			else	//Hero/Villain/Antihero cases 
				x.setCurrentHP(newHP(c,(Champion)x));
			
			if(x.getCurrentHP() == 0){	//if target dies from attack
				removeDamageable(x);		//remove from game
			}
		}
		
		c.setCurrentActionPoints(c.getCurrentActionPoints() - 2);	//deduct action points regardless
	}
	
	
	public void removeDamageable(Damageable d){
		if(d instanceof Champion){		//remove champion from team
			Champion c = (Champion)d;	
			c.setCondition(Condition.KNOCKEDOUT);	//KNOCKEDOUT = Dead
			getPlayer(c).getTeam().remove(c);
		}
		
		PriorityQueue temp = new PriorityQueue(6);		//remove champion from turn order
		while(!turnOrder.isEmpty()){	//remove all champions from turnOrder
			if(!((Damageable)turnOrder.peekMin()).equals(d))	//if any other champion -> out in temp queue
				temp.insert(turnOrder.remove());
			else		//if dead champion -> remove and don't put in temp -> deleted
				turnOrder.remove();
		}
		
		while(!temp.isEmpty()){	//place champions stored in temp back in turnOrder
			turnOrder.insert(temp.remove());
		}
		
		int x =(int) d.getLocation().getX();	
		int y = (int) d.getLocation().getY();
		board[x][y] = null;	//remove damageable from board
	}

	public Damageable getFirstCell(Direction d, Champion c){	//finds first target in the direction d
		Point p = c.getLocation();	//starting point where champion is standing
		int x = (int)p.getX();
		int y = (int)p.getY();
		switch(d){		//up,down -> x, right,left -> y
			case UP: 
				for(int i = x-1; i >= 0  ; i--){ 	//start at first cell above champion till upper edge of board
					if (board[i][y] != null)	//if non-empty cell -> target 
						return (Damageable) board[i][y];	//return the target on this cell
				}
			case DOWN:
				for(int i = x+1; i <= 4; i++){		//start at first cell below champion till lower edge of board
					if (board[i][y] != null)
						return (Damageable) board[i][y];
				}
				
			case RIGHT:
				for(int i = y+1; i <= 4; i++){	//start at first cell to the right of champion till rightmost edge of board
					if (board[x][i] != null)
						return (Damageable) board[x][i];
				}
			
			case LEFT:
				for(int i = y-1; i >=0 ; i--){	//start at first cell to the left of champion till leftmost edge of board
					if (board[x][i] != null)
						return (Damageable) board[x][i];
				}
			
			default: return null;	//no target found in this direction
		}
	}
	
	public int distance(Point p1, Point p2){	//calculates manhattan distance between 2 points
		return (int)(Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
	}
	
	
	public int newHP(Champion c, Champion x){	//cases for champion attacks
		int newHP = 0;	//initialize variable
		if((c instanceof Hero && !(x instanceof Hero)) || (c instanceof Villain && !(x instanceof Villain)) || (c instanceof AntiHero && !(x instanceof AntiHero)))
			//hero vs. villain OR villain vs. hero OR (antiheroes act like hero/villain depending on who they're attacking)
			//so if any type is attacking another champion who is NOT the same type -> 50% extra damage (*1.5)
			newHP =  x.getCurrentHP() - ((int)(c.getAttackDamage()*1.5));
		
		else //hero vs hero OR villain vs villain OR antihero vs antihero (same type)
			newHP = x.getCurrentHP() - c.getAttackDamage();
		
		//newHP is the optimal value of the updated HP IF the target has no effect that blocks attack
		//before returning newHP check for any effects that might return a different value than expected
		
		if(checkEffect(x,"Shield")){	//shield blocks attack and HP is unaffected (ONLY USED ONCE)
			for(int i = 0; i < x.getAppliedEffects().size(); i++){		//remove shield effect after using
				if(x.getAppliedEffects().get(i) instanceof Shield){	//find shield effect index
					x.getAppliedEffects().get(i).remove(x);		//remove from list
					x.getAppliedEffects().remove(i);		//remove effect
					break;	//in case of multiple shield effects Only remove 1
				}
			}
			return x.getCurrentHP();	//return old unchanged HP since no damage is done 
		}
		
		else if(checkEffect(x,"Dodge")){	//Dodge has a 50% chance of blocking attack completely or not blocking at all (random probability)
			int n = (int)(Math.random()*2);	//generates 0 or 1 (0 -> doesn't block, 1 -> blocks)
			if(n == 0)	//won't dodge	
				return newHP;	//HP affected by attack 
			else
				return x.getCurrentHP();	//dodges so HP is unaffected
		}
		
		else return newHP;	//if no applied effect return the calculated HP based on the cases above
	}
	
	
	public void castAbility(Ability a) throws AbilityUseException, NotEnoughResourcesException, InvalidTargetException, Exception {
		Champion c = getCurrentChampion(); //champion playing
		
		//EXCEPTIONS
		
		if(a.getCurrentCooldown() != 0)		//still has to wait turns before using ability
			throw new AbilityUseException("Cooldown time is not over yet");
		
		else if(checkEffect(c,"Silence"))		//silence effect -> can't cast ability
			throw new AbilityUseException("You are Silenced. You can't cast abilities");
		
		if(c.getCurrentActionPoints() < a.getRequiredActionPoints() || c.getMana() < a.getManaCost())	//ability costs more than he has
			throw new NotEnoughResourcesException("You don't have enough resources for this ability");
	
		
		else{
		ArrayList<Damageable> targets = getTargets(c,a);	//get valid targets based on type of Ability & area of effect
		
		a.execute(targets);		//execute ability on targets (defined in ability subclasses)
		
		for(int j = 0; j<targets.size(); j++){		//check if any target died due to the attack
			Damageable m = targets.get(j);
			if(m.getCurrentHP() == 0){		//target died
				removeDamageable(m);	//previously defined above (removes from board, team, turnOrder)
			}
		}
		
		a.setCurrentCooldown(a.getBaseCooldown());	//reset cooldown (no. of turns they have to wait to use the ability again)
		
		c.setCurrentActionPoints(c.getCurrentActionPoints() - a.getRequiredActionPoints());	//deduct action points
		c.setMana(c.getMana() - a.getManaCost());	//deduct mana
		}
	}
		
	
	public static boolean checkEffect(Champion c, String e){	//checks if c has the effect with the name e
		ArrayList<Effect> a = c.getAppliedEffects();	//list of applied effects
		for(int i = 0; i<a.size(); i++){
			if(a.get(i).getName().equals(e))	//if effect has same name as e
				return true;	//effect found
		}
		return false;	//effect not found
	}
	
	
	public ArrayList<Damageable> getTargets(Champion c, Ability a) throws InvalidTargetException{		//gets valid targets for first cast ability method
		ArrayList<Damageable> targets = new ArrayList<Damageable>();	//create list that will hold targets
		Point p = c.getLocation();	//champion's location
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		switch(a.getCastArea()){	//targets depend on area of effect
			
		case SURROUND: 	
			for(int i = x+1; i >= x-1; i--){		//above till below champion
				for(int j = y-1; j <= y+1; j++){		//left till right of champion
					if(i >-1 && i<5 && j >-1 && j<5){	//in case champion is on edge of board
					if(board[i][j] != null && !(i==x && j==y)){		//non-empty cell that is not the champion's current cell
						Damageable target = (Damageable) board[i][j];	
						/*
						if(a instanceof DamagingAbility){
							if(getEnemyPlayer(c).getTeam().contains(target) || target instanceof Cover){
								targets.add(target);
							}
						}
						
						else if(a instanceof HealingAbility){
							if(getPlayer(c).getTeam().contains(target))
								targets.add(target);
						}
						
						else if(a instanceof CrowdControlAbility){
							if (((CrowdControlAbility)a).getEffect().getType().equals(EffectType.BUFF)){
								if(getPlayer(c).getTeam().contains(target))
									targets.add(target);
							}
							else
								if(getEnemyPlayer(c).getTeam().contains(target)){
									targets.add(target);
								}	
						}
						*/
						addTargets(c,target,a,targets);	//add target based on ability type
					}
				}
				}
			}
			break;
			
		case SELFTARGET:
			targets.add(c);	//the champion himself is the only target
			break;
			
		case TEAMTARGET:	//targets are either the champion's team or the enemy team (depending on ability)
			ArrayList<Champion> targetTeam;	//arraylist that will point to the desired team
			if(a instanceof DamagingAbility)	//damaging -> target = enemy team
				targetTeam = getEnemyPlayer(c).getTeam();
			
			else if(a instanceof HealingAbility){	//healing  -> target =  own team
				targetTeam = getPlayer(c).getTeam();
			}
			
			else{	//crowd control ability
				if (((CrowdControlAbility)a).getEffect().getType().equals(EffectType.BUFF)){	//BUFF -> target = own team
					targetTeam = getPlayer(c).getTeam();
				}
				else	//DEBUFF -> target = enemy team
					targetTeam = getEnemyPlayer(c).getTeam();	
			}
			
			for(int i = 0 ; i < targetTeam.size(); i++){	//for each target make sure they're within range
				Champion e = targetTeam.get(i);
				if(distance(c.getLocation(), e.getLocation()) <= a.getCastRange())	
					targets.add(targetTeam.get(i));	//only add team member to targets list IF within range
			}
			
		default:
			break;	//do nothing if DIRECTIONAL or SINGLETARGET
		}
		return targets;		//returns valid targets
	}

	
	public void castAbility(Ability a, Direction d) throws Exception, NotEnoughResourcesException, InvalidTargetException, AbilityUseException{
		Champion c = getCurrentChampion();
		Point p = c.getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		//EXCEPTIONS	-> same as above
		if(a.getCurrentCooldown() != 0)		
			throw new AbilityUseException("Cooldown time is not over yet");
		
		else if(checkEffect(c,"Silence"))	
			throw new AbilityUseException("You are Silenced. You can't cast abilities");
		
		if(c.getCurrentActionPoints() < a.getRequiredActionPoints() || c.getMana() < a.getManaCost())	
			throw new NotEnoughResourcesException("You don't have enough resources for this ability");
	
		else{
		
		ArrayList<Damageable> targets = new ArrayList<Damageable>();	//list to hold vaild targets
		Damageable target = null;	//initialize target variable
		
		switch(d){	//depending on direction
		case UP: 	//find targets above champion
			for(int i = x-1; i > 0; i--){	//start at first cell above champion till upper edge of board
				if (board[i][y] != null){	//if non-empty cell
					target = (Damageable) board[i][y];	//possible target
					if(distance(target.getLocation(), c.getLocation()) <= a.getCastRange())	//check that target is within range
						addTargets(c,target,a,targets);	//add target based on type of ability (defined below)
				}		
			}
			break;
			
		case DOWN:
			for(int i = x+1; i < 5 ; i++){	//start at first cell below champion till lower edge of board
				//same as above
				if (board[i][y] != null){
					target = (Damageable) board[i][y];
					if(distance(target.getLocation(), c.getLocation()) <= a.getCastRange())
						addTargets(c,target,a,targets);
				}
			}
			break;
			
		case RIGHT:
			for(int i = y+1; i < 5; i++){	//start at first cell to the right of champion till rightmost edge of board
				//same as above
				if (board[x][i] != null){
					target = (Damageable) board[x][i];
					if(distance(target.getLocation(), c.getLocation()) <= a.getCastRange())
						addTargets(c,target,a,targets);
				}
			}
			break;
		
		case LEFT:
			for(int i = y-1; i >=0 ; i--){	//start at first cell to the left of champion till leftmost edge of board
				//same as above
				if (board[x][i] != null){
					target = (Damageable) board[x][i];
					if(distance(target.getLocation(), c.getLocation()) <= a.getCastRange())
						addTargets(c,target,a,targets);
				}
			}
		}
		
		a.execute(targets);	//cast ability on list of valid targets
		
		for(int j = 0; j<targets.size(); j++){		//check if any target died due to the attack
			Damageable m = targets.get(j);
			if(m.getCurrentHP() == 0){		//target died
				removeDamageable(m);	//previously defined above (removes from board, team, turnOrder)
			}
		}
		
		c.setCurrentActionPoints(c.getCurrentActionPoints() - a.getRequiredActionPoints());	//deduct action points
		c.setMana(c.getMana() - a.getManaCost());	//deduct mana
		a.setCurrentCooldown(a.getBaseCooldown());	//reset cooldown counter
		}
	}
	
	
	public void castAbility(Ability a, int x, int y) throws Exception, NotEnoughResourcesException, InvalidTargetException, AbilityUseException{
		Champion c = getCurrentChampion();
		Point p = new Point(x,y);	//store given x, y in point 
		Damageable target = (Damageable) board[x][y];	//find target at given point on board
		
		
		//EXCEPTIONS
		
		if(a.getCurrentCooldown() != 0)		//still has to wait turns before using ability
			throw new AbilityUseException("Cooldown time is not over yet");
				
		else if(checkEffect(c,"Silence"))		//silence effect -> can't cast ability
			throw new AbilityUseException("You are Silenced. You can't cast abilities");
				
		if(c.getCurrentActionPoints() < a.getRequiredActionPoints() || c.getMana() < a.getManaCost())	//ability costs more than he has
			throw new NotEnoughResourcesException("You don't have enough resources for this ability");
		
		if(target == null)	//there is no damageable at given point
			throw new InvalidTargetException("The target cell is empty");
		
		if(distance(c.getLocation(), p) > a.getCastRange())		//given point is out of range
			throw new AbilityUseException("Your target is too far. Try a close target");
		
		if(p.equals(c.getLocation()))		//if target is himself -> only allowed if the ability is positive (Healing Or CC Buff)
			if(a instanceof DamagingAbility || (a instanceof CrowdControlAbility && !(((CrowdControlAbility)a).getEffect().getType().equals(EffectType.BUFF))) )
				throw new InvalidTargetException("You can't harm yourself");	//if trying to cast negative ability on himself	
		
		
		if(!(a instanceof DamagingAbility) && target instanceof Cover)	//Only damaging ability can be cast on a cover
			throw new InvalidTargetException("Only Damaging Abilities can be used on Covers");
	
		if(a instanceof DamagingAbility && getPlayer(c).getTeam().contains(target))
			throw new InvalidTargetException("You can't use a damaging ability on a target in your team");
		
		ArrayList<Damageable> targets = new ArrayList<Damageable>();	//list to hold valid targets
		//even though there is only a single target -> must be placed in a list since execute() takes a list as a parameter
		
		addTargets(c,target,a, targets);	//add given target (at x,y) to list based on type of ability
		
		a.execute(targets);	//cast ability on target
		
		for(int j = 0; j<targets.size(); j++){		//check if any target died due to the attack
			Damageable m = targets.get(j);
			if(m.getCurrentHP() == 0){		//target died
				removeDamageable(m);	//previously defined above (removes from board, team, turnOrder)
			}
		}
		
		a.setCurrentCooldown(a.getBaseCooldown());	//reset cooldown
		
		c.setCurrentActionPoints(c.getCurrentActionPoints() - a.getRequiredActionPoints());	//deduct action points
		c.setMana(c.getMana() - a.getManaCost());	//deduct mana
	}
	
	
	public static Player getPlayer(Champion c){	//returns which player the given champion belongs to
		/*for(int i = 0; i < firstPlayer.getTeam().size(); i++){	//traverse firstplayer's team
			if(c.equals(firstPlayer.getTeam().get(i))){		//if champion is found
				return firstPlayer;
			}
		}*/
		if(firstPlayer.getTeam().contains(c))	//check if c is on first player's team
			return firstPlayer;
		else		//c is in second player's team
			return secondPlayer;
	}
	
	public Player getEnemyPlayer(Champion c){	//returns the other player who is not playing with the given champion
		if(getPlayer(c).equals(firstPlayer))	//if c belongs to first player
			return secondPlayer;	//enemy is second player
		else		//vice versa
			return firstPlayer;
	}
	
	
	//adds potential target x to targets ArrayList based on type of ability
	public void addTargets(Champion c, Damageable x, Ability a, ArrayList<Damageable> targets) throws InvalidTargetException{
		if(a instanceof DamagingAbility && !(getPlayer(c).getTeam().contains(x)))	//damaging ability -> only if target is NOT on same team (enemy/cover)
			targets.add(x);
		
		else if(a instanceof HealingAbility && getPlayer(c).getTeam().contains(x))	//healing ability -> only if on same team 
			targets.add(x);
		
		else if(a instanceof CrowdControlAbility){	//CC ability
			CrowdControlAbility ability = (CrowdControlAbility)a;	//type cast ability to CC 
			
			if(ability.getEffect().getType().equals(EffectType.BUFF) && getPlayer(c).getTeam().contains(x))	//BUFF -> only if on same team
				targets.add(x);
			
			else if(ability.getEffect().getType().equals(EffectType.DEBUFF) && getEnemyPlayer(c).getTeam().contains(x)) //DEBUFF -> only if on enemy team
				targets.add(x);
			
			else if(a.getCastArea().equals(AreaOfEffect.SINGLETARGET))	//if CC on single target and the above conditions don't apply -> throw Exception!
				throw new InvalidTargetException("This ability can't be used on the selected target");
		}
	}
	
	
	 public void useLeaderAbility() throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException{	 //validates list of targets for user ability based on champion type
		 Champion c = getCurrentChampion();
		 
		 //EXCEPTIONS
		 if(!(getPlayer(c).getLeader().equals(c)))	//make sure that the champion playing now is the leader
			 throw new LeaderNotCurrentException("It is not the leader's turn yet.");
		 
		 
		//each leader can only use their ability ONCE!
		if((getPlayer(c).equals(firstPlayer) && firstLeaderAbilityUsed) || (getPlayer(c).equals(secondPlayer) && secondLeaderAbilityUsed) )
			throw new LeaderAbilityAlreadyUsedException("You've already used your Leader ability.");
		 
		else{
			 ArrayList<Champion> targets = new ArrayList<Champion>();	//list to hold valid targets
			 
			 if(c instanceof Hero) 	//hero -> target = own team
				 targets = getPlayer(c).getTeam();
			 
			 else if (c instanceof Villain){	//villain -> target = enemy team members whose HP < 30%
				 ArrayList<Champion> t = getEnemyPlayer(c).getTeam();	//store enemy team in t (so it's easier to call)
				 for(int i = 0; i<t.size(); i++){	//traverse enemy team
					 if(t.get(i).getCurrentHP() < 0.3*t.get(i).getMaxHP())	//if currentHP < 30% of maxHP
						 targets.add(t.get(i));	//add to valid targets
				 }
			 }
			 
			 else{	//antihero -> target = all players from both teams EXCEPT leaders
				 for(int i = 0; i < firstPlayer.getTeam().size(); i++){		//first player team
					 if(!(firstPlayer.getTeam().get(i).equals(firstPlayer.getLeader())))	//make sure it's not the leader
							 targets.add(firstPlayer.getTeam().get(i));
				 }
				 
				 for(int i = 0; i < secondPlayer.getTeam().size(); i++){	//second player team
					 if(!(secondPlayer.getTeam().get(i).equals(secondPlayer.getLeader())))	//make sure it's not the leader
							 targets.add(secondPlayer.getTeam().get(i));
				 }
			 }
			 
			 
			 c.useLeaderAbility(targets);	//call useLeaderAbility() method defined in champions class on the valid targets
			 
			 //the leader who just played can't use leader ability again 
			 if(getPlayer(c).equals(firstPlayer))	//if first player leader
				 this.firstLeaderAbilityUsed = true;
			 if(getPlayer(c).equals(secondPlayer))	//if second player leader
				 this.secondLeaderAbilityUsed = true;
			 
			 //setting the boolean to true will cause the LeaderAbilityAlreadyUsedException to be thrown if they try to use it again


			for(int j = 0; j<targets.size(); j++){		//check if any target died due to the attack
				Damageable m = targets.get(j);
				if(m.getCurrentHP() == 0){		//target died
					removeDamageable(m);	//previously defined above (removes from board, team, turnOrder)
				}
			}
		}
	 }
	 
	 
//	 public void endTurn(){		//ends champion's turn and moves onto next champion
//		 
//		 turnOrder.remove();	//remove champion whose turn just ended
//		 
//		 if(turnOrder.isEmpty()){	//round is over
//			 if(checkGameOver() == null)	 //no winner yet
//				 prepareChampionTurns();	//prepare next round's turns
//			 
//			 else{							//one of the players won -> game over
//				// System.out.println("GAME OVER! " + checkGameOver() + " Wins!");	//checkGameOver() -> returns name of winning player
//				 return;	//exit method no need to continue
//			 }
//		 }
//		 
//		 //game is still going and new turnOrder prepared
//		 
//		 while(((Champion)turnOrder.peekMin()).getCondition().equals(Condition.INACTIVE) && !turnOrder.isEmpty()){
//			 turnOrder.remove();	//skip INACTIVE champion's turns until active champion found
//		 }
//		 
//		 for(int i = 0; i<firstPlayer.getTeam().size(); i++){	//update first player's champions timers
//			 Champion c = firstPlayer.getTeam().get(i);
//			 updateAbilities(c);	//updates abilities cooldowns
//			 updateEffects(c);		//updates effects durations
//			 c.setCurrentActionPoints(c.getMaxActionPointsPerTurn());	//resets action point per turn to initial max
//		 }
//		 
//		 for(int i = 0; i<secondPlayer.getTeam().size(); i++){	//update second player's champions timers
//			 Champion c = secondPlayer.getTeam().get(i);
//			 updateAbilities(c);	//updates abilities cooldowns
//			 updateEffects(c);		//updates effects durations
//			 c.setCurrentActionPoints(c.getMaxActionPointsPerTurn());	//resets action point per turn to initial max
//		 }
//		 
//	 }
	 
	 
	public void endTurn() {
			turnOrder.remove();
			if (turnOrder.isEmpty())
				prepareChampionTurns();
			
			while (!turnOrder.isEmpty() && checkEffect((Champion) turnOrder.peekMin(), "Stun")) {
				Champion current = (Champion) turnOrder.peekMin();
				updateAbilities(current);
				updateEffects(current);
				turnOrder.remove();
				
				if (turnOrder.isEmpty())
					prepareChampionTurns();
				
			}
			
			
			
			Champion current = (Champion) turnOrder.peekMin();
			updateAbilities(current);
			updateEffects(current);
			current.setCurrentActionPoints(current.getMaxActionPointsPerTurn());
	}
	 
	 
	 public void updateAbilities(Champion c){	//updates abilities cooldowns
		 for(int i = 0; i < c.getAbilities().size(); i++){	//traverse all of champion's abilities
			 Ability a = c.getAbilities().get(i);
			 a.setCurrentCooldown(a.getCurrentCooldown()-1);	//one less turn to wait before using ability
		 }
	 }
	 
	 public void updateEffects(Champion c){		//updates effects durations
		 for(int i = 0; i < c.getAppliedEffects().size(); i++){		//traverse all of champion's applied effects
			 Effect e = c.getAppliedEffects().get(i);
			 e.setDuration(e.getDuration() - 1);	//duration decreases since 1 turn passed
			 
			 if(e.getDuration() == 0){	//if duration decreased to 0, remove effect 
				 e.remove(c);	//remove from champion
				 removeEffect(c,e.getName());	//remove from appliedEffects list (defined below)
			 }
		 }
	 }
	 
	 
	 public void removeEffect(Champion c, String s){	//removes effect of name s from appliedEffects list
		 for(int i = 0; i < c.getAppliedEffects().size(); i++){		//traverse appliedEffects list
				if(c.getAppliedEffects().get(i).getName().equals(s)){	//if effect of the given name is found
					c.getAppliedEffects().remove(i);	//remove from list
					break;	//IMPORTANT in case of several effects with the same name ONLY remove one
				}
			}
	 }
	 
	 public void prepareChampionTurns(){	//prepares turnOrder of next round
		// System.out.println(firstPlayer.getTeam().size());
		 for(int i = 0; i<firstPlayer.getTeam().size(); i++){	//first player team
			
			 Champion c = firstPlayer.getTeam().get(i);
			 
			 if(!(c.getCondition().equals(Condition.KNOCKEDOUT)))	//if champion is still not dead -> add to turnOrder
					 turnOrder.insert(c);
		 }
		 
		 
		 //System.out.println(secondPlayer.getTeam().size());
		 for(int i = 0; i<secondPlayer.getTeam().size(); i++){	//second player team
			 
			 Champion c = secondPlayer.getTeam().get(i);
			 
			 if(!(c.getCondition().equals(Condition.KNOCKEDOUT)))	//if champion is still not dead -> add to turnOrder
					 turnOrder.insert(c); 
		 }
	 }
	 
	 

//	public Damageable getAttackTarget() {
//		return attackTarget;
//	}
//
//	@Override
//	public void moved() {
//		//listener.BoardUpdated();
//		
//	}
	
	
}
