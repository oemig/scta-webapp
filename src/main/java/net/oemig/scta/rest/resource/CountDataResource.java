package net.oemig.scta.rest.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.CountData;
import net.oemig.scta.Message;
import net.oemig.scta.Store;

//http://localhost:8080/webapp/rest/admin
@Path("/admin/countdata") 
public class CountDataResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CountData> getCountData(){
		System.out.println(CountDataResource.class.getName()+": all count data retrieved.");
		return Store.getInstance().getCountData();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message post(CountData aCountData){
		System.out.println(CountDataResource.class.getName()+": count data - "+aCountData.toString()+" - added.");
		String id=Store.getInstance().addCountData(aCountData);
		return new Message(
				"CountData (Id:"+id+") added successfully.",
				id);
	}


}
