package br.com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import br.com.database.Conexao;

public class Imagem {
	
	public void insereImagem( String sql,InputStream file) throws Exception {
;
	    PreparedStatement ps = null;
	    Conexao con=new Conexao("localhost", "Teste", "root", "");
	    Connection	 conn=con.getConexao();
	                
	        ps = conn.prepareStatement(sql);
	        if (file != null) {
	           
	            ps.setBinaryStream(1, file, (int) file.available());
	        } else {
	            ps.setNull(1, Types.BLOB);
	        }
	        
	        ps.executeUpdate();
	}
	
	 public String FiletoStringBase64(InputStream fis) throws IOException   
	    {  
		 String base64String = "";
	        
	            byte[] byteArray = new byte[4960];  
	            byteArray=InputStreamtobyte(fis);       
	                
	            base64String = Base64.encode(byteArray);   
	                              
	                  
	           
			return base64String;  
	    } 
	 public InputStream StringtoInputStream(String file){
		 InputStream is = new ByteArrayInputStream(
				 org.apache.tomcat.util.codec.binary.Base64.decodeBase64(file.getBytes()));
		 return is;
		 
	 }
	 
	 public String BlobtoString(Blob blob) throws SQLException, IOException{
		 String   base64String="";
		 
		 if (blob!= null){
		 InputStream binaryStream=blob.getBinaryStream();
		 byte[] image= InputStreamtobyte(binaryStream);
         base64String = Base64.encode(image); 
		 }
		return base64String;
		
		 
	 }
	 
	 public byte[] InputStreamtobyte(InputStream is) throws IOException{
		 ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		 int nRead;
		 byte[] data = new byte[4096];

		 while ((nRead = is.read(data, 0, data.length)) != -1) {
		   buffer.write(data, 0, nRead);
		 }

		 buffer.flush();

		 return buffer.toByteArray();
		 
	 }
}
