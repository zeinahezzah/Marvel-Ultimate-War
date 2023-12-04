package exceptions;

@SuppressWarnings("serial")
abstract public class GameActionException extends Exception {

	public GameActionException() {
		super();
	}

	public GameActionException(String s) {
		super(s);
	}

}
