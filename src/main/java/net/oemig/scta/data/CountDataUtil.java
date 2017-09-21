package net.oemig.scta.data;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.oemig.scta.CountData;

/**
 * Helps to filter and select count data.
 * @author oemig
 */
public final class CountDataUtil {
	public static final String NOBODY = "Nobody";
	public static final String NONE = "None";

	/**
	 * private constructor
	 */
	private CountDataUtil() {
	}

	/**
	 * Filters count data that was produced by other
	 * than the specified user
	 * @param countData - letter count data
	 * @param userName - name of the user we do not seek count data of
	 * @return 
	 */
	public static Collection<CountData> filterOthersCountData(
			Collection<CountData> countData, 
			final String aParticipantId) {
		return Collections2.filter(countData, new Predicate<CountData>() {
			@Override
			public boolean apply(CountData cd){
				//here is the difference
				return !cd.getParticipantId().equals(aParticipantId);
			}
			
		});
	}

	/**
	 * Filters count data produced by specified user
	 * @param countData
	 * @param userName
	 * @return
	 */
	public static Collection<CountData> filterMyCountData(
			Collection<CountData> countData,
			final String aParticipantId) {
		return Collections2.filter(countData, new Predicate<CountData>() {
			@Override
			public boolean apply(CountData cd){
				return cd.getParticipantId().equals(aParticipantId);
			}
			
		});
	}

	/**
	 * From the given list of {@link CountData} a random {@link CountData}
	 * instance is selected and returned.
	 * @param countData
	 * @return
	 * @throws QuestionException
	 */
	public static CountData random(Collection<CountData> countData){

		Random randomGenerator = new Random();
		if (countData.size() < 1) {
			//FIXME use exception
			return null;
		} else {
			return Lists.newArrayList(countData).get(randomGenerator.nextInt(countData.size()));
		}
	}
	
	public static String random(List<String> letters){
		return letters.get(getRandomNumber(letters.size()));
	}

	/**
	 * Returns a {@link Set} of letters that were counted and are thus
	 * part of the {@link CountData}
	 * @param countData - counted letters by users
	 * @return
	 */
	public static List<String> getUncountedLetters(Collection<CountData> countDatas) {

		Set<String> counted = getCountedLetters(countDatas);
		List<String> uncounted = Lists.newArrayList();

		for (String letter : alphabet()) {
			if (!counted.contains(letter)) {
				uncounted.add(letter);
			}
		}

		return uncounted;
	}
	
	public static String[] alphabet(){
		return new String[] { "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
	}
	
	/**
	 * Returns a random letter from the alphabet.
	 * @return
	 */
	public static String getRandomLetter(){
		return alphabet()[getRandomNumber(alphabet().length)];
	}
	
	/**
	 * Returns a {@link Set} of counted letters of the {@link CountData}s.
	 * @param countDatas
	 * @return
	 */
	public static Set<String> getCountedLetters(Collection<CountData>countDatas){
		Set<String> counted = Sets.newHashSet();
		for (CountData c : countDatas) {
			counted.add(c.getLetter());
		}
		return counted;
	}

	public static int getRandomNumber(int max) {
		Random random = new Random();
		return random.nextInt(max);
	}
}
