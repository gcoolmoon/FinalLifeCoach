package unitn.lifecoach.processcentric;

import javax.jws.WebService;
import javax.swing.text.html.parser.Entity;
import javax.ws.rs.client.Client;   
//import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.Service;

import org.json.*;
import org.codehaus.jackson.map.introspect.BasicClassIntrospector.GetterMethodFilter;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;    
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import sun.security.provider.certpath.OCSPResponse.ResponseStatus;
import unitn.lifecoach.business.LifecoachBussiness;

import javax.ws.rs.core.MediaType;


@WebService(endpointInterface = "unitn.lifecoach.processcentric.person")
public class personImpl implements person {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner b = new Scanner(System.in); 
	static javax.ws.rs.core.Response resp = null;
	static ClientConfig clientConfig = new ClientConfig();
	static javax.ws.rs.client.Client client = ClientBuilder.newClient(clientConfig);	
	static WebTarget service = client.target("http://api.theysaidso.com/");
	
//	initiate bussness class
	
	 
   static LifecoachBussiness hello = null;
   // System.out.println("##############################"+hello.getPeople());
     
     
     private static void init() throws Exception
     {
    	 URL url = new URL("http://localhost:6903/lifecoach/?wsdl");
         // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://processcentric.lifecoach.unitn/", "personImplService");
        Service service1 = Service.create(url, qname);
         hello = service1.getPort(LifecoachBussiness.class);
       // System.out.println("##############################"+hello.getPeople());
     }
     
