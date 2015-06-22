package unitn.lifecoach.dataacess;

import unitn.lifecoach.dao.LifeCoachDao;
import unitn.lifecoach.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.runners.Parameterized.Parameters;

import sun.invoke.empty.Empty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 

















//import org.apache.http.HttpResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.Serializable;

@Stateless
@LocalBean
@Path("/foodSuggestion")
public class DailyrecipeSuggestionsResource implements Serializable{

	private static final long serialVersionUID = 1L;

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@SuppressWarnings("rawtypes")
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Recipe> getAvailablegoals() throws UnirestException, JsonParseException, JsonMappingException, IOException
	{
		 List<Recipe> list = new ArrayList<Recipe>();
		
		HttpResponse<JsonNode> response = Unirest.get("https://community-food2fork.p.mashape.com/get?key=3e9166ad629eca6587a5e501e4e30961&rId=37859")
				.header("X-Mashape-Key", "BKZAyT5EpvmshSYHZGoWa6xZVMofp1DlKfyjsnx4R6kTXRxiu9")
				.header("Accept", "application/json")
				.asJson();
		JSONObject recipe= response.getBody().getObject();
		Iterator x = recipe.keys();
		JSONArray jsonArray = new JSONArray();

		while (x.hasNext()){
		    String key = (String) x.next();
		    jsonArray.put(recipe.get(key));
		}
				
			  for (int i = 0; i < jsonArray.length(); i++) {
			    JSONObject jsonObj = jsonArray.getJSONObject(i);
			     ObjectMapper mapper = new ObjectMapper();
			    Recipe usr = mapper.readValue(jsonObj.toString(), Recipe.class);      
			    list.add(usr);
			    System.out.print(list.toString());
			    		
			  		}
			  return list;		  
	}
	
	
}
