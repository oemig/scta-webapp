package net.oemig.scta.question;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import net.oemig.scta.CountData;
import net.oemig.scta.Question;
import net.oemig.scta.question.what.GroupWhatQuestionGenerator;
import net.oemig.scta.question.what.SelfWhatQuestionGenerator;
import net.oemig.scta.question.who.GroupWhoQuestionGenerator;
import net.oemig.scta.question.who.SelfWhoQuestionGenerator;

/**
 * {@link QuestionFactory} wraps multiple {@link IQuestionGenerator}s that
 * produce questions for freeze probes.
 * @see GroupHowQuestionGenerator
 * @see GroupWhoQuestionGenerator
 * @see IndividualHowQuestionGenerator
 * @see IndividualWhoQuestionGenerator
 * @author oemig
 */
public final class QuestionFactory {
	
	//private constructor
	private QuestionFactory() {
	}

	public static Collection<Question> getMultiple(final String aParticipantId, Collection<CountData> someCountData) {
		List<IQuestionGenerator> generators = Lists.newArrayList();
		generators.add(new GroupWhoQuestionGenerator(aParticipantId, someCountData));
		generators.add(new GroupWhatQuestionGenerator(aParticipantId,someCountData));
		generators.add(new SelfWhoQuestionGenerator(aParticipantId, someCountData));
		generators.add(new SelfWhatQuestionGenerator(aParticipantId,someCountData ));
		//
		//Coordination questions
//		generators.add(new CoordinationWhoQuestionGenerator(anEnvironment.getCoordinationSupportSystem(), someUserNames, aResourceBundle, anEnvironment.getTraceModel().getCurrentRun().getCountData()));

		// create random order for questions
		Collections.shuffle(generators);

		List<Question> questions = Lists.newArrayList();
		for (IQuestionGenerator g : generators) {
			questions.add(g.generate());
		}

		return questions;
	}

}
