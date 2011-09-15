package tutoringfx.adapter;

import com.sun.jersey.api.client.*;
import dukestutoring.entity.Student;
import java.util.List;

/**
 * Jersey REST client generated for REST resource:Expression REST_RESOURCE_NAME
 * is undefined on line 11, column 54 in
 * Templates/WebServices/JerseyClient.java.<br>
 *  USAGE:
 * <pre>
 *        StatusClient client = new StatusClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ievans
 */
public class StatusClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/dukes-tutoring/resources";

    public StatusClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("status");
    }

    public <T> T getGuardianByEmail_XML(Class<T> responseType, String guardianEmail) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("guardian/{0}", new Object[]{guardianEmail}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getGuardianByEmail_JSON(Class<T> responseType, String guardianEmail) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("guardian/{0}", new Object[]{guardianEmail}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getStatusByGuardianId_XML(Class<T> responseType, String guardianId) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("id/{0}", new Object[]{guardianId}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getStatusByGuardianId_JSON(Class<T> responseType, String guardianId) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("id/{0}", new Object[]{guardianId}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public List<Student> getStatusByGuardianEmail(String guardianEmail) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("email/{0}", new Object[]{guardianEmail}));
        ClientResponse response = resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(ClientResponse.class);
        return (List<Student>) response.getEntity(new GenericType<List<Student>>() {});
    }

    public <T> T getStatusByGuardianEmail_XML(Class<T> responseType, String guardianEmail) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("email/{0}", new Object[]{guardianEmail}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getStatusByGuardianEmail_JSON(Class<T> responseType, String guardianEmail) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("email/{0}", new Object[]{guardianEmail}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.destroy();
    }
    
}
