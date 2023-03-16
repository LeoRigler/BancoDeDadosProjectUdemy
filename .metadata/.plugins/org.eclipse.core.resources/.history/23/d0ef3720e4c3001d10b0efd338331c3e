package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		Connection conn = null; 
		PreparedStatement st = null; 
		
		try {
			conn = DB.getConnection(); 
			st = conn.prepareStatement( //O prepareStatement é utilizado também pra fazer UPDATE
					"UPDATE seller "
					+"SET BaseSalary = BaseSalary + ?" //Aqui estou incrementando o salario do vendedor;
					+"WHERE "
					+"(DepartmentID = ?)");
			
			st.setDouble(1, 200.0);
			st.setInt(2, 2);
			
			int rowsAffected = st.executeUpdate(); //O comando executeUpdate irá executar, e o rows affected vai dizer quantas linhas foram afetadas;
			
			System.out.println("Done! Rows affected " + rowsAffected);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	
	}
}