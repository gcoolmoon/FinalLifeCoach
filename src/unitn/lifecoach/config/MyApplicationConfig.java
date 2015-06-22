package unitn.lifecoach.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("lifecoachapp")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("unitn");
    }
}
