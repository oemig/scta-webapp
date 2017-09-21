 package net.oemig.scta.question.who;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.oemig.scta.CountData;
import net.oemig.scta.Question;
import net.oemig.scta.Store;
import net.oemig.scta.data.CountDataUtil;

public abstract class AbstractWhoQuestionGenerator implements
		net.oemig.scta.question.IQuestionGenerator {

	private final String participantId;
	private final Collection<CountData> countData;
	
	public AbstractWhoQuestionGenerator(final String aParticipantId, Collection<CountData>someCountData) {
		participantId=aParticipantId;
		countData=someCountData;
	}

	@Override
	public Question generate()  {

		//initialize answers with the user's name and nobody
		List<String> answers = Lists.newArrayList();
		answers.add(Store.getInstance().getParticipantById(participantId).getName());
		answers.add("Nobody");

		//then try to add a third answer from count data
		//or any other random letter that nobody has counted
		//yet
		String letter;
		String correct;
		
			// get the "others" data
		CountData cd = CountDataUtil.random(getCountData());
		if(null!=cd){
			letter = cd.getLetter();
			correct = Store.getInstance().getParticipantById(cd.getParticipantId()).getName();
			answers.add(Store.getInstance().getParticipantById(cd.getParticipantId()).getName());
		} else {
			// otherwise select uncounted letters.. since nobody counted these
			List<String> uncountedLetters = CountDataUtil
					.getUncountedLetters(countData);
			Collections.shuffle(uncountedLetters);
			letter = uncountedLetters.get(0);
			correct = "Nobody";
			answers.add("");
		}

		Collections.shuffle(answers);

		return new Question("Who counted the letter <b>" + letter + "</b>? ("+getType()+")", answers.get(0), answers.get(1),
				answers.get(2), correct, getType());
	}

	public abstract String getType();

	public abstract Collection<CountData> getCountData();

	public String getParticipantId() {
		return participantId;
	}

	public Collection<CountData> getOriginalCountData() {
		return countData;
	}

}
