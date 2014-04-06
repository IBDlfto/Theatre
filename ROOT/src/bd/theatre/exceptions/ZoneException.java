package bd.theatre.exceptions;

import java.sql.SQLException;

public class ZoneException extends SQLException {

	public ZoneException() {
		// TODO Auto-generated constructor stub
	}

	public ZoneException (String m) {
		super(m);
	}
}
