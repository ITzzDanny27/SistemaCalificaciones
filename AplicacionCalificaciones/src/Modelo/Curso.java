package Modelo;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Conexion.cls_conexion;

import java.util.ArrayList;

public class Curso {
	
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    cls_conexion cn = new cls_conexion();
	
    private int id;
	private String curso;
	private String paralelo;
	
	public Curso() {
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Curso(int id ,String curso, String paralelo) {
		this.id=id;
		this.curso=curso;
		this.paralelo=paralelo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getParalelo() {
		return paralelo;
	}
	

	public void setParalelo(String paralelo) {
		this.paralelo = paralelo;
	}

	public boolean registrarCurso(Curso curso) {
		
		String sql="INSERT INTO CURSO(CURSO, PARALELO) VALUES (?,?)";
		
		try {
			
			con=cn.getConnection();
			ps=con.prepareStatement(sql);
					
			ps.setString(1, this.getCurso());	
			ps.setString(2, this.getParalelo());
			ps.execute();
			return true;
							
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		} finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }	
			
	}
	
	
	public List<Curso> listarCursos() {
	    List<Curso> listaCursos = new ArrayList<>();

	    String sql = "SELECT * FROM CURSO";

	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            // Create a new Curso instance for each row
	            Curso curso = new Curso();
	            curso.setId(rs.getInt("ID_CURSO"));
	            curso.setCurso(rs.getString("CURSO"));
	            curso.setParalelo(rs.getString("PARALELO"));

	            listaCursos.add(curso);
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

	    return listaCursos;
	}
	
	
	public boolean ActualizarCurso(Curso curso){
		
		String sql="UPDATE CURSO SET CURSO=?, PARALELO=? WHERE ID_CURSO=?";
		
		try {
			
			con=cn.getConnection();
			ps=con.prepareStatement(sql);
			
			
			ps.setString(1, this.getCurso());
			ps.setString(2, this.getParalelo());
			ps.setInt(3, this.getId());
			
			ps.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
            return false;		
		}finally {
            // Cerrar recursos - Esto es importante para evitar fugas de memoria
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}
	
	
	public boolean EliminarCurso(Curso curso) {
		
		String sql="DELETE FROM CURSO WHERE ID_CURSO=?";
		
		try {
			con=cn.getConnection();
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, this.getId());
			ps.execute();
			
			return true;
			
			
		}catch(SQLException e) {
			
			System.out.println(e.toString());
			
            return false;
			
		}finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
				
	}
	
	public int ObtenerIDCurso(String curso, String paralelo) {
	    String sql = "SELECT ID_CURSO FROM CURSO WHERE CURSO=? AND PARALELO=?";
	    
	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setString(1, curso);
	        ps.setString(2, paralelo);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("ID_CURSO");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        try {
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
	            e.printStackTrace(); 
	        }
	    }

	    return -1; 
	}


}
