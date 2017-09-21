package net.oemig.scta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import net.oemig.scta.question.QuestionFactory;

/**
 * Singleton implementation!
 * 
 * @author christoph.oemig
 *
 */
public class Store {
	
	//minutes
	private static final int MILLIS_BETWEEN_FREEZE_PROBES = 1*60*1000;
	private static final int MAX_TIME=10000;
	private static final int MAX_FREEZE_PROBES=3;
	
	private static Store instance=null;
	
	public static Store getInstance(){
		if(null==instance){
			instance=new Store();
		}
		
		return instance;
	}

	private Map<String, Participant> participants;
	private Map<String,Document> documents;
	private Map<String, Run> runs;
	private Multimap<String,CountData> countData;
	private Multimap<String,ResponseData> responseData;
	private Map<Date,String> log;
	private int participantCounter;
	private int runCounter;
	private int documentCounter;
	private int countdataCounter;
	private int responsedataCounter;
	
	//this is the timer to launch the freeze probe
	private Timer timer;
//	private Status status;

	private Store(){
		participants=Maps.newHashMap();
		documents=Maps.newHashMap();
		runs=Maps.newHashMap();
		log=Maps.newHashMap();
		countData=ArrayListMultimap.create();
		responseData=ArrayListMultimap.create();
		participantCounter=0;
		runCounter=0;
		documentCounter=0;
		countdataCounter=0;
		responsedataCounter=0;
		//TODO remove
		//initial STATE!! no state logic in this class!!!
		//FIXME use status ENUM
//		status=new Status("CLOSED");
	}
	//PARTICIPANTS-------------------------------------------------------------
	public String addParticipant(Participant aParticipant){
		aParticipant.setId(Integer.valueOf(participantCounter).toString());
		//FIXME use status ENUM
		aParticipant.setStatus("REGISTERED");
		participants.put(aParticipant.getId(), aParticipant);
		participantCounter++;
		return aParticipant.getId();
	}
	
	public Participant getParticipantById(String anId){
		return participants.get(anId);
	}
	
	public String updateParticipant(Participant aParticipant){
		participants.put(aParticipant.getId(), aParticipant);
		return aParticipant.getId();
	}
	
	public Collection<Participant>getParticipants(){
		return participants.values();
	}
	//DOCUMENTS---------------------------------------------------------------
	public String addDocument(Document aDocument){
		aDocument.setId(Integer.valueOf(documentCounter).toString());
		documents.put(aDocument.getId(), aDocument);
		documentCounter++;
		return aDocument.getId();
	}
	
	public Document getDocumentById(String anId){
		return documents.get(anId);
	}
	
	public Collection<Document>getDocuments(){
		return documents.values();
	}
	
	//COUNTDATA--------------------------------------------------------------
	public String addCountData(CountData aCountData){
		aCountData.setId(Integer.valueOf(countdataCounter).toString());
		countData.put(aCountData.getParticipantId(), aCountData);
		countdataCounter++;
		return aCountData.getId();
	}
	
	public Collection<CountData>getCountData(){
		return countData.values();
	}
	//RESPONSEDATA-----------------------------------------------------------
	public String addResponseData(ResponseData aResponseData){
		aResponseData.setId(Integer.valueOf(responsedataCounter).toString());
		responseData.put(aResponseData.getParticipantId(), aResponseData);
		responsedataCounter++;
		return aResponseData.getId();
	}
	
	public Collection<ResponseData> getResponseData(){
		return responseData.values();
	}
	//QUESTIONS--------------------------------------------------------------
	public Collection<Question> getQuestionsById(String anId){
		return QuestionFactory.getMultiple(anId, getCountData());
	}
	//RUNS-------------------------------------------------------------------
	public String addRun(Run aRun){
		aRun.setId(Integer.valueOf(runCounter).toString());
		//FIXME use status enum
		aRun.setStatus("CREATED");
		runs.put(aRun.getId(), aRun);
		runCounter++;
		
		Document d=new Document("?", Letters.create());
		String docId=this.addDocument(d);
		//FIXME add doc id to run
		return aRun.getId();
	}
	public String updateRun(Run aRun) {
		runs.put(aRun.getId(), aRun);
		return aRun.getId();
	}	
	
	public Run getRunById(String anId){
		return runs.get(anId);
	}
	