	private static void deleteMethod(String path) {
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).delete();
		System.out.println("=> Result: "+ resp.getStatus()+ " \n=> HTTP Status: "+ resp.getStatus()+ "\n\n"+  resp.readEntity(String.class));
		}

	private static void putMethod(String path, String data) {
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(data));
		System.out.println("=> Result: "+ resp.getStatus()+ " \n=> HTTP Status: "+ resp.getStatus()+ "\n\n"+  resp.readEntity(String.class));
		}

	private static void postMethod(String path, String data) {
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.json(data));
		System.out.println("=> Result: "+ resp.getStatus()+ " \n=> HTTP Status: "+ resp.getStatus()+ "\n\n"+  resp.readEntity(String.class));
		}

	private static String getMethod(String path) {
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).get();
		//System.out.println("=> Result: "+ resp.getStatus()+ " \n=> HTTP Status: "+ resp.getStatus()+ "\n\n"+  resp.readEntity(String.class));
		return resp.readEntity(String.class);
	}

	

	@Override
	public String getPeople() throws ParseException {
		// TODO Auto-generated method stub
		/*resp =service.path("caregiver").request().accept(MediaType.Appli).get();
		System.out.println("=> Result: "+ resp.getStatus()+ " \n=> HTTP Status: "+ resp.getStatus()+ "\n\n"+  resp.readEntity(String.class));
		*/
		//JSONObject
		String s =getMethod("qod.json");
		org.json.JSONObject jsonObject = new org.json.JSONObject(s);

		String quote = jsonObject.getJSONObject("contents").getString("quote");
		
		//array. = JSONArray.
		//array.add(obj);
        System.out.println(quote);
		return quote;
		//return "Hello World";
	}



	@Override
	public boolean registerReminder(String input) {
		// TODO Auto-generated method stub
		String path = "reminder"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(input));
		
		if(resp.getStatus() == 200)
		return true;
		else 
		return false;
	}

	@Override
	public boolean setGoals(String input) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String Getmotivation()
	{
		String path = "healthmeasurementhistory/motivation"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).get();
		org.json.JSONObject jsonObject = new org.json.JSONObject(resp.readEntity(String.class));

		String quote = jsonObject.getJSONObject("contents").getString("quote");
		return quote;
	}

	@Override
	public Boolean AddNewPerson(String data) {
		// TODO Auto-generated method stub
		String path = "person"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
		
	}

	@Override
	public boolean registerHealthMeasure(int personId, String data) {
		// TODO Auto-generated method stub
		String path = "healthmeasure"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

	@Override
	public boolean registerLifeStyle(String input, String data) {
		// TODO Auto-generated method stub
		String path = "lifestylemeasure"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

	@Override
	public String GetCurrentMeasure(int personId, int MeasureTypeId) {
		// TODO Auto-generated method stub
		String path = "lifestylemeasure/"+MeasureTypeId + "/"+ personId; 
		return getMethod(path);
		
	}

	@Override
	public String GetAllCurrentMeasure(int personId) {
		// TODO Auto-generated method stub
		String path = "lifestylemeasure/"+ personId; 		
		return getMethod(path);
	}

	@Override
	public String GetAllCareGiver() {
		// TODO Auto-generated method stub
		String path = "lifestylemeasure/all"; 		
		return getMethod(path);
	}

	@Override
	public boolean registerCareGiver(String input) {
		// TODO Auto-generated method stub
		String path = "caregiver"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(input));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

	@Override
	public boolean registerHealthPersonGoal(int personId, String data) {
		// TODO Auto-generated method stub
		String path = "healthgoal"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}
	@Override
	public boolean registerLifeStylePersonGoal(int personId, String data) {
		// TODO Auto-generated method stub
		
		String path = "lifestylegoal"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

	

	@Override
	public String getAllPersonalLifestyleGoals(int personId) {
		// TODO Auto-generated method stub
		String path = "lifestylegoal/"+personId; 		
		return getMethod(path);
	}

	@Override
	public String getHealthMeasureDefinition() {
		// TODO Auto-generated method stub
		String path = "healthmeasurement/"; 		
		return getMethod(path);
	}
	@Override
	public String getLifestyleMeasureDefinition() {
		// TODO Auto-generated method stub
		String path = "lifestylemeasurement/"; 		
		return getMethod(path);
	}
	@Override
	public String getHealthMeasureHistory(int personId, int measureTypeId) {
		// TODO Auto-generated method stub
		String path = "healthmeasure/"+personId; 		
		return getMethod(path);
	}
	@Override
	public String getLifestyleMeasureHistory(int personId, int measureTypeId) {
		// TODO Auto-generated method stub
		String path = "lifestylemeasure/"+personId; 			
		return getMethod(path);
	}

	@Override
	public String getReminder(int personId) {
		// TODO Auto-generated method stub
		String path = "reminder/"+personId; 			
		return getMethod(path);
	}

	@Override
	public Boolean AddReminder(int personId, String data) {
		// TODO Auto-generated method stub
		String path = "reminder"+personId; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

	@Override
	public Boolean TrackGoal(int personId, int measureTypeId) {
		try{
		 URL url = new URL("http://localhost:6903/lifecoach/?wsdl");
         // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://processcentric.lifecoach.unitn/", "personImplService");
        Service service1 = Service.create(url, qname);
         hello = service1.getPort(LifecoachBussiness.class);
       // System.out.println("##############################"+hello.getPeople());
         return hello.goalTrackingService(personId, measureTypeId);
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public Boolean EmailReminder(int personId) {
		try{
			 URL url = new URL("http://localhost:6903/lifecoach/?wsdl");
	         // 1st argument service URI, refer to wsdl document above
	        // 2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://processcentric.lifecoach.unitn/", "personImplService");
	        Service service1 = Service.create(url, qname);
	         hello = service1.getPort(LifecoachBussiness.class);
	       // System.out.println("##############################"+hello.getPeople());
	         return hello.emailReminder(personId);
			}catch(Exception e)
			{
				return false;
			}
	}

	@Override
	public Boolean PostTweet(String message) {
		try{
			 URL url = new URL("http://localhost:6903/lifecoach/?wsdl");
	         // 1st argument service URI, refer to wsdl document above
	        // 2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://processcentric.lifecoach.unitn/", "personImplService");
	        Service service1 = Service.create(url, qname);
	         hello = service1.getPort(LifecoachBussiness.class);
	       // System.out.println("##############################"+hello.getPeople());
	         return hello.sendTweet(message);
			}catch(Exception e)
			{
				return false;
			}
	}

	@Override
	public boolean registerHealthCurrentMeasure(int personId, String data) {
		// TODO Auto-generated method stub
		String path = "healthmeasure"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

	@Override
	public boolean registerLifeStyleCurrentMeasure(int personId, String data) {
		// TODO Auto-generated method stub
		String path = "lifestylemeasure"; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(data));
		
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

	@Override
	public String getPersonalHealthGoal(int personId, int measureTypeId) {
		// TODO Auto-generated method stub
		String path = "reminder/"+personId; 			
		return getMethod(path);
	}

	@Override
	public String getPersonalLifestyleGoal(int personId, int measureTypeId) {
		// TODO Auto-generated method stub
		String path = "lifestylegoal/"+measureTypeId+"/"+personId; 			
		return getMethod(path);
	}

	@Override
	public String getPersonalLifestyleGoal(int personId) {
		// TODO Auto-generated method stub
		String path = "lifestylegoal/"+personId; 			
		return getMethod(path);
	}

	@Override
	public String getPersonalHealthGoal(int measureTypeId) {
		// TODO Auto-generated method stub
		String path = "reminder/"+measureTypeId; 			
		return getMethod(path);
	}

	@Override
	public String getAllPersonalHealthGoals(int personId) {
		// TODO Auto-generated method stub
		String path = "healthgoal/"+personId; 		
		return getMethod(path);
	}

	@Override
	public Boolean FoodSuggestion(String message) {
		// TODO Auto-generated method stub
		String path = "foodsuggestion/"; 		
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(message));
		if(resp.getStatus() == 200)
		return true;
		else 
	    return false;
	}

}
