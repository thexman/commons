package com.a9ski.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testCompareTo() {
		assertTrue(StringUtils.compareTo("a", "b") < 0);
		assertTrue(StringUtils.compareTo("a", "a") == 0);
		assertTrue(StringUtils.compareTo(null, "b") < 0);
		assertTrue(StringUtils.compareTo("a", null) > 0);
		assertTrue(StringUtils.compareTo(null, null) == 0);
	}

	@Test
	public void testCompareToIgnoreCase() {
		assertTrue(StringUtils.compareToIgnoreCase("A", "b") < 0);
		assertTrue(StringUtils.compareToIgnoreCase("A", "a") == 0);
		assertTrue(StringUtils.compareToIgnoreCase(null, "b") < 0);
		assertTrue(StringUtils.compareToIgnoreCase("a", null) > 0);
		assertTrue(StringUtils.compareToIgnoreCase(null, null) == 0);
	}

	@Test
	public void testEqualsTrim() {
		assertTrue(StringUtils.equalsTrim("   a ", "a"));
		assertTrue(StringUtils.equalsTrim("a", "   a   "));
		assertFalse(StringUtils.equalsTrim("A", "   a   "));
		assertFalse(StringUtils.equalsTrim(null, "b"));
		assertFalse(StringUtils.equalsTrim("a", null));
		assertTrue(StringUtils.equalsTrim(null, null));
	}

	@Test
	public void testNotEqualsTrim() {
		assertFalse(StringUtils.notEqualsTrim("   a ", "a"));
		assertFalse(StringUtils.notEqualsTrim("a", "   a   "));
		assertTrue(StringUtils.notEqualsTrim("A", "   a   "));
		assertTrue(StringUtils.notEqualsTrim(null, "b"));
		assertTrue(StringUtils.notEqualsTrim("a", null));
		assertFalse(StringUtils.notEqualsTrim(null, null));
	}

	@Test
	public void testEqualsIgnoreCaseTrim() {
		assertTrue(StringUtils.equalsIgnoreCaseTrim("   a ", "a"));
		assertTrue(StringUtils.equalsIgnoreCaseTrim("a", "   a   "));
		assertTrue(StringUtils.equalsIgnoreCaseTrim("A", "   a   "));
		assertFalse(StringUtils.equalsIgnoreCaseTrim(null, "b"));
		assertFalse(StringUtils.equalsIgnoreCaseTrim("a", null));
		assertTrue(StringUtils.equalsIgnoreCaseTrim(null, null));
	}

	@Test
	public void testNotEqualsIgnoreCaseTrim() {
		assertFalse(StringUtils.notEqualsIgnoreCaseTrim("   a ", "a"));
		assertFalse(StringUtils.notEqualsIgnoreCaseTrim("a", "   a   "));
		assertFalse(StringUtils.notEqualsIgnoreCaseTrim("A", "   a   "));
		assertTrue(StringUtils.notEqualsIgnoreCaseTrim(null, "b"));
		assertTrue(StringUtils.notEqualsIgnoreCaseTrim("a", null));
		assertFalse(StringUtils.notEqualsIgnoreCaseTrim(null, null));
	}

	@Test
	public void testNotEquals() {
		assertFalse(StringUtils.notEquals("a", "a"));
		assertFalse(StringUtils.notEquals("a", "a"));
		assertTrue(StringUtils.notEquals("A", "a"));
		assertTrue(StringUtils.notEquals(null, "b"));
		assertTrue(StringUtils.notEquals("a", null));
		assertFalse(StringUtils.notEquals(null, null));
	}

	@Test
	public void testNotContains() {
		assertTrue(StringUtils.notContains("abcd", "bcq"));
		assertFalse(StringUtils.notContains("abcd", "bc"));
	}

	@Test
	public void testContainsTrim() {
		assertFalse(StringUtils.containsTrim("    abcd   ", "   bcq  "));
		assertTrue(StringUtils.containsTrim("   abcd  ", "   bc   "));
	}

	@Test
	public void testContainsTrimIgnoreCase() {
		assertFalse(StringUtils.containsTrimIgnoreCase("    abcd   ", "   Bcq  "));
		assertTrue(StringUtils.containsTrimIgnoreCase("   abcd  ", "   bC   "));
	}

	@Test
	public void testNotContainsTrim() {
		assertTrue(StringUtils.notContainsTrim("    abcd   ", "   bcq  "));
		assertFalse(StringUtils.notContainsTrim("   abcd  ", "   bc   "));
	}

	@Test
	public void testNotContainsTrimIgnoreCase() {
		assertTrue(StringUtils.notContainsTrimIgnoreCase("    abcd   ", "   Bcq  "));
		assertFalse(StringUtils.notContainsTrimIgnoreCase("   abcd  ", "   bC   "));
	}

	@Test
	public void testHashCodeString() {
		assertEquals(0, StringUtils.hashCode(null));
		assertEquals("abc".hashCode(), StringUtils.hashCode("abc"));
	}

	@Test
	public void testJoinNonBlanksStringIterableOfString() {
		final List<String> l = Arrays.asList(null, "valar", null, "morgulis", null, "", "", "= all", null, "men must", null, null, "die!");
		assertEquals("valar morgulis = all men must die!", StringUtils.joinNonBlanks(" ", l));
		assertNull(StringUtils.joinNonBlanks(" ", (Iterable<String>) null));
	}

	@Test
	public void testJoinNonBlanksStringStringArray() {
		assertEquals("valar morgulis = all men must die!", StringUtils.joinNonBlanks(" ", null, "valar", null, "morgulis", null, "", "", "= all", null, "men must", null, null, "die!"));
		assertNull(StringUtils.joinNonBlanks(" ", (String[]) null));
	}

	@Test
	public void testJoinWithFilter() {
		assertEquals("valar morgulis = all men must die!", StringUtils.joinWithFilter(" ", null, "valar", "morgulis", "= all", "men must", "die!"));
	}

	@Test
	public void testReplaceWithSingleSpace() {
		assertEquals(" hello world ", StringUtils.replaceWithSingleSpace("    hello    world    "));
		assertEquals(" ", StringUtils.replaceWithSingleSpace("    "));
		assertEquals("", StringUtils.replaceWithSingleSpace(""));
		assertNull(StringUtils.replaceWithSingleSpace(null));
	}

	@Test
	public void testNotEqualsAny() {
		assertTrue(StringUtils.notEqualsAny("MORGULIS", null, "valar", null, "morgulis", null, "", "", "= all", null, "men must", null, null, "die!"));
		assertFalse(StringUtils.notEqualsAny("morgulis", null, "valar", null, "morgulis", null, "", "", "= all", null, "men must", null, null, "die!"));
	}

	@Test
	public void testNotEqualsIgnoreCaseAny() {
		assertTrue(StringUtils.notEqualsIgnoreCaseAny("game-of-thrones", null, "valar", null, "morgulis", null, "", "", "= all", null, "men must", null, null, "die!"));
		assertFalse(StringUtils.notEqualsIgnoreCaseAny("MORGULIS", null, "valar", null, "morgulis", null, "", "", "= all", null, "men must", null, null, "die!"));
	}

}
