package net.oemig.scta.rest.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.Message;
import net.oemig.scta.ResponseData;
import net.oemig.scta.Store;

//http://localhost:8080/webapp/rest/admin
@Path("/admin/responsedata") 
public class ResponseDataResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ResponseData> getResponseData(){
		System.out.println(ResponseDataResource.class.getName()+": retrieve all response data.");
		return Store.getInstance().getResponseData();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message post(ResponseData aResponseData){
		System.out.println(ResponseDataResource.class.getName()+": response data - "+aResponseData.toString()+" - added.");
		String id=Store.getInstance().addResponseData(aResponseData);
		return new Message("ResponseData (Id: "+id+") added successfully.",id);
	}
	

}
