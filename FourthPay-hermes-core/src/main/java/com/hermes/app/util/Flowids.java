package com.hermes.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Flowids {
	private Flowids() {
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat();

	private static final Sequence sequence = Sequence.getSequence();

	public static String createFlowid() {
        sdf.applyPattern("yyMMddHHmmss");
		String timetip = sdf.format(new Date());
		return timetip + String.valueOf(sequence.getNextSeq());
	}
}
