package net.oemig.scta.question.who;

import java.util.Collection;

import net.oemig.scta.CountData;
import net.oemig.scta.Question;
import net.oemig.scta.data.CountDataUtil;

public class SelfWhoQuestionGenerator extends
		AbstractWhoQuestionGenerator {

	public SelfWhoQuestionGenerator(final String aParticipantId, Collection<CountData>someCountData) {
		super(aParticipantId,someCountData);
	}

	@Override
	public String getType() {
		return Question.SELF_WHO;
	}

	@Override
	public Collection<CountData> getCountData()  {
		return CountDataUtil.filterMyCountData(getOriginalCountData(), getParticipantId());
	}

}
