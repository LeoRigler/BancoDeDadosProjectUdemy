package db;

public class DbException extends RuntimeException{

	private static final long serialVersionUID = 4680219855638974793L;
	public DbException(String msg) {
		super(msg);
	}

}
