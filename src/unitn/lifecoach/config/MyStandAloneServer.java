package unitn.lifecoach.config;

	import unitn.lifecoach.dataacess.*;
	import java.io.IOException;
	import java.net.InetAddress;
	import java.net.URI;
	import java.net.URISyntaxException;
	import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
	import org.glassfish.jersey.server.ResourceConfig;

	public class MyStandAloneServer {
		public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	    {
	        String protocol = "http://";
	        String port = ":8000/";
	        String hostname = InetAddress.getLocalHost().getHostAddress();
	        if (hostname.equals("127.0.0.1"))
	        {
	            hostname = "localhost";
	        }

	        URI baseUrl = new URI(protocol + hostname + port);

	        ResourceConfig rc = new ResourceConfig(PersonCollectionResource.class );
	        JdkHttpServerFactory.createHttpServer(baseUrl, rc);
	        System.out.println("server starts on " + baseUrl + "\n [kill the process to exit]");
	    }

	}

