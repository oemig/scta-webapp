package net.oemig.scta.rest.resource;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.Administration;
import net.oemig.scta.model.data.User;

/**
 * {@link AdministrationResource}: this is the place where the REST calls come in.
 * <br>
 * <br>
 * <b>REST WS Best Practices</b>
 * <ul>
 * <li> we use a version number as part of the REST WS URL which typically looks like this: v1
 * <li> we use nouns instead of verbs
 * <li> we use HTTP GET, POST, PUT and DELETE to retrieve, create, update and delete data instances
 * <li> we use HATEOAS
 * </ul>
 * <pre>
 * This resource awaits request behind the following URL: http://localhost:8080/webapp/rest/admin
 * 
 * - ../webapp is the registered name of the web app itself (Standard JEE). The entire .war file.
 * - ../rest stems from the configuration inside the web.xml for the Jersey REST Service. This is
 *   the place where JERSEY makes REST resources it finds in the specified packages available.
 * - ../admin is the URL hook for this class, i.e., the {@link AdministrationResource}
 *    
 * 
 * 
 * 
 * 
 * </pre>
 * 
 * @author info@oemig.net
 *
 */
//http://localhost:8080/webapp/rest/admin
@Path("/admin") 
public class AdministrationResource {
	
	  @GET
	  @Path("users")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Collection<User> getUsers() {
		  System.out.println("TALKED TO JSON CLIENT");
	    return new Administration().getUsers();
	  }
	  
//	  @POST
//	  @Path("register")
//	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	  @Produces(MediaType.TEXT_PLAIN)
//	  public String register(@FormParam("user") String aUser,
//			  	@Context HttpServletResponse aResponse) throws IOException{
//		  new Administration().addUser(new User(aUser));
//		  
//		  return "ok";
//	  }
	  
	  @POST
	  @Path("post")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public String post(User aUser) throws IOException{
		  System.out.println("TALKED TO JSON.REGISTER	");
		  new Administration().addUser(aUser);
		  
		  return "ok";
	  }
	  

}
