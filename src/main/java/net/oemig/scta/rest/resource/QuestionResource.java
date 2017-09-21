package net.oemig.scta.rest.resource;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.Question;
import net.oemig.scta.Store;

//http://localhost:8080/webapp/rest/admin
@Path("/admin/questions") 
public class QuestionResource {
	
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
	public Collection<Question> getQuestionsById(@PathParam("id") String id){
		return Store.getInstance().getQuestionsById(id);
	}

}
