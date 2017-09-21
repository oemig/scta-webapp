package net.oemig.scta.rest.resource;

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
import net.oemig.scta.Run;
import net.oemig.scta.Store;

/**
 * {@link RunResource}
 * @author CO
 *
 */
//http://localhost:8080/webapp/rest/admin/documents
@Path("/admin/runs") 
public class RunResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Run> getRuns(){
		System.out.println(RunResource.class.getName()+": retrieve all runs.");
		return Store.getInstance().getRuns();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Run getRun(@PathParam("id")String anId){
		System.out.println(RunResource.class.getName()+": retrieve run with id ("+anId+")");
		return Store.getInstance().getRunById(anId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message post(Run aRun){
		String id=Store.getInstance().addRun(aRun);
		System.out.println(RunResource.class.getName()+": created run (id: "+id+")");
		return new Message("Run created (id: "+ id+") - ok",id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message put(Run aRun){
		String id=Store.getInstance().updateRun(aRun);
		System.out.println(RunResource.class.getName()+": updated run (id: "+id+")");
		return new Message("Run updated (id: "+ id+") - ok", id);
	}
}
