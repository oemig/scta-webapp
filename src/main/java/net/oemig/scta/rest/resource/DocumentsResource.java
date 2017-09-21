package net.oemig.scta.rest.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oemig.scta.Document;
import net.oemig.scta.Message;
import net.oemig.scta.Store;

//http://localhost:8080/webapp/rest/admin/documents
@Path("/admin/documents") 
public class DocumentsResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Document> getDocuments(){
		System.out.println(DocumentsResource.class.getName()+": retrieve all documents.");
		return Store.getInstance().getDocuments();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Document getDocument(@PathParam("id")String anId){
		System.out.println(DocumentsResource.class.getName()+": retrieve document with ID--"+anId);
		return Store.getInstance().getDocumentById(anId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message post(Document aDocument){
		String id=Store.getInstance().addDocument(aDocument);
		System.out.println(DocumentsResource.class.getName()+": created document with ID--"+id);
		return new Message(
				"Document created with ID - "+id+" - ok.",
				id);
		
	}

}
