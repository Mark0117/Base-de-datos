package modelo;
import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
	private Connection con;
    private final String ruta; 
    private final String usuario;
    private final String password;


    public Conexion(){
        ruta="jdbc:mysql://localhost:3306/marco";
        usuario="root";
        password="";

    }
    
   public Connection getConexion(){
	   try {
	       Class.forName("com.mysql.jdbc.Driver");
	       con=DriverManager.getConnection(ruta,usuario,password);
	       return  con;
	   } catch (Exception e) {
		   System.out.println(e.getMessage());
	   }
       return con;

   }
    
}
