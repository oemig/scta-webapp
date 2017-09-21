package net.oemig.scta;

import org.apache.commons.lang.RandomStringUtils;

public class Letters {
	public static final String create(){
		StringBuffer sbuffer = new StringBuffer();
		for (int i = 1; i < 26; i++) {

			sbuffer.append(RandomStringUtils.random(5, true, false));
			sbuffer.append(" ");
			if (i % 5 == 0) {
				sbuffer.append("<br>");
			}
		}

		return sbuffer.toString().toLowerCase();
	}

}
