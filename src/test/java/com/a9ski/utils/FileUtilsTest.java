package com.a9ski.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

/*
 * #%L
 * Commons utilities
 * %%
 * Copyright (C) 2017 Kiril Arabadzhiyski
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
public class FileUtilsTest {

	@Test
	public void testMkdirs() throws Exception {
		final File sub1 = new File(FileUtils.getTempDirectory(), "sub1");
		final File sub2 = new File(sub1, "sub2");
		FileUtils.deleteDirectory(sub1);
		assertFalse(sub2.exists());
		final String s = sub2.getCanonicalPath();
		assertTrue(FileUtils.mkdirs(s));
		assertTrue(sub2.exists());
		FileUtils.deleteDirectory(sub1);
	}

	@Test
	public void testCreateTempFileFromResource() throws Exception {
		final File f = FileUtils.createTempFileFromResource(FileUtilsTest.class, "test.txt");
		try {
			assertEquals("Hello world!", FileUtils.readFileToString(f, "UTF-8"));
		} finally {
			f.delete();
		}

		try {
			FileUtils.createTempFileFromResource(null, "nonexistingfile");
			fail("Expected to throw FileNotFoundException");
		} catch (final FileNotFoundException ex) {
			// expected
		}
	}

	@Test
	public void testCreateFileFromResource() throws Exception {
		final File f = File.createTempFile("temp", ".txt");
		try {
			FileUtils.createFileFromResource(FileUtilsTest.class, "test.txt", f);
			assertEquals("Hello world!", FileUtils.readFileToString(f, "UTF-8"));

			try {
				FileUtils.createFileFromResource(null, "nonexistingfile", f);
				fail("Expected to throw FileNotFoundException");
			} catch (final FileNotFoundException ex) {
				// expected
			}
		} finally {
			f.delete();
		}
	}

	@Test
	public void testGetFileName() {
		assertEquals("test", FileUtils.getFileName("/a/b/c/d.dir/test.txt"));
		assertEquals("test", FileUtils.getFileName(new File("/a/b/c/d.dir/test.txt")));
		assertEquals("test", FileUtils.getFileName("/a/b/c/d.dir/test"));
		assertEquals("test", FileUtils.getFileName(new File("/a/b/c/d.dir/test")));
		assertEquals("", FileUtils.getFileName("/a/b/c/d.dir/"));
		assertEquals("d", FileUtils.getFileName(new File("/a/b/c/d.dir/")));
		assertEquals("", FileUtils.getFileName(new File("/a/b/c/d.dir/.ssh")));
		assertEquals("", FileUtils.getFileName(new File(".ssh")));
		assertEquals("..", FileUtils.getFileName(new File("..")));
		assertEquals(".", FileUtils.getFileName(new File(".")));
		assertEquals("", FileUtils.getFileName(new File("")));
		assertEquals("", FileUtils.getFileName(""));
	}

	@Test
	public void testGetFileExtensionFile() {
		assertEquals(".txt", FileUtils.getFileExtension("/a/b/c/d.dir/test.txt"));
		assertEquals(".txt", FileUtils.getFileExtension(new File("/a/b/c/d.dir/test.txt")));
		assertEquals("", FileUtils.getFileExtension(new File("/a/b/c/d.dir/test")));
		assertEquals("", FileUtils.getFileExtension("/a/b/c/d.dir/"));
		assertEquals(".dir", FileUtils.getFileExtension(new File("/a/b/c/d.dir/")));
		assertEquals(".ssh", FileUtils.getFileExtension(new File("/a/b/c/d.dir/.ssh")));
		assertEquals(".ssh", FileUtils.getFileExtension(new File(".ssh")));
		assertEquals("", FileUtils.getFileExtension(new File("..")));
		assertEquals("", FileUtils.getFileExtension(new File(".")));
		assertEquals("", FileUtils.getFileExtension(new File("")));
		assertEquals("", FileUtils.getFileExtension(""));
	}

	@Test
	public void testCreateTempFile() throws Exception {
		final String content = "Hello world";
		final File f = FileUtils.createTempFile(content.getBytes("UTF-8"), "hello", ".txt");
		try {
			assertTrue(f.isFile());
			assertEquals(content, FileUtils.readFileToString(f, "UTF-8"));
		} finally {
			f.delete();
		}
	}

}
