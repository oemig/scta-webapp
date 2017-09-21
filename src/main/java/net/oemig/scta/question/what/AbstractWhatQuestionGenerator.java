package net.oemig.scta.question.what;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.oemig.scta.CountData;
import net.oemig.scta.Question;
import net.oemig.scta.data.CountDataUtil;
import net.oemig.scta.question.IQuestionGenerator;

/**
 * The {@link AbstractWhatQuestionGenerator} is the basis for questions that
 * ask what was counted.
 * 
 * @author christoph oemig, info@oemig.net
 */
public abstract class AbstractWhatQuestionGenerator implements
		IQuestionGenerator {

	private final String participantId;
	private final Collection<CountData> countData;
	
	
	/**
	 * Constructor 
	 * @param aUserName
	 * @param aTraceModel
	 */
	public AbstractWhatQuestionGenerator(
			final String aParticipantId,
			final Collection<CountData> someCountData
			) {
		participantId=aParticipantId;
		countData=someCountData;
//		if(locale.equals(Locale.GERMANY)){
//			yes="ja";
//			no="nein";
//			questionFragment1="Wurde der Buchstabe <b>";
//			questionFragment2="</b> gezählt?";
//		}else{
//			yes="yes";
//			no="no";
//			questionFragment1="Was the letter <b>";
//			questionFragment2="</b> counted?";
//		}
	}

	@Override
	public Question generate()  {
		List<String>answers=ImmutableList.of("Yes","No");
		String letter;
		String correct;
		//flip coin to either proceed with counted
		//letters (group or individual) or uncounted letters
		//50:50 chance
		if(1==CountDataUtil.getRandomNumber(1)){
			//proceed with counted
			
			CountData cd=CountDataUtil.random(getCountData());
			if(null!=cd){
				letter=cd.getLetter();
				correct="Yes";
			} else {
				//no letters yet counted
				letter=CountDataUtil.getRandomLetter();
				correct="No";
			}

		}else{
			//proceed with uncounted
			letter=CountDataUtil.random(CountDataUtil.getUncountedLetters(countData));
			correct="No";	
		}
		
		
		//no shuffle necessary
		return new Question("Was the letter <b>"+letter+"</b> counted? ("+getType()+")",
				answers.get(0),
				answers.get(1),
				"",
				correct,
				getType());
	}
	
	public abstract String getType();
	public abstract Collection<CountData> getCountData() ;

	public String getParticipantId() {
		return participantId;
	}

	public Collection<CountData> getOriginalCountData() {
		return countData;
	}

}
