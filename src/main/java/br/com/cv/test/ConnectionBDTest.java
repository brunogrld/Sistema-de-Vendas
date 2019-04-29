package br.com.cv.test;
import java.sql.Connection;

import org.junit.Test;

import br.com.cv.util.ConnectionBD;

public class ConnectionBDTest {

	@Test
	public void testConnection() {
		Connection conn = ConnectionBD.getConnection();
		System.out.println(conn);
	}
}
