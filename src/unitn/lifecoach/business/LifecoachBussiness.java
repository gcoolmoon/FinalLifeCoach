package unitn.lifecoach.business;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import twitter4j.TwitterException;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface LifecoachBussiness {

	 //method 1
    @WebMethod(operationName="emailReminder")
    @WebResult(name="people") 
    public Boolean emailReminder(@WebParam(name="personId") int personId);
    //method 2
    @WebMethod(operationName="getReminder")
    @WebResult(name="people") 
    public Object getReminder(@WebParam(name="personId") int personId);
  //method 3
    @WebMethod(operationName="getPerson")
    @WebResult(name="people") 
    public Object getPerson(@WebParam(name="personId") int personId);
	
    
	@WebMethod(operationName="sendTweet")
	@WebResult(name="tweet")
	public boolean sendTweet(String message) throws IOException, TwitterException;
	
	// goal tracking and based on her/his performance send motivation message
	@WebMethod(operationName="goalTrackingService")
	@WebResult(name="tweet")
	public 	boolean goalTrackingService(int personId, int measurementType);
	
	@WebMethod(operationName="getMotivation")
	@WebResult(name="motivation")
	public String Getmotivation();
	 
}
