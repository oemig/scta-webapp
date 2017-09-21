package net.oemig.scta.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.Status;
import net.oemig.scta.Store;

//http://localhost:8080/webapp/rest/admin/status
@Path("/admin/status") 
public class StatusResource {
	
	
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Status getStatus() {
		Status s=Store.getInstance().getStatus();
		System.out.println(StatusResource.class.getName()+": status retrieved: "+s.getName());
	    return s;
	  }
	  
	  //TODO remove
//	  @POST
//	  @Consumes(MediaType.APPLICATION_JSON)
//	  @Produces(MediaType.APPLICATION_JSON)
//	  public Message post(Status aStatus){
//		  System.out.println(StatusResource.class.getName()+": status set to "+aStatus.getName());
//		  Store.getInstance().setStatus(aStatus);
//		  
//		  return new Message(
//				  "Set status to - "+aStatus.getName()+" - ok.",
//				  "0");
//	  }

}