	public Collection<Run>getRuns(){
		return runs.values();
	}
	//STATUS-----------------------------------------------------------------
	//TODO remove
// makes no sense.. status is calculated (see below)!!!!
//	public void setStatus(Status aStatus){
//		status=aStatus;
//	}
	
	//FIXME only capable of handling a single run!!!!
	public Status getStatus(){
		//Der Status ist abgeleitet aus dem Status der Runs!!
		//gibt es einen run im status offen?
		//ja: return open
		//else: gibt es einen run im Status running?
			//ja: counting
			//else: gibt es eine run im Status probing?
				//ja: return probing
				//else return closed
		Collection<Run> runs=getRuns();
		if(runs.isEmpty()){
			return new Status(Status.CLOSED);
		}else{
			final Multimap<String, Run> statusToRuns=ArrayListMultimap.create();
			for(Run r:runs){
				statusToRuns.put(r.getStatus(), r);
			}
			if(statusToRuns.get(Status.OPEN).size()>0){
				return new Status(Status.OPEN);
			}
			if(statusToRuns.get(Status.RUNNING).size()>0){
				//we are in RUNNING state
				//if you havent done so set up a timer and let it fire 
				//when the freezeprobe is due
				if(null==timer){
					System.out.println(Store.class.getName()+": setup timer.");
					ActionListener taskPerformer = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.out.println(Store.class.getName()+": timer fired. Trigger freeze probe.");
							triggerFreezeProbe(statusToRuns.get(Status.RUNNING));
						}
					};
					timer=new Timer(MILLIS_BETWEEN_FREEZE_PROBES, taskPerformer);
					timer.start();
				}
				
				return new Status(Status.RUNNING);
			}
			
			
			if(statusToRuns.get(Status.PROBING).size()>0){
				Multimap<String,Participant>participantsToStatus=ArrayListMultimap.create();
				for(Participant p:getParticipants()){
					participantsToStatus.put(p.getStatus(), p);
				}
				if(getParticipants().size()==participantsToStatus.get(Participant.FINISHED_FREEZE_PROBE).size()){
					//all participants finshed freezeprobe
					for(Run r: statusToRuns.get(Status.PROBING)){
						r.setStatus(Status.RUNNING);
					}
					for(Participant p:participantsToStatus.get(Participant.FINISHED_FREEZE_PROBE)){
						p.setStatus(Participant.COUNTING);
					}
					return new Status(Status.RUNNING);
				}
				return new Status(Status.PROBING);
			}
			
			if(statusToRuns.get(Status.COMPLETED).size()>0){
				return new Status(Status.COMPLETED);
			}

		}
		//still here, e.g. when there is only a run in CREATED
		return new Status(Status.CLOSED);
	}
	
	public Collection<EvaluationData> getEvaluationData(){
		List<EvaluationData> result=Lists.newArrayList();
		Multimap<String,ResponseData>questionTypeToResponseData=ArrayListMultimap.create();
		for(ResponseData r: getResponseData()){
			questionTypeToResponseData.put(r.getQuestionType(), r);
		}
		
		for(String qT:questionTypeToResponseData.keySet()){
			//questionspecific results
			int counterCorrect=0;
			int speed=0;
			int baseNumber=questionTypeToResponseData.get(qT).size();
			for(ResponseData r:questionTypeToResponseData.get(qT)){
				if(r.isCorrect()){
					counterCorrect++;
				}
				speed=speed+r.getMilliseconds();
			}
			
			
			EvaluationData ed=new EvaluationData(
					qT,
					Double.valueOf((MAX_TIME-(speed/baseNumber))*100/MAX_TIME).intValue(),
					Double.valueOf(counterCorrect*100/baseNumber).intValue(),
					qT
					);
			
			result.add(ed);
		}
		return result;
		
	}
	
	public void log(String aSource, String aLogEntry){
		Date d=new Date();
		log.put(d, "("+aSource+"): "+aLogEntry);
	}
	
	private void triggerFreezeProbe(Collection<Run> someRunningRuns){
		timer.stop();
		timer=null;
		for(Run r: someRunningRuns){
			if(r.getFreezeProbes()<MAX_FREEZE_PROBES){
				System.out.println(Store.class.getName()+": have not reached max freeze probes, yet. Do probe!");
				r.setStatus(Status.PROBING);
				r.setFreezeProbes(r.getFreezeProbes()+1);
			}else{
				System.out.println(Store.class.getName()+": max freeze probes reached. Run COMPLETED!");
				r.setStatus(Status.COMPLETED);
			}
		}
		
	}

}
