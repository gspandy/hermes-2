package com.hermes.app.util;


/**
 *
 */
public class Sequence {
	private static int key = 1000;
	private static Sequence sequence = null;

	private Sequence() {
	}

	public static synchronized Sequence getSequence() {
		if (sequence == null) {
            sequence = new Sequence();
		}
		return sequence;
	}

	public int getNextSeq() {
		synchronized (sequence) {
			if (key == 9999) {
                key = 1000;
				return key++;
			} else {
				return key++;
			}
		}
	}
}
