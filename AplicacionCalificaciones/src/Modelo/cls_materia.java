package Modelo;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;


public class cls_materia {
	String Id_materia;
	String Materia;
	String sentencia;
	
	Conexion.cls_conexion conex = new Conexion.cls_conexion();
	
		public cls_materia() {
			this.Id_materia="";
			this.Materia="";
			this.sentencia="";
			
		}

		public String getId_materia() {
			return Id_materia;
		}
		
		public void setId_materia(String id_materia) {
			Id_materia = id_materia;
		}
		
		public String getMateria() {
			return Materia;
		}

		public void setMateria(String materia) {
			Materia = materia;
		}
		

		public String getSentencia() {
			return sentencia;
		}

		public void setSentencia(String sentencia) {
			this.sentencia = sentencia;
		}

		public boolean insertar() {
		    int respuesta = 0;
		    try {
		    	String sentencia = "INSERT INTO materia(MATERIA) VALUES (?)";
		    	PreparedStatement st = conex.getConnection().prepareStatement(sentencia);
		    	st.setString(1, this.getMateria());
		        respuesta = st.executeUpdate();
		    } catch(Exception e){
		        System.out.println(e);
		        return false;
		    }
		    return respuesta > 0;
		}
	
	
		public boolean actualizar(cls_materia materia) {
		    int respuesta = 0;
		    try {
		        String sentencia = "UPDATE materia SET MATERIA = ? WHERE ID_MATERIA = ?";
		        try (PreparedStatement st = conex.getConnection().prepareStatement(sentencia)) {
		            st.setString(1, materia.getMateria());
		            st.setString(2, materia.getId_materia());
		            respuesta = st.executeUpdate();
		        }
		        System.out.println("Registros actualizados: " + respuesta);
		    } catch (SQLException e) {
		        System.out.println(e.toString());
		        return false;
		    } catch (Exception e) {
		        System.out.println("Error al actualizar la materia: " + e.getMessage());
		        e.printStackTrace();
		        return false;
		    }
		    return respuesta > 0;
		}


		
		public int eliminar() {
			int respuesta = 0;
			try {
				this.sentencia = "DELETE FROM materia WHERE ID_MATERIA=?";
				PreparedStatement st = conex.getConnection().prepareStatement(sentencia);
				st.setString(1, this.getId_materia());
				respuesta = st.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
			return respuesta;
		}
		public String [] constultarDato(String id){
			String fila[]= new String[6];
			try {
			this.sentencia="Select * from materia where ID_MATERIA= '"+id+"'";
				java.sql.Statement st=(conex.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY));
				ResultSet datos = ((java.sql.Statement) st).executeQuery(this.sentencia);
				datos.beforeFirst();		
				while (datos.next()) {
					fila[0]=datos.getObject(1).toString();
					fila[1]=datos.getObject(2).toString();

				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return fila;
		}
		public Object[][] consultar(String mater, String id){
			Object obj[][]=null;
			try {
				 if (id.matches("\\d+")) {
	                    this.sentencia = "SELECT * FROM materia WHERE ID_MATERIA = " + id;
	                } else {
	                    this.sentencia = "SELECT * FROM materia WHERE MATERIA LIKE '%" + id+ "%'";
	                }
			 
				java.sql.Statement st=(conex.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY));
				ResultSet datos = ((java.sql.Statement) st).executeQuery(this.sentencia);
				datos.last();
				int nf=datos.getRow();
				obj= new Object[nf][6];
						datos.beforeFirst();
				int f=0;
				while (datos.next()) {
					obj[f][0]=datos.getObject(1);
					obj[f][1]=datos.getObject(2);

					f++;	
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return obj;
		}
}
