package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import Conexion.cls_conexion;

public class Estudiante {

	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    cls_conexion cn = new cls_conexion();
    
    private int ID_ESTUDIANTE;
    private int ID_CURSO;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String correo;
    private String curso;
    private String paralelo;
    
    public Estudiante() {
    	
    }

	public Estudiante(int iD_ESTUDIANTE, int iD_CURSO, String identificacion, String nombre, String apellido,
			String correo, String curso, String paralelo) {
		super();
		ID_ESTUDIANTE = iD_ESTUDIANTE;
		ID_CURSO = iD_CURSO;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.curso = curso;
		this.paralelo = paralelo;
	}

	public int getID_ESTUDIANTE() {
		return ID_ESTUDIANTE;
	}

	public void setID_ESTUDIANTE(int iD_ESTUDIANTE) {
		ID_ESTUDIANTE = iD_ESTUDIANTE;
	}

	public int getID_CURSO() {
		return ID_CURSO;
	}

	public void setID_CURSO(int iD_CURSO) {
		ID_CURSO = iD_CURSO;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getParalelo() {
		return paralelo;
	}

	public void setParalelo(String paralelo) {
		this.paralelo = paralelo;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	
	public boolean registrarEstudiante(Estudiante estudiante) {
	    String sql = "INSERT INTO ESTUDIANTE (ID_CURSO, identificacion, nombre, apellido, correo) VALUES (?, ?, ?, ?, ?)";

	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, this.getID_CURSO());
	        ps.setString(2, this.getIdentificacion());
	        ps.setString(3, this.getNombre());
	        ps.setString(4, this.getApellido());
	        ps.setString(5, this.getCorreo());

	        ps.executeUpdate();

	        return true; // Return true if at least one row was affected

	    } catch (SQLException e) {
	        e.printStackTrace(); // Log the exception or handle it appropriately
	        return false;
	    }
	}

	public List<Estudiante> listarEstudiantes() {
	    List<Estudiante> listaEstudiantes = new ArrayList<>();

	    String sql = "SELECT e.ID_ESTUDIANTE, e.IDENTIFICACION, e.NOMBRE, e.APELLIDO, e.CORREO, c.CURSO, c.PARALELO FROM estudiante e "
	            + "INNER JOIN curso c ON e.ID_CURSO = c.ID_CURSO";

	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Estudiante estudiante = new Estudiante();
	            estudiante.setID_ESTUDIANTE(rs.getInt("ID_ESTUDIANTE"));
	            estudiante.setIdentificacion(rs.getString("IDENTIFICACION"));
	            estudiante.setNombre(rs.getString("NOMBRE"));
	            estudiante.setApellido(rs.getString("APELLIDO"));
	            estudiante.setCorreo(rs.getString("CORREO"));
	            estudiante.setCurso(rs.getString("CURSO"));
	            estudiante.setParalelo(rs.getString("PARALELO"));

	            listaEstudiantes.add(estudiante);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Log the exception or handle it appropriately
	    } finally {
	        try {
	            // Close resources properly
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Log the exception or handle it appropriately
	        }
	    }

	    return listaEstudiantes;
	}
	
	
	public boolean ActualizarEstudiante(Estudiante estudiante) {
		
		String sql="UPDATE ESTUDIANTE SET ID_CURSO=?, IDENTIFICACION=?, NOMBRE=?, APELLIDO=?, CORREO=? WHERE ID_ESTUDIANTE=?";
		
		try {
			
			con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        
	        ps.setInt(1, this.getID_CURSO());
	        ps.setString(2, this.getIdentificacion());
	        ps.setString(3, this.getNombre());
	        ps.setString(4, this.getApellido());
	        ps.setString(5, this.getCorreo());
	        ps.setInt(6, this.getID_ESTUDIANTE());
	        
	        ps.executeUpdate();
	        
	        return true;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return false;
		
	}
	
	
	public boolean EliminarEstudiante(Estudiante estudiante){
		
		String sql="DELETE FROM ESTUDIANTE WHERE ID_ESTUDIANTE=?";
		
		try {
			con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        
	        ps.setInt(1, this.getID_ESTUDIANTE());
	        ps.execute();
	        
	        return true;
	        
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return false;
		
	}

	
	
}
