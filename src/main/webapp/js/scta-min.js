/**
 * scta-min JavaScript library
 */
(function(window) {
'use strict'

function defineScta() {
  var Scta={
  STATES:{ WAITING: 0, MOD: 1, ADMIN: 2},
  letters: ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"],
  oldStatus: "UNKNOWN",
  
  getIpAddress: function(callback) {
	  console.log("Scta.ipAddress");
	  jQuery.ajax ({
		    url: "http://freegeoip.net/json/",
		    type: "GET",
		    success: function(result, status, xhr){
		        console.log(result);
		        callback(result);   
		    }
	  }); 	
	  
  },
  
  getCopyright: function() {
	  var year=new Date().getFullYear();
	  return "&copy; Christoph Oemig 2011-"+year;
  },
  
  getVersion: function() {
	  return "SCTA Version 2.1";
  },
  
  register: function(aParticipantName){
		console.log(aParticipantName);
		
		var u=new Object();
		u.name=aParticipantName;
		u.ip=ip;
		u.id="unknown";
		u.status="unknown";
		
		jQuery.ajax ({
		    url: "/webapp/rest/admin/participants",
		    type: "POST",
		    data: JSON.stringify(u),
		    dataType: "json",
		    contentType: "application/json",
		    accept:"application/json",
		    success: function(data, status, xhr){
		        $("#login").fadeOut(1600,function(){});
		        $("#message").html("Hi "+aParticipantName+", great to have you here! Please stand by as we will start soon.").animate({left: '250px'});
		        
		        //set globally.. to identify participant
		        participantId=data.id;
		        participantName=aParticipantName;
		        
		        $("#footer").append(" - Participant: "+participantName+" (Id: "+participantId+")");
		    }
		}); 
  },
  createRun: function(runName){
	  	console.log("create run with name - "+runName);
	  	var r=new Object();
	  	r.id="unknown";
	  	r.freezeProbes=0;
	  	r.description="Please enter your description.";
	  	r.title=runName;
	  	
		jQuery.ajax ({
		    url: "/webapp/rest/admin/runs",
		    type: "POST",
		    data: JSON.stringify(r),
		    dataType: "json",
		    contentType: "application/json",
		    accept:"application/json",
		    success: function(data, status, xhr){
		        $("#createrun").fadeOut(1600,function(){});
		        $("#message").fadeIn(1600,function(){});
		        $("#message").text(data.text);
		        Scta.getParticipants();
		        Scta.getRuns();
		        
		    }
		}); 	  	
	  	
  },
  getParticipants: function(){
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/participants",
		    type: "GET",
		    success: function(result, status, xhr){
		    	console.log(result);
		    	$("#participants").html("<tr><th>Id</th><th>Name</th><th>Status</th><th>IP-Address</th></tr>");
		    	for(var i=0;i<result.length;i++){
		    		$("#participants").append('<tr><td>'+result[i].id+'</td><td>'+result[i].name+'</td><td>'+result[i].status+'</td><td>'+result[i].ip+'</td></tr>');
		    	}
		    	//show when done
		    	$("#participants").fadeIn(1600,function(){});
		    }
	  }); 		
  },
  getRuns: function(){
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/runs",
		    type: "GET",
		    success: function(result, status, xhr){
		    	console.log(result);
		    	//FIXME only show create run when there is no run in state OPEN
		    	if(0==result.length){
		    		$("#createrun").fadeIn(1600,function(){});
		    	}else{
			    	$("#runs").html("<tr><th>Id</th><th>Titel</th><th>Description</th><th># of Freeze Probes</th><th>Status</th><th>Action</th></tr>");
			    	for(var i=0;i<result.length;i++){
			    		var tablerow="<tr><td>"+result[i].id+"</td><td>"+result[i].title+"</td><td>"+result[i].description+"</td><td>"+result[i].freezeProbes+"</td><td>"+result[i].status+"</td><td>";
			    		if("CREATED"==result[i].status){
			    			tablerow=tablerow+"<button type='button' onclick='Scta.openRun("+result[i].id+")'>Open</button>";
			    		}
			    		if("OPEN"==result[i].status){
			    			tablerow=tablerow+"<button type='button' onclick='Scta.startRun("+result[i].id+")'>Start</button>";
			    		}
			    		tablerow=tablerow+"</td></tr>";
			    		
			    		$("#runs").append(tablerow);
			    	}
			    	//show when done
			    	$("#runs").fadeIn(1600,function(){});
		    	}
		    }
	  }); 		
	  
  },
  openRun: function(id){
	  console.log("open run with id "+id);
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/runs/"+id,
		    type: "GET",
		    success: function(result, status, xhr){
		    	console.log(result);
		    	//FIXME use run status ENUM
		    	result.status="OPEN";
		    	Scta.updateRun(result);
		    }
	  });
  },
  startRun:function(id){
	  console.log("start run with id "+id);
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/runs/"+id,
		    type: "GET",
		    success: function(result, status, xhr){
		    	console.log(result);
		    	//FIXME use run status ENUM
		    	//FIXME check number of participants first!!!
		    	result.status="RUNNING";
		    	Scta.updateRun(result);
		    }
	  });
  },
  updateRun: function(run){
	  console.log("update run with id "+run.id);
		jQuery.ajax ({
		    url: "/webapp/rest/admin/runs",
		    type: "PUT",
		    data: JSON.stringify(run),
		    dataType: "json",
		    contentType: "application/json",
		    accept:"application/json",
		    success: function(data, status, xhr){
		        $("#message").html("Run (Id: "+run.id+") was updated successfully.");
		    }
		}); 	

  },
  startAdminLoop: function(){
	console.log("Start admin looping...");
	var admintimer=window.setInterval(Scta.checkAdminStatus, 3000);
	  
  },
  checkAdminStatus: function(){
	Scta.getRuns();
	Scta.getParticipants();
  },
  startLoop: function(){
	console.log("Start looping...");
	var timer=window.setInterval(Scta.checkStatus, 3000);
	
  },
  checkStatus: function(){
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/status",
		    type: "GET",
		    success: function(result, status, xhr){
		        console.log(result);
		        if(Scta.oldStatus!=result.name){
			        //FIXME use scta state enum!!
		        	
		        	//the new becomes the old...
		        	Scta.oldStatus=result.name;
		        	//then switch status specific behavior
			        if(result.name=="RUNNING"){
			        	console.log("counting state detected...");
			        	Scta.handleRunningState();
			        }
			        if(result.name=="CLOSED"){
			        	//no run is created
			        	Scta.handleClosedState();
			        }
			        if(result.name=="OPEN"){
			        	//no run is created
			        	Scta.handleOpenState();
			        }
			        if(result.name=="PROBING"){
			        	Scta.handleProbingState();
			        }
			        
			        if(result.name=="COMPLETED"){
			        	Scta.handleCompletedState();
			        }
		        }else{
		        	console.log("no state change: "+Scta.oldStatus);
		        }

		    }
	  }); 		  
  },
  handleClosedState: function(){
	console.log("handle state: CLOSED");
	$("#message").html("Sorry, there is currently no open run. Stay tuned!").animate({left: '250px'});
  },
  handleCompletedState: function(){
	console.log("handle state: COMPLETED");  
	$("#document").fadeOut(1600,function(){
		$("#message").html("Hey, we are done. Good job! Thank you for your participation!").show().animate({left: '250px'});
		
		  jQuery.ajax ({
			    url: "/webapp/rest/admin/evaluationdata",
			    type: "GET",
			    success: function(json, status, xhr){
			    	
				    google.charts.load('current', {'packages':['corechart']});
				    google.charts.setOnLoadCallback(drawSeriesChart);

				    function drawSeriesChart() {
				    	var dataArray=[
					        ['ID', 'Speed', 'Success', 'Question Type','Pace'],
					      ];
				      
				    for (var i=0;i<json.length;i++){
				    	dataArray.push([json[i].id,json[i].speed,json[i].correctness,json[i].questionType,5]);
				    }
				      var data = google.visualization.arrayToDataTable(dataArray);

				      var options = {
				        title: '4I - diagramm',
				        width: 800,
				        height: 600,
				        hAxis: {title: 'Speed (%), max Time 10 s', minValue:0},
				        vAxis: {title: 'Success (%)',minValue:0},
				        bubble: {textStyle: {fontSize: 11}}
				      };

				      var chart = new google.visualization.BubbleChart(document.getElementById('evaluation'));
				      chart.draw(data, options);		
				    }
					$("#evaluation").fadeIn(1600,function(){}).animate({left: '250px'});


			    }
		  });
		
		
	});
	
  },
  handleOpenState: function(){
	console.log("handle state: OPEN");
	
	if("unknown"==participantId){
		$("#message").html("Hey, we will start soon. Please sign up!").hide().show().animate({left: '250px'});
		$("#login").show().animate({left: '250px'});
	}else{
		$("#message").html("The particpant "+participantName+" is already registered?").animate({left: '250px'});
	}
  },
  handleRunningState: function(){
	  console.log("handle state: RUNNING");
	  
	  $("#overlay").hide();
	  
	  //fill letter select
	  for(var i=0;i<Scta.letters.length;i++){
		  $("#letter").append('<option value="' + Scta.letters[i] + '">' + Scta.letters[i] + '</option>');
	  }
	  //get document
	  Scta.getDocument(0,Scta.handleDocument);
	  //switch ui
	  
	  //hide wait
	  $("#message").fadeOut(1600,function(){
		//show document
		  $("#document").fadeIn(1600,function(){});
	  });
	  
	  
  },
  handleProbingState: function(){
	  console.log("handle state: PROBING");
	  //get questions
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/questions/"+participantId,
		    type: "GET",
		    success: function(someQuestions, status, xhr){
		    	console.log("questions received: ");
		    	console.log(questions);
		    	questions=someQuestions;
		    	$("#questiontext").html(someQuestions[questionCounter].question);
		    	$("#button-answer-one").html(someQuestions[questionCounter].answer1).show();
		    	$("#button-answer-two").html(someQuestions[questionCounter].answer2).show();
		    	$("#button-answer-three").html(someQuestions[questionCounter].answer3).show();
		    	$("#overlay").show();
		    	//start measuring the time
		    	startTime=new Date();
		    	
		    }
	  }); 
  },
  saveResponseData: function(anAnswer){
	  endTime=new Date();
	  var millis=endTime-startTime;
	  console.log("millis: "+millis);
	  
	  var isCorrect=(anAnswer==questions[questionCounter].correctAnswer);
	  console.log("answer:"+anAnswer+" - correct: "+questions[questionCounter].correctAnswer+" - is correct? "+isCorrect+" - millis: "+millis);
	  //shot the data over to the server
	  var r=new Object();
	  r.id="??";
	  r.participantId=participantId;
	  r.correct=isCorrect;
	  r.milliseconds=millis;
	  r.questionType=questions[questionCounter].questionType;
	  
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/responsedata",
		    type: "POST",
		    data: JSON.stringify(r),
		    dataType: "json",
		    contentType: "application/json",
		    accept:"application/json",
		    success: function(data, status, xhr){
		        //on to the next question or end freezeprobe
		    	questionCounter++;
		    	//reset stopwatch
		    	if(questionCounter<questions.length){
		    		$("#questionbox").hide();
		    	  	$("#questiontext").html(questions[questionCounter].question);
		    		$("#button-answer-one").html(questions[questionCounter].answer1);
		    		$("#button-answer-two").html(questions[questionCounter].answer2);
		    		$("#button-answer-three").html(questions[questionCounter].answer3);
		    		$("#questionbox").fadeIn(1600,function(){});
		    		startTime=new Date();
		    	}else{
		    		//reset
		      	  	$("#questiontext").html("Freeze probe finished. Please wait!");
		    		$("#button-answer-one").hide();
		    		$("#button-answer-two").hide();
		    		$("#button-answer-three").hide();
		    		questionCounter=0;
		    		Scta.finishedFreezeProbe();
		    	}
		    }
		}); 	  	
	  
	  
  },
  finishedFreezeProbe: function(){
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/participants/"+participantId,
		    type: "GET",
		    success: function(p, status, xhr){
		        console.log(p);
		        p.status="FINISHED_FREEZE_PROBE";
		        
		        Scta.updateParticipant(p);
		        
		        
		    }
	  }); 

  },
  
  updateParticipant: function(aParticipant){
	  console.log("update participant with id "+aParticipant.id);
		jQuery.ajax ({
		    url: "/webapp/rest/admin/participants",
		    type: "PUT",
		    data: JSON.stringify(aParticipant),
		    dataType: "json",
		    contentType: "application/json",
		    accept:"application/json",
		    success: function(data, status, xhr){
		        console.log("participant updated:"+data);
		    }
		}); 	
  },
  getDocument:function(id,callback){
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/documents/"+id,
		    type: "GET",
		    success: function(result, status, xhr){
		        console.log(result);
		        callback(result);
		    }
	  }); 
	  
  },
  handleDocument:function(document){
	$("#letters").html(document.text);  
  },
  sendCountData: function(aParticipantId, aLetter, aQuantity){
	  
	  console.log("particpantId:"+aParticipantId+" letter:"+aLetter+" quantity:"+aQuantity);
	  var countData=new Object();
	  countData.participantId=aParticipantId;
	  countData.letter=aLetter;
	  countData.quantity=aQuantity;
	  
	  jQuery.ajax ({
		    url: "/webapp/rest/admin/countdata",
		    type: "POST",
		    data: JSON.stringify(countData),
		    dataType: "json",
		    contentType: "application/json",
		    accept:"application/json",
		    success: function(data, status, xhr){
		    	$("#message").fadeOut(1600,function(){
		    		$("#message").html("Your count data was saved successfully.").show();	
		    	});
		    	$("#message").fadeOut(3000,function(){});
		    	
		    }
		}); 	  	
	  
	  
  }
  
  };
  return Scta;
}

if(typeof(Scta)==='undefined'){
  window.Scta=defineScta(); //register this stuff onload
}
})(window);

