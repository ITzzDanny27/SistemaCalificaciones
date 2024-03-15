package Modelo;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;


public class cls_profesores {
	int Id_profesores;
	String Identificacion;
	String Nombre;
	String Apellido;
	String Correo;
	String Telefono;
	String usuario;
	String clave;
	String sentencia;
	
	Conexion.cls_conexion conex = new Conexion.cls_conexion();
	
		public cls_profesores() {
			this.Id_profesores=0;
			this.Identificacion="";
			this.Nombre="";
			this.Apellido="";
			this.Correo="";
			this.Telefono="";
			this.usuario="";
			this.clave="";
			this.sentencia="";
		}

		public int getId_profesores() {
			return Id_profesores;
		}

		public void setId_profesores(int id_profesores) {
			Id_profesores = id_profesores;
		}

		public String getIdentificacion() {
			return Identificacion;
		}

		public void setIdentificacion(String identificacion) {
			Identificacion = identificacion;
		}

		public String getNombre() {
			return Nombre;
		}

		public void setNombre(String nombre) {
			Nombre = nombre;
		}

		public String getApellido() {
			return Apellido;
		}

		public void setApellido(String apellido) {
			Apellido = apellido;
		}

		public String getCorreo() {
			return Correo;
		}

		public void setCorreo(String correo) {
			Correo = correo;
		}

		public String getTelefono() {
			return Telefono;
		}

		public void setTelefono(String telefono) {
			Telefono = telefono;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getClave() {
			return clave;
		}

		public void setClave(String clave) {
			this.clave = clave;
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
		    	String sentencia = "INSERT INTO profesor(IDENTIFICACION,NOMBRE,APELLIDO,TELEFONO, CORREO, USUARIO, CLAVE) VALUES (?,?,?,?,?,?,?)";
		    	PreparedStatement st = conex.getConnection().prepareStatement(sentencia);
		    	st.setString(1, this.getIdentificacion());
		    	st.setString(2, this.getNombre());
		    	st.setString(3, this.getApellido());
		    	st.setString(4, this.getTelefono());
		    	st.setString(5, this.getCorreo());
		    	st.setString(6, this.getUsuario());
		    	st.setString(7, this.getClave());
		    	
		        respuesta = st.executeUpdate();
		    } catch(Exception e){
		        System.out.println(e);
		        return false;
		    }
		    return respuesta > 0;
		}
	
	
		public boolean actualizar(cls_profesores profesor) {
		    int respuesta = 0;
		    try {
		        String sentencia = "UPDATE profesor SET IDENTIFICACION=?, NOMBRE=?, APELLIDO=?, TELEFONO=?, CORREO=?, USUARIO=?, CLAVE=? WHERE ID_PROFESOR = ?";
		        try (PreparedStatement st = conex.getConnection().prepareStatement(sentencia)) {
		            st.setString(1, profesor.getIdentificacion());
		            st.setString(2, profesor.getNombre());
		            st.setString(3, profesor.getApellido());
		            st.setString(4, profesor.getCorreo());
		            st.setString(5, profesor.getTelefono());
		            st.setString(6, profesor.getUsuario());
		            st.setString(7, profesor.getClave());	            
		            
		            
		            st.setInt(8, profesor.getId_profesores());
		            respuesta = st.executeUpdate();
		            System.out.println("Registros actualizados: " + respuesta);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error SQL al actualizar el profesor: " + e.getMessage());
		        e.printStackTrace();
		        return false;
		    } catch (Exception e) {
		        System.out.println("Error al actualizar el profesor: " + e.getMessage());
		        e.printStackTrace();
		        return false;
		    }
		    return respuesta > 0;
		}



		
		public int eliminar() {
			int respuesta = 0;
			try {
				this.sentencia = "DELETE FROM profesor WHERE ID_PROFESOR=?";
				PreparedStatement st = conex.getConnection().prepareStatement(sentencia);
				st.setInt(1, this.getId_profesores());
				respuesta = st.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
			return respuesta;
		}
		public String [] constultarDato(String id){
			String fila[]= new String[9];
			try {
			this.sentencia="Select * from profesor where ID_PROFESOR= '"+id+"'";
				java.sql.Statement st=(conex.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY));
				ResultSet datos = ((java.sql.Statement) st).executeQuery(this.sentencia);
				datos.beforeFirst();		
				while (datos.next()) {
					fila[0]=datos.getObject(1).toString();
					fila[1]=datos.getObject(2).toString();
					fila[2]=datos.getObject(3).toString();
					fila[3]=datos.getObject(4).toString();
					fila[4]=datos.getObject(5).toString();
					fila[5]=datos.getObject(6).toString();
					fila[6]=datos.getObject(7).toString();
					fila[7]=datos.getObject(8).toString();
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
	                    this.sentencia = "SELECT * FROM profesor WHERE ID_PROFESOR = " + id;
	                } else {
	                    this.sentencia = "SELECT * FROM profesor WHERE NOMBRE LIKE '%" + id+ "%'";
	                }
			 
				java.sql.Statement st=(conex.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY));
				ResultSet datos = ((java.sql.Statement) st).executeQuery(this.sentencia);
				datos.last();
				int nf=datos.getRow();
				obj= new Object[nf][8];
						datos.beforeFirst();
				int f=0;
				while (datos.next()) {
					obj[f][0]=datos.getObject(1);
					obj[f][1]=datos.getObject(2);
					obj[f][2]=datos.getObject(3);
					obj[f][3]=datos.getObject(4);
					obj[f][4]=datos.getObject(5);
					obj[f][5]=datos.getObject(6);
					obj[f][6]=datos.getObject(7);
					obj[f][7]=datos.getObject(8);
					f++;	
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return obj;
		}

}
