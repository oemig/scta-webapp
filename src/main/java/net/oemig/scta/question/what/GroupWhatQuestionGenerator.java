package net.oemig.scta.question.what;

import java.util.Collection;

import net.oemig.scta.CountData;
import net.oemig.scta.Question;
import net.oemig.scta.data.CountDataUtil;


public class GroupWhatQuestionGenerator extends AbstractWhatQuestionGenerator {

	public GroupWhatQuestionGenerator(final String aParticipantId, Collection<CountData> someCountData) {
		super(aParticipantId,someCountData);
	}

	@Override
	public String getType() {
		return Question.GROUP_WHAT;
	}

	@Override
	public Collection<CountData> getCountData() {
		return CountDataUtil.filterOthersCountData(getOriginalCountData(), getParticipantId());
	}

}
