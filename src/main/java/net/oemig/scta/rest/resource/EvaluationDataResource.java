package net.oemig.scta.rest.resource;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.EvaluationData;
import net.oemig.scta.Store;

//http://localhost:8080/webapp/rest/admin
@Path("/admin/evaluationdata") 
public class EvaluationDataResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<EvaluationData> getEvaluationData(){
		System.out.println(EvaluationDataResource.class.getName()+": get evaluation data");
		return Store.getInstance().getEvaluationData();
	}
}
