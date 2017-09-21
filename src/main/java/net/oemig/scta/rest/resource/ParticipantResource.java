package net.oemig.scta.rest.resource;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.Message;
import net.oemig.scta.Participant;
import net.oemig.scta.Store;

/**
 * {@link ParticipantResource}: this is the place where the REST calls come in.
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
 * - ../admin is the URL hook for this class, i.e., the {@link ParticipantResource}
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
@Path("/admin/participants") 
public class ParticipantResource {

	  /**
	   * Retrieves and returns all currently registered participants
	   * @return a collection of participants (all)
	   */
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Collection<Participant> getParticipants() {
		System.out.println(ParticipantResource.class.getName()+": participant list retrieved.");
	    return Store.getInstance().getParticipants();
	  }
	  
	  /**
	   * Retrieves and returns the participant identified by the given id
	   * @param id - identifies the participant
	   * @return
	   */
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("{id}")
	  public Participant getParticipantById(@PathParam("id") String id){
		  return Store.getInstance().getParticipantById(id);
	  }
	  

	  /**
	   * Creates the given participant as new.
	   * @param aParticipant
	   * @return
	   * @throws IOException
	   */
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Message post(Participant aParticipant) throws IOException{
		  System.out.println(ParticipantResource.class.getName()+": participant "+aParticipant.toString()+" registered.");
		  String id=Store.getInstance().addParticipant(aParticipant);
		  
		  return new Message(
				  "Participant "+aParticipant.getName()+" (Id:"+id+") registered successfully.",
				  id);
	  }
	  
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Message put(Participant aParticipant){
			String id=Store.getInstance().updateParticipant(aParticipant);
			System.out.println(ParticipantResource.class.getName()+": update participant--> "+aParticipant.toString());
			return new Message("Participant updated (id: "+id+") - ok",id);
		}

	  

}
