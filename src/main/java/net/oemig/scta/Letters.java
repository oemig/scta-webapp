package net.oemig.scta;

import org.apache.commons.lang3.RandomStringUtils;

public class Letters {
	private static final int GROUP_SIZE=5;
	private static final int LETTER_GROUPS=50; //prior 25 for 2 min
	private static final int GROUPS_PER_ROW=5;
	
	public static final String create(){
		StringBuffer sbuffer = new StringBuffer();
		// in total wie use 25 random letter groups
		for (int i = 1; i < (LETTER_GROUPS+1); i++) {
			
			//add five random letters -->random letter group
			sbuffer.append(RandomStringUtils.random(GROUP_SIZE, true, false));
			//add space after random letter group
			sbuffer.append(" ");
			//add a <br> every five letter columns -- new line
			if (i % GROUPS_PER_ROW == 0) {
				sbuffer.append("<br>");
			}
		}

		return sbuffer.toString().toLowerCase();
	}

}
