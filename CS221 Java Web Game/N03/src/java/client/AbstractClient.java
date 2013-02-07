package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import entity.Server;
//import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

/**
 *
 * @author James Bowcott
 */
public abstract class AbstractClient {
    protected WebResource webResource;
    protected Client client;
    
    private Server server;

    public AbstractClient(Server server) {
	ClientConfig config = new DefaultClientConfig();
	client = Client.create(config);
	client.setConnectTimeout(5000);
	client.setReadTimeout(10000);
	this.server = server;
	this.webResource = client.resource(server.getServiceRootURL());
    }
    
    public AbstractClient(Server server, String rootPath) {
	this(server);
	this.webResource = webResource.path(rootPath);
    }

    public void close() {
	client.destroy();
    }
    
    protected WebResource getWebResource() {
	return webResource;
    }

    public Server getServer() {
	return server;
    }

    public void setServer(Server server) {
	this.server = server;
    }
    
}
