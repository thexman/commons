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

/**
 * Implements {@link EquityComparator} based on reference check
 *
 * @author Kiril Arabadzhiyski
 *
 * @param <O>
 *            the object type
 */
public class ReferenceEquityComparator<O> implements EquityComparator<O> {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.a9ski.utils.EquityComparator#equals(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean equals(final O o1, final O o2) {
		return o1 == o2; // NOSONAR this is the purpose of this comparator - to compare references
	}

}
