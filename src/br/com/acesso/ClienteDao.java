package br.com.acesso;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.business.Cliente;
import br.com.database.InteracaoDados;
import br.com.database.Pesquisa;
import br.com.util.Imagem;

public class ClienteDao {

	private InteracaoDados dados;
	private ResultSet rs;
	private Connection conn;
	private List<Cliente> list;
	private Cliente cli;
	private Imagem image=new Imagem();

	public String Inserir(String json) throws Exception {
		JSONObject object = new JSONObject(json);
		String sql = "";
		if (object.isNull("Foto")){
		if (object.getInt("Id") == 0) {
			sql = "Insert into TbClientes (Cli_Nome,Cli_Endereco,Cli_Telefone)"
					+ "values ('" + object.getString("Nome") + "','"
					+ object.getString("Endereco") + "','"
					+ object.getString("Telefone") + "');";
		} else {
			sql = "Update TbClientes set Cli_Nome='" + object.getString("Nome")
					+ "', Cli_Endereco='" + object.getString("Endereco")
					+ "', Cli_Telefone ='" + object.getString("Telefone")
					+ "' where Cli_Id =" + object.getInt("Id");
		}
		dados = new InteracaoDados();
		dados.Executar(sql);
		
		}else{
			if (object.getInt("Id") == 0) {
				sql = "Insert into TbClientes (Cli_Nome,Cli_Endereco,Cli_Telefone,Cli_Foto)"
						+ "values ('" + object.getString("Nome") + "','"
						+ object.getString("Endereco") + "','"
						+ object.getString("Telefone") + "',?);";
			} else {
				sql = "Update TbClientes set Cli_Nome='" + object.getString("Nome")
						+ "', Cli_Endereco='" + object.getString("Endereco")
						+ "', Cli_Telefone ='" + object.getString("Telefone")
						+ "', Cli_Foto= ? where Cli_Id =" + object.getInt("Id");
			}	
		
			
			image.insereImagem(sql, image.StringtoInputStream(object.getString("Foto")));
			
		}

		
		return json;
	}

	public String Pesquisar(String json) throws SQLException, IOException {

		String sql = "Select Cli_id, Cli_Nome, Cli_Endereco, Cli_Telefone,Cli_Foto from TbClientes;";

		dados = new InteracaoDados();

		list = new ArrayList<>();
		Pesquisa resultado = dados.Pesquisar(sql);
		rs = resultado.getResultSet();
		conn = resultado.getConnection();
		while (rs.next()) {
			cli = new Cliente();
			cli.setId(rs.getInt("Cli_id"));
			cli.setNome(rs.getString("Cli_Nome"));
			cli.setEndereco(rs.getString("Cli_Endereco"));
			cli.setTelefone(rs.getString("Cli_Telefone"));
			cli.setFoto( image.BlobtoString(rs.getBlob("Cli_Foto")));
			list.add(cli);
		}

		rs.close();
		conn.close();

		JSONArray jsonlist = new JSONArray(list);
		String result = jsonlist.toString();

		return result;
	}
	
	public String Deletar(String json) throws JSONException, SQLException{
		JSONObject obj=new JSONObject(json);
		
		String sql="Delete from TbClientes where Cli_id = " + obj.getInt("Id") ;
		
		dados=new InteracaoDados();
		dados.Executar(sql);
		return json;
	}
}
