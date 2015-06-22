package client;

import java.util.List;

import unitn.lifecoach.processcentric.*;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
 
public class LifeCoachClient{
	public static void main(String[] args) throws Exception {
	    URL url = new URL("http://localhost:6903/lifecoach/?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://processcentric.lifecoach.unitn/", "personImplService");
        Service service = Service.create(url, qname);
        person hello = service.getPort(person.class);
        System.out.println("##############################"+hello.getPeople());
    
    }
}