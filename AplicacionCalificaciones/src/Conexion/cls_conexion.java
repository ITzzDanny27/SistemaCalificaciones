package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cls_conexion {
    String db = "colegio";
    String usu = "root";
    String cla = "";
    String url = "jdbc:mysql://localhost/" + db;
    static Connection conexion = null;

    public cls_conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usu, cla);
            if (conexion != null) {
                System.out.println("Conexión correcta");
            } else {
                System.out.println("Error de Conexión");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Connection getConnection() throws SQLException{
        return (Connection) DriverManager.getConnection(url, usu, cla);
    }


    public boolean validarCredenciales(String usuario, String clave) {
        String consulta = "SELECT * FROM profesor WHERE USUARIO = ? AND CLAVE = ?";
        try {
            PreparedStatement stat = conexion.prepareStatement(consulta);
            stat.setString(1, usuario);
            stat.setString(2, clave);
            ResultSet resultSet = stat.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

/*public class cls_conexion {
	String db="colegio";
	String usu="root";
	String cla="";
	String url="jdbc:mysql://localhost/"+db;
	static Connection conexion = null;
		public cls_conexion(){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection(url,usu,cla);
				if(conexion!=null) {
					System.out.println("Conexión correcta");	
				} else {
					System.out.println("Eror de Conexión");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		public Connection conectar() {
			return conexion;
		}
		
		
		public boolean validarCredenciales(String usuario, String clave) {
			
			String consulta = "Select * from profesor where USUARIO = ? and CLAVE = ?";
			try {
				PreparedStatement stat = conexion.prepareStatement(consulta);
				stat.setString(1, usuario);
				stat.setString(2, clave);
				ResultSet resultSet = stat.executeQuery();
				if(resultSet.next()) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
}*/
