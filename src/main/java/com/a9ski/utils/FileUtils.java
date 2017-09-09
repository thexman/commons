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
package com.a9ski.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * General file manipulation utilities.
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * Creates the directory named by this abstract pathname, including any necessary but nonexistent parent directories. Note that if this operation fails it may have succeeded in creating some of the necessary parent directories.
	 *
	 * @param absolutePath
	 *            absolute path to be created
	 * @return <code>true</code> if and only if the directory was created, along with all necessary parent directories; <code>false</code> otherwise
	 *
	 * @throws SecurityException
	 *             If a security manager exists and its <code>{@link
	 *          java.lang.SecurityManager#checkRead(java.lang.String)}</code> method does not permit verification of the existence of the named directory and all necessary parent directories; or if the <code>{@link
	 *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code> method does not permit the named directory and all necessary parent directories to be created
	 */
	public static boolean mkdirs(final String absolutePath) {
		final File d = new File(absolutePath);
		return d.mkdirs();
	}

	/**
	 * Creates temporary file with contents copied from the given resource.
	 *
	 * @param clazz
	 *            the class.
	 * @param resourceName
	 *            the resource name
	 * @return File handler to a temporary file
	 * @throws IOException
	 *             In case an error occurs.
	 */
	public static File createTempFileFromResource(final Class<?> clazz, final String resourceName) throws IOException {
		final InputStream is = getResourceAsStream(clazz, resourceName);

		if (is == null) {
			throw new FileNotFoundException(String.format("Resource '%s' not found", resourceName));
		}

		final File tempFile = File.createTempFile("tmp", "tmp");
		copyInputStreamToFile(is, tempFile);
		return tempFile;
	}

	/**
	 * Creates file with contents copied from the given resource.
	 *
	 * @param clazz
	 *            the class.
	 * @param resourceName
	 *            the resource name
	 * @param file
	 *            the destination file
	 * @return File handler to a destination file
	 * @throws IOException
	 *             In case an error occurs.
	 */
	public static File createFileFromResource(final Class<?> clazz, final String resourceName, final File file) throws IOException {
		final InputStream is = getResourceAsStream(clazz, resourceName);

		if (is == null) {
			throw new FileNotFoundException(String.format("Resource '%s' not found", resourceName));
		}

		copyInputStreamToFile(is, file);
		return file;
	}

	/**
	 * Gets resource as stream
	 *
	 * @param clazz
	 *            class whose resource to take. If null uses thread context classloader
	 * @param resourceName
	 *            the resource name
	 * @return InputStream to the resource
	 */
	public static InputStream getResourceAsStream(final Class<?> clazz, final String resourceName) {
		InputStream is = null;
		if (clazz != null) {
			is = clazz.getResourceAsStream(resourceName);
		}
		if (is == null) {
			final ClassLoader loader = Thread.currentThread().getContextClassLoader();
			if (loader != null) {
				is = loader.getResourceAsStream(resourceName);
			}
		}

		return is;
	}

	/**
	 * Returns the filename without the extension part.
	 *
	 * @param file
	 *            the file
	 * @return the file name without extension
	 */
	public static String getFileName(final File file) {
		return getFileName(file.getName());
	}

	/**
	 * Returns the filename without the extension part.
	 *
	 * @param filePath
	 *            the file path
	 * @return the file name without extension
	 */
	public static String getFileName(final String filePath) {
		return filePath.substring(getLastSeparator(filePath) + 1).replaceFirst("[.][^.\\/]+$", "");
	}

	/**
	 * Returns the file extension of the file or an empty string if an error occurred. <br>
	 * The file extension includes the <b>dot</b>
	 *
	 * @param file
	 *            the file
	 * @return the file extension
	 */
	public static String getFileExtension(final File file) {
		return getFileExtension(file.getAbsolutePath());
	}

	/**
	 * Return the file extension of the file name or an empty string if the passed name is blank. <br>
	 * The file extension includes the <b>dot</b>
	 *
	 * @param filePath
	 *            the file path
	 * @return the file extension
	 */
	public static String getFileExtension(final String filePath) {
		if (org.apache.commons.lang3.StringUtils.isBlank(filePath)) {
			return "";
		}
		final int dot = filePath.lastIndexOf(".");
		final int lastSeparator = getLastSeparator(filePath);
		if (dot > lastSeparator && dot < filePath.length() - 1) {
			return filePath.substring(dot);
		} else {
			return "";
		}
	}

	/**
	 * Gets the position of the last file separator ('/' or '\' or {@link File#separatorChar})
	 *
	 * @param filePath
	 *            the file path
	 * @return the position of the last file separator or -1
	 */
	public static int getLastSeparator(final String filePath) {
		final int lastSeparator = Math.max(Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\')), filePath.lastIndexOf(File.separatorChar));
		return lastSeparator;
	}

	/**
	 * Creates a temp file from the passed content, prefix and suffix
	 *
	 * @param content
	 *            the content of the file
	 * @param prefix
	 *            the temp file prefix
	 * @param suffix
	 *            the temp file suffix
	 * @return temp file with <tt>byteArray</tt> content
	 * @throws IOException
	 *             if an error occurs
	 */
	public static File createTempFile(final byte[] content, final String prefix, final String suffix) throws IOException {
		final File tempFile = File.createTempFile(prefix, suffix, null);
		writeByteArrayToFile(tempFile, content);
		return tempFile;
	}
}
