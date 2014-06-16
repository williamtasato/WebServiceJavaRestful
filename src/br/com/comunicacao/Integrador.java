package br.com.comunicacao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.business.Parametros;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;






@Path("/Integrador")
public class Integrador {
	private static String Pacote="br.com.acesso.";
	
	@SuppressWarnings("rawtypes")
	@POST
	@Path("/ChamaFuncao")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String ChamaFuncao(String json) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JsonParseException, JsonMappingException, IOException, JSONException{
		
		JSONObject object=new JSONObject(json);
		
		Parametros parametro=	new Parametros();
		parametro.setClasse(object.getString("Classe"));
		parametro.setMetodo(object.getString("Metodo"));
		if(object.isNull("Json")){
				}else{
			parametro.setJson(object.getString("Json"));
		}
		Class classe= Class.forName(Pacote +parametro.getClasse());
		Class[] tipoParametro=new Class[1];
		tipoParametro[0]=String.class;
		@SuppressWarnings("unchecked")
		Method metodo=classe.getMethod(parametro.getMetodo(), tipoParametro);
		Object[] parametros=new Object[1];
		parametros[0]=parametro.getJson();
		Object instancia=classe.newInstance();
		return	(String) metodo.invoke(instancia, parametros[0]);
	
	}

}
