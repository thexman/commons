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

import java.util.List;

/**
 * Interface for lazy loading provider
 *
 * @author Kiril Arabadzhiyski
 *
 * @param <E>
 *            type of the provided elements
 */
public interface LazyLoadingProvider<E> {
	/**
	 * Returns the total count of elements
	 *
	 * @return total count of elements
	 */
	public int countItems();

	/**
	 * Loads elements from given page
	 *
	 * @param page
	 *            the page number
	 * @return elements belonging to given page
	 */
	public List<E> loadItems(int page);

}
