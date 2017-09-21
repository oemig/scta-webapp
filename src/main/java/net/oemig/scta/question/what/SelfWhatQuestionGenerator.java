package net.oemig.scta.question.what;

import java.util.Collection;

import net.oemig.scta.CountData;
import net.oemig.scta.Question;
import net.oemig.scta.data.CountDataUtil;


public class SelfWhatQuestionGenerator extends
		AbstractWhatQuestionGenerator {

	public SelfWhatQuestionGenerator(final String aParticipantId,
			Collection<CountData> someCountData) {
		
		super(aParticipantId,someCountData);
	}

	@Override
	public String getType() {
		return Question.SELF_WHAT;
	}

	@Override
	public Collection<CountData> getCountData(){
		return CountDataUtil.filterMyCountData(getOriginalCountData(), getParticipantId());
	}

}
