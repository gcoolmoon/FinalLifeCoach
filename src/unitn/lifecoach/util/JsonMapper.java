package unitn.lifecoach.util;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonMapper {

	public String ToJson(Object entity)
	{
		ObjectMapper mapper = new ObjectMapper();
	 
		try {
	 
			// convert user object to json string, and save to a file
			mapper.writeValue(new File("c:\\user.json"), entity);
	 
			// display to console
			System.out.println(mapper.writeValueAsString(entity));
			return mapper.writeValueAsString(entity);
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
      return null;
	}
	public Object JsonToEntity(String json)
	{
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
	 
			// read from file, convert it to user class
			Object user = mapper.readValue(new File("c:\\user.json"), Object.class);
	 
			// display to console
			System.out.println(user);
			return user;
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
	 return null;
	  }
	 
}
