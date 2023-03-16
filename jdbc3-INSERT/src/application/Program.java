package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null; 
		PreparedStatement st = null; //Classe responsável pela consulta para dar um Insert; 
		try {
			conn = DB.getConnection(); 
			
			st = conn.prepareStatement( //O preperStatement pede uma string de comando de inserção banco de dados
					"INSERT INTO seller " //Comando de inserção na tabela Seller
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId)" //O entre parênteses são os campos ou seja, as colunas (é Linguagem sql)
					+"VALUES " //As informações que serão inseridas
					+"(?, ?, ?, ?, ?)" //O interrogação é o placeholder, (é um lugar onde colocaremos DEPOIS as informações). Cada interrogação é para cada campo da tabela
					,Statement.RETURN_GENERATED_KEYS); //Aqui ele vai retornar o ID novo gerado da tabela Seller
			
			/*Insert para department
			 * 			st = conn.prepareStatement(
					"INSERT INTO department "
					+"(name) "
					+" VALUES "
					+" (?),(?)"
					);
			 * 
			 * */
			
			
			//Agora vou definir os comandos para inserir no lugar dos "?"
			
			st.setString(1, "Carl Purple"); //o 1 refere-se ao primeiro interrogação, e em seguida a informação que é o Carl Purple;
			st.setString(2, "carlpurple@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime())); //quando vamos instanciar uma DATA dentro do JDBC, precisa dar este comando
			st.setDouble(4, 5000.0); 
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate(); //Essa operação irá executar os updates da linha acima coloquei este rowsAffected para saber quantas linhas foram afetadas; 
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys(); //Ela retorna um ResultSet da chave gerada ou seja o ID novo da inserção
				while (rs.next()) {
					int id = rs.getInt(1); //O int do get int se refere a posição 1, que na tabel é o ID
					System.out.println("Done! ID = " + id);
				}
			
			}else {
				System.out.println("No rown affected!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
		e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}