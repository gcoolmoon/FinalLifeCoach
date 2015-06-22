package publisher;


import javax.xml.ws.Endpoint;

import unitn.lifecoach.business.*;

public class LifeCoachPublisher {
	public static String SERVER_URL = "http://localhost";
    public static String PORT = "6903";
    public static String BASE_URL = "/lifecoach";
    public static String getEndpointURL() {
        return SERVER_URL+":"+PORT+BASE_URL;
    }
    public static void main(String[] args) {
        String endpointUrl = getEndpointURL();
        System.out.println("Starting Lifecoach Service...");
        System.out.println("--> Published at = "+endpointUrl);
        Endpoint.publish(endpointUrl, new LifeCoachBussImp());
    }
}
