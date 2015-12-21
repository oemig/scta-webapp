package net.oemig.scta.rest.resource;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.Administration;
import net.oemig.scta.model.data.User;

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
