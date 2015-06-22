package unitn.lifecoach.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Properties;
import java.lang.Math;

import javax.jws.WebService;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import unitn.lifecoach.model.*;

import org.glassfish.jersey.client.ClientConfig;


@WebService(endpointInterface = "unitn.lifecoach.business.LifecoachBussiness")
public class LifeCoachBussImp implements LifecoachBussiness {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner b = new Scanner(System.in); 
	static javax.ws.rs.core.Response resp = null;
	static ClientConfig clientConfig = new ClientConfig();
	static javax.ws.rs.client.Client client = ClientBuilder.newClient(clientConfig);	
	static WebTarget service = client.target("http://localhost:5900/lifecoachapp/");
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

	private static void getMethod(String path) {
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).get();
		System.out.println("=> Result: "+ resp.getStatus()+ " \n=> HTTP Status: "+ resp.getStatus()+ "\n\n"+  resp.readEntity(String.class));
		}
	
	public Boolean sendEmail(String mes, int personID)
	{
		//Get email
				Person p = (Person)getPerson(personID);
				String email = p.getEmail();
		// Recipient's email ID needs to be mentioned.
	      String to = email;

	      // Sender's email ID needs to be mentioned
	      String from = "web@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("Reminder Check this out !!!");

	         // Now set the actual message
	         message.setText(mes);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	         return false;
	      }
		return true;
	}
	@Override
	public Boolean emailReminder(int personId) {
		// TODO Auto-generated method stub
		//Get reminder
		Reminder reminder = (Reminder)getReminder(personId);
		return sendEmail(reminder.getAppointmentDescription(), personId);
		//Get email
		
	}
@Override
    public boolean sendTweet(String message)throws IOException, TwitterException
{

    //Your Twitter App's Consumer Key
    String consumerKey = "iOrsJc7WQmhTKPt0LAIQ2LLH7";

    //Your Twitter App's Consumer Secret
    String consumerSecret = "UCVjKfAkCWtRWYj6fwLCavxlGubqH7O6OSZ9YQGC1Z64yzJ0Pn";

    //Your Twitter Access Token
    String accessToken = "124418182-whF9Jq1wlKiAtyyGCggpI3r31mXhrzVv5SN2Lurm";

    //Your Twitter Access Token Secret
    String accessTokenSecret = "GnabNDrsW5TAfZ7hyon39O0WEVCz3qFCPj3jbXpvnxFRT";

    //Instantiate a re-usable and thread-safe factory
    TwitterFactory twitterFactory = new TwitterFactory();

    //Instantiate a new Twitter instance
    Twitter twitter = twitterFactory.getInstance();

    //setup OAuth Consumer Credentials
    twitter.setOAuthConsumer(consumerKey, consumerSecret);

    //setup OAuth Access Token
    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

    //Instantiate and initialize a new twitter status update
    StatusUpdate statusUpdate = new StatusUpdate(message);
    //attach any media, if you want to
   /* statusUpdate.setMedia(
            //title of media
            "http://h1b-work-visa-usa.blogspot.com", new URL("http://lh6.ggpht.com/-NiYLR6SkOmc/Uen_M8CpB7I/AAAAAAAAEQ8/tO7fufmK0Zg/h-1b%252520transfer%252520jobs%25255B4%25255D.png?imgmax=800").openStream());
*/
    //tweet or update status
    Status status = twitter.updateStatus(statusUpdate);
	return true;
	
	}
	
@Override	
    public Object getReminder(int personId) {
		// TODO Auto-generated method stub
		String path = "reminder/" + personId; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).get();
		
		return resp.readEntity(Reminder.class);
	}

	@Override
	public Object getPerson(int personId) {
		// TODO Auto-generated method stub
		String path = "person"+personId;
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).get();
		
		return resp.readEntity(Person.class);
	}
	
	@Override
	public boolean goalTrackingService(int personId, int measurementType)
	{
		//get current measure value of a person based on measure 
		String path = "healthmeasurementhistory/"+personId+"/"+measurementType; 
		resp =service.path(path).request().accept(MediaType.APPLICATION_JSON).get();
		Healthmeasurehistory healthmeasure = resp.readEntity(Healthmeasurehistory.class);
		int currentMeasure = healthmeasure.getHealthValue();
		
		//get current goal of a person based on measure
		String pathgoal = "lifestylegoal/"+personId+"/"+measurementType; 
		resp =service.path(pathgoal).request().accept(MediaType.APPLICATION_JSON).get();
		Lifestylegoal healthmeasuregoal = resp.readEntity(Lifestylegoal.class);
		float lifegoal = healthmeasuregoal.getGoalValue();
		
		//get recipe information 
		String pathrecipe = "lifestylegoal/"+personId+"/"+measurementType; 
		resp =service.path(pathrecipe).request().accept(MediaType.APPLICATION_JSON).get();
		String recipe = resp.readEntity(String.class);
		//float lifegoal = healthmeasuregoal.getGoalValue();
		
		//comapre the values 
		if(currentMeasure == lifegoal){
			//send a motivation saying bravo
			}
		else if(Math.abs((int)(currentMeasure-lifegoal))<5){
			//send a reminder
			try{
			sendTweet("You are too close to your goal.");
			}
			catch(TwitterException e)
			{
				return false;
			}
			catch(IOException e)
			{
				return false;
			}
			return sendEmail(recipe, personId);
			}
		else {
			//send a reminder in twitter
			try{
				sendTweet("You are far from your goal. You need to work out.");}
				catch(TwitterException e)
				{
					return false;
				}
				catch(IOException e)
				{
					return false;
				}
		}
		return true;
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

}
