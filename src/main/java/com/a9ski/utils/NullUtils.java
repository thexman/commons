package com.a9ski.utils;

public class NullUtils {
	protected NullUtils() {
		super();
	}

	public static void checkNotNull(final Object o, final String msg) {
		if (o == null) {
			throw new IllegalArgumentException(msg);
		}
	}
}
