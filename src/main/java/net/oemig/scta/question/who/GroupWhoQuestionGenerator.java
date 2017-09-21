package net.oemig.scta.question.who;

import java.util.Collection;

import net.oemig.scta.CountData;
import net.oemig.scta.Question;
import net.oemig.scta.data.CountDataUtil;

/**
 * GroupWhoQuestion Get a count result from the model. Counting person must not
 * be the current user.
 * 
 * "Who counted"+countresult.letter+"?"
 * 
 */
public class GroupWhoQuestionGenerator extends AbstractWhoQuestionGenerator {

	public GroupWhoQuestionGenerator(final String aParticipantId, Collection<CountData>someCountData) {
		super(aParticipantId, someCountData);
	}

	@Override
	public String getType() {
		return Question.GROUP_WHO;
	}

	@Override
	public Collection<CountData> getCountData(){
		//here you use the data of others.. not your own!!
		return CountDataUtil.filterOthersCountData(getOriginalCountData(), getParticipantId());
	}
}
