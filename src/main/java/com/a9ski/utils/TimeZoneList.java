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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * List of time zones available in Windows
 *
 * @author Kiril Arabadzhiyski
 *
 */
public class TimeZoneList {

	private static final List<TimeZoneMapping> ZONEMAPPINGS = new ArrayList<>();

	private static final TimeZoneMapping GMT_MAPPING = new TimeZoneMapping("GMT Standard Time", "Europe/London", "(GMT) Dublin, Edinburgh, Lisbon, London");

	static {
		ZONEMAPPINGS.add(new TimeZoneMapping("Afghanistan Standard Time", "Asia/Kabul", "(GMT +04:30) Kabul"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Alaskan Standard Time", "America/Anchorage", "(GMT -09:00) Alaska"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Arab Standard Time", "Asia/Riyadh", "(GMT +03:00) Kuwait, Riyadh"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Arabian Standard Time", "Asia/Dubai", "(GMT +04:00) Abu Dhabi, Muscat"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Arabic Standard Time", "Asia/Baghdad", "(GMT +03:00) Baghdad"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Argentina Standard Time", "America/Buenos_Aires", "(GMT -03:00) Buenos Aires"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Atlantic Standard Time", "America/Halifax", "(GMT -04:00) Atlantic Time (Canada)"));
		ZONEMAPPINGS.add(new TimeZoneMapping("AUS Central Standard Time", "Australia/Darwin", "(GMT +09:30) Darwin"));
		ZONEMAPPINGS.add(new TimeZoneMapping("AUS Eastern Standard Time", "Australia/Sydney", "(GMT +10:00) Canberra, Melbourne, Sydney"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Azerbaijan Standard Time", "Asia/Baku", "(GMT +04:00) Baku"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Azores Standard Time", "Atlantic/Azores", "(GMT -01:00) Azores"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Bangladesh Standard Time", "Asia/Dhaka", "(GMT +06:00) Dhaka"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Canada Central Standard Time", "America/Regina", "(GMT -06:00) Saskatchewan"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Cape Verde Standard Time", "Atlantic/Cape_Verde", "(GMT -01:00) Cape Verde Is."));
		ZONEMAPPINGS.add(new TimeZoneMapping("Caucasus Standard Time", "Asia/Yerevan", "(GMT +04:00) Yerevan"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Cen. Australia Standard Time", "Australia/Adelaide", "(GMT +09:30) Adelaide"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central America Standard Time", "America/Guatemala", "(GMT -06:00) Central America"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central Asia Standard Time", "Asia/Almaty", "(GMT +06:00) Astana"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central Brazilian Standard Time", "America/Cuiaba", "(GMT -04:00) Cuiaba"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central Europe Standard Time", "Europe/Budapest", "(GMT +01:00) Belgrade, Bratislava, Budapest, Ljubljana, Prague"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central European Standard Time", "Europe/Warsaw", "(GMT +01:00) Sarajevo, Skopje, Warsaw, Zagreb"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central Pacific Standard Time", "Pacific/Guadalcanal", "(GMT +11:00) Solomon Is., New Caledonia"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central Standard Time (Mexico)", "America/Mexico_City", "(GMT -06:00) Guadalajara, Mexico City, Monterrey"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Central Standard Time", "America/Chicago", "(GMT -06:00) Central Time (US & Canada)"));
		ZONEMAPPINGS.add(new TimeZoneMapping("China Standard Time", "Asia/Shanghai", "(GMT +08:00) Beijing, Chongqing, Hong Kong, Urumqi"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Dateline Standard Time", "Etc/GMT+12", "(GMT -12:00) International Date Line West"));
		ZONEMAPPINGS.add(new TimeZoneMapping("E. Africa Standard Time", "Africa/Nairobi", "(GMT +03:00) Nairobi"));
		ZONEMAPPINGS.add(new TimeZoneMapping("E. Australia Standard Time", "Australia/Brisbane", "(GMT +10:00) Brisbane"));
		ZONEMAPPINGS.add(new TimeZoneMapping("E. Europe Standard Time", "Europe/Minsk", "(GMT +02:00) Minsk"));
		ZONEMAPPINGS.add(new TimeZoneMapping("E. South America Standard Time", "America/Sao_Paulo", "(GMT -03:00) Brasilia"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Eastern Standard Time", "America/New_York", "(GMT -05:00) Eastern Time (US & Canada)"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Egypt Standard Time", "Africa/Cairo", "(GMT +02:00) Cairo"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Ekaterinburg Standard Time", "Asia/Yekaterinburg", "(GMT +05:00) Ekaterinburg"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Fiji Standard Time", "Pacific/Fiji", "(GMT +12:00) Fiji, Marshall Is."));
		ZONEMAPPINGS.add(new TimeZoneMapping("FLE Standard Time", "Europe/Sofia", "(GMT +02:00) Helsinki, Kyiv, Riga, Sofia, Tallinn, Vilnius"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Georgian Standard Time", "Asia/Tbilisi", "(GMT +04:00) Tbilisi"));
		ZONEMAPPINGS.add(GMT_MAPPING);
		ZONEMAPPINGS.add(new TimeZoneMapping("Greenland Standard Time", "America/Godthab", "(GMT -03:00) Greenland"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Greenwich Standard Time", "Atlantic/Reykjavik", "(GMT) Monrovia, Reykjavik"));
		ZONEMAPPINGS.add(new TimeZoneMapping("GTB Standard Time", "Europe/Istanbul", "(GMT +02:00) Athens, Bucharest, Istanbul"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Hawaiian Standard Time", "Pacific/Honolulu", "(GMT -10:00) Hawaii"));
		ZONEMAPPINGS.add(new TimeZoneMapping("India Standard Time", "Asia/Calcutta", "(GMT +05:30) Chennai, Kolkata, Mumbai, New Delhi"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Iran Standard Time", "Asia/Tehran", "(GMT +03:30) Tehran"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Israel Standard Time", "Asia/Jerusalem", "(GMT +02:00) Jerusalem"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Jordan Standard Time", "Asia/Amman", "(GMT +02:00) Amman"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Kamchatka Standard Time", "Asia/Kamchatka", "(GMT +12:00) Petropavlovsk-Kamchatsky - Old"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Korea Standard Time", "Asia/Seoul", "(GMT +09:00) Seoul"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Magadan Standard Time", "Asia/Magadan", "(GMT +11:00) Magadan"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Mauritius Standard Time", "Indian/Mauritius", "(GMT +04:00) Port Louis"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Mid-Atlantic Standard Time", "Etc/GMT+2", "(GMT -02:00) Mid-Atlantic"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Middle East Standard Time", "Asia/Beirut", "(GMT +02:00) Beirut"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Montevideo Standard Time", "America/Montevideo", "(GMT -03:00) Montevideo"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Morocco Standard Time", "Africa/Casablanca", "(GMT) Casablanca"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Mountain Standard Time (Mexico)", "America/Chihuahua", "(GMT -07:00) Chihuahua, La Paz, Mazatlan"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Mountain Standard Time", "America/Denver", "(GMT -07:00) Mountain Time (US & Canada)"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Myanmar Standard Time", "Asia/Rangoon", "(GMT +06:30) Yangon (Rangoon)"));
		ZONEMAPPINGS.add(new TimeZoneMapping("N. Central Asia Standard Time", "Asia/Novosibirsk", "(GMT +06:00) Novosibirsk"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Namibia Standard Time", "Africa/Windhoek", "(GMT +02:00) Windhoek"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Nepal Standard Time", "Asia/Katmandu", "(GMT +05:45) Kathmandu"));
		ZONEMAPPINGS.add(new TimeZoneMapping("New Zealand Standard Time", "Pacific/Auckland", "(GMT +12:00) Auckland, Wellington"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Newfoundland Standard Time", "America/St_Johns", "(GMT -03:30) Newfoundland"));
		ZONEMAPPINGS.add(new TimeZoneMapping("North Asia East Standard Time", "Asia/Irkutsk", "(GMT +08:00) Irkutsk"));
		ZONEMAPPINGS.add(new TimeZoneMapping("North Asia Standard Time", "Asia/Krasnoyarsk", "(GMT +07:00) Krasnoyarsk"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Pacific SA Standard Time", "America/Santiago", "(GMT -04:00) Santiago"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Pacific Standard Time (Mexico)", "America/Tijuana", "(GMT -08:00) Baja California"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Pacific Standard Time", "America/Los_Angeles", "(GMT -08:00) Pacific Time (US & Canada)"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Pakistan Standard Time", "Asia/Karachi", "(GMT +05:00) Islamabad, Karachi"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Paraguay Standard Time", "America/Asuncion", "(GMT -04:00) Asuncion"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Romance Standard Time", "Europe/Paris", "(GMT +01:00) Brussels, Copenhagen, Madrid, Paris"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Russian Standard Time", "Europe/Moscow", "(GMT +03:00) Moscow, St. Petersburg, Volgograd"));
		ZONEMAPPINGS.add(new TimeZoneMapping("SA Eastern Standard Time", "America/Cayenne", "(GMT -03:00) Cayenne, Fortaleza"));
		ZONEMAPPINGS.add(new TimeZoneMapping("SA Pacific Standard Time", "America/Bogota", "(GMT -05:00) Bogota, Lima, Quito"));
		ZONEMAPPINGS.add(new TimeZoneMapping("SA Western Standard Time", "America/La_Paz", "(GMT -04:00) Georgetown, La Paz, Manaus, San Juan"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Samoa Standard Time", "Pacific/Apia", "(GMT -11:00) Samoa"));
		ZONEMAPPINGS.add(new TimeZoneMapping("SE Asia Standard Time", "Asia/Bangkok", "(GMT +07:00) Bangkok, Hanoi, Jakarta"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Singapore Standard Time", "Asia/Singapore", "(GMT +08:00) Kuala Lumpur, Singapore"));
		ZONEMAPPINGS.add(new TimeZoneMapping("South Africa Standard Time", "Africa/Johannesburg", "(GMT +02:00) Harare, Pretoria"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Sri Lanka Standard Time", "Asia/Colombo", "(GMT +05:30) Sri Jayawardenepura"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Syria Standard Time", "Asia/Damascus", "(GMT +02:00) Damascus"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Taipei Standard Time", "Asia/Taipei", "(GMT +08:00) Taipei"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Tasmania Standard Time", "Australia/Hobart", "(GMT +10:00) Hobart"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Tokyo Standard Time", "Asia/Tokyo", "(GMT +09:00) Osaka, Sapporo, Tokyo"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Tonga Standard Time", "Pacific/Tongatapu", "(GMT +13:00) Nuku'alofa"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Ulaanbaatar Standard Time", "Asia/Ulaanbaatar", "(GMT +08:00) Ulaanbaatar"));
		ZONEMAPPINGS.add(new TimeZoneMapping("US Eastern Standard Time", "America/Indianapolis", "(GMT -05:00) Indiana (East)"));
		ZONEMAPPINGS.add(new TimeZoneMapping("US Mountain Standard Time", "America/Phoenix", "(GMT -07:00) Arizona"));
		ZONEMAPPINGS.add(new TimeZoneMapping("GMT ", "Etc/GMT", "(GMT) Coordinated Universal Time"));
		ZONEMAPPINGS.add(new TimeZoneMapping("GMT +12", "Etc/GMT-12", "(GMT +12:00) Coordinated Universal Time+12"));
		ZONEMAPPINGS.add(new TimeZoneMapping("GMT -11", "Etc/GMT+11", "(GMT -11:00) Coordinated Universal Time-11"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Venezuela Standard Time", "America/Caracas", "(GMT -04:30) Caracas"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Vladivostok Standard Time", "Asia/Vladivostok", "(GMT +10:00) Vladivostok"));
		ZONEMAPPINGS.add(new TimeZoneMapping("W. Australia Standard Time", "Australia/Perth", "(GMT +08:00) Perth"));
		ZONEMAPPINGS.add(new TimeZoneMapping("W. Central Africa Standard Time", "Africa/Lagos", "(GMT +01:00) West Central Africa"));
		ZONEMAPPINGS.add(new TimeZoneMapping("W. Europe Standard Time", "Europe/Berlin", "(GMT +01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna"));
		ZONEMAPPINGS.add(new TimeZoneMapping("West Asia Standard Time", "Asia/Tashkent", "(GMT +05:00) Tashkent"));
		ZONEMAPPINGS.add(new TimeZoneMapping("West Pacific Standard Time", "Pacific/Port_Moresby", "(GMT +10:00) Guam, Port Moresby"));
		ZONEMAPPINGS.add(new TimeZoneMapping("Yakutsk Standard Time", "Asia/Yakutsk", "(GMT +09:00) Yakutsk"));
	}

	private static class LazyHolder {
		private static final TimeZoneList INSTANCE = new TimeZoneList();
	}

	/**
	 * Retrieves the singleton instance
	 *
	 * @return the singleton instance
	 */
	public static TimeZoneList getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final List<NamedTimeZone> timeZones;
	private final NamedTimeZone gmt;

	private TimeZoneList() {
		final Set<String> availableIdsSet = new HashSet<>(Arrays.asList(TimeZone.getAvailableIDs()));

		//@formatter:off
		timeZones = Collections.unmodifiableList(ExtCollectionUtils.sort(
			ZONEMAPPINGS.stream()
				.filter(zoneMapping -> availableIdsSet.contains(zoneMapping.getId()))
				.map(zoneMapping -> zoneMapping.createNamedTimeZone())
				.collect(Collectors.toList()),
			NamedTimeZoneComparator.INSTANCE));

		gmt = timeZones.stream()
			.filter(ntz -> ntz.getTimeZone().getID().equals(GMT_MAPPING.getId()))
			.findFirst()
			.orElseGet(() -> GMT_MAPPING.createNamedTimeZone());
		//@formatter:on

	}

	/**
	 * Returns unmodifiable list of all named time zones
	 *
	 * @return unmodifiable list of all named time zones
	 */
	public List<NamedTimeZone> getNamedTimeZones() {
		return ExtCollectionUtils.unmodifiableList(timeZones);
	}

	/**
	 * Gets the java time zones
	 *
	 * @return the java time zones
	 */
	public List<TimeZone> getTimeZones() {
		return ExtCollectionUtils.unmodifiableList(getNamedTimeZones().stream().map(ntz -> ntz.getTimeZone()).collect(Collectors.toList()));
	}

	/**
	 * Gets the java time zone ids
	 *
	 * @return the java time zone ids
	 */
	public List<String> getTimeZoneIds() {
		return ExtCollectionUtils.unmodifiableList(getNamedTimeZones().stream().map(ntz -> ntz.getTimeZone().getID()).collect(Collectors.toList()));
	}

	/**
	 * Returns the default named time zone
	 *
	 * @return the default named time zone
	 */
	public NamedTimeZone getDefaultTimeZone() {
		return getNamedTimeZone(TimeZone.getDefault());
	}

	/**
	 * Gets named time zone for given java time zone
	 *
	 * @param tz
	 *            java time zone
	 * @return named time zone corresponding for java time zone
	 */
	public NamedTimeZone getNamedTimeZone(final TimeZone tz) {
		if (tz != null) {
			return getNamedTimeZone(tz.getID());
		} else {
			return gmt;
		}
	}

	/**
	 * Gets the named time zone for GMT
	 *
	 * @return named time zone for GMT
	 */
	public NamedTimeZone getGmtTimeZone() {
		return gmt;
	}

	/**
	 * Finds a named time zone by given predicate
	 *
	 * @param predicate
	 *            the predicate
	 * @return the first named time zone matching the predicate
	 */
	public Optional<NamedTimeZone> findNamedTimeZone(final Predicate<NamedTimeZone> predicate) {
		return getNamedTimeZones().stream().filter(predicate).findFirst();
	}

	/**
	 * Gets the first named time zone mathing given predicate
	 *
	 * @param predicate
	 *            the predicate
	 * @return the first named time zone mathing given predicate or null, if there is no match
	 */
	public NamedTimeZone getNamedTimeZone(final Predicate<NamedTimeZone> predicate) {
		return findNamedTimeZone(predicate).orElse(null);
	}

	/**
	 * Gets a named time zone by java time zone id
	 *
	 * @param id
	 *            java time zone id
	 * @return named time zone matching java time zone id
	 */
	public NamedTimeZone getNamedTimeZone(final String id) {
		// 1. Find by direct zone ID matching
		final NamedTimeZone zone1 = getNamedTimeZone(t -> StringUtils.equals(t.getTimeZone().getID(), id));
		if (zone1 != null) {
			return zone1;
		}

		// 2. Find by zone with same rules (strict version)
		final TimeZone tz = TimeZone.getTimeZone(id);
		final NamedTimeZone zone2 = getNamedTimeZone(t -> t.getTimeZone().hasSameRules(tz));
		if (zone2 != null) {
			return zone2;
		}

		// 3. Find by zone with same rules (more relax version)
		final NamedTimeZone zone3 = getNamedTimeZone(t -> hasSameRules(t.getTimeZone(), tz));
		if (zone3 != null) {
			return zone3;
		}

		// 4. fallback to GMT
		return gmt;
	}

	/**
	 * Checks if two java time zones have the same rules
	 *
	 * @param tz1
	 *            java time zone 1
	 * @param tz2
	 *            java time zone 2
	 * @return true if two java time zones have the same rules
	 */
	public static boolean hasSameRules(final TimeZone tz1, final TimeZone tz2) {
		return (tz1.getRawOffset() == tz2.getRawOffset() && tz1.useDaylightTime() == tz2.useDaylightTime());
	}

	/**
	 * Comparator for NamedTimeZones. Compares regarding the offset from GMT
	 *
	 * @author Kiril Arabadzhiyski
	 *
	 */
	public static final class NamedTimeZoneComparator implements Comparator<NamedTimeZone> {
		public static final NamedTimeZoneComparator INSTANCE = new NamedTimeZoneComparator();

		@Override
		public int compare(final NamedTimeZone a, final NamedTimeZone b) {
			final int diff = a.getTimeZone().getRawOffset() - b.getTimeZone().getRawOffset();
			if (diff < 0) {
				return -1;
			} else if (diff > 0) {
				return 1;
			} else {
				return a.getDisplayName().compareTo(b.getDisplayName());
			}
		}

	}

	/**
	 * Class representing a named time zone. Named time zone are corresponding to time zones configurations in Windows
	 *
	 * @author Kiril Arabadzhiyski
	 *
	 */
	public static final class NamedTimeZone implements Comparable<NamedTimeZone> {
		private final TimeZone timeZone;
		private final String displayName;
		private final String standardDisplayName;

		public NamedTimeZone(final TimeZone timeZone, final String displayName, final String standardDisplayName) {
			this.timeZone = timeZone;
			this.displayName = displayName;
			this.standardDisplayName = standardDisplayName;
		}

		/**
		 * Java Time zone
		 *
		 * @return java time zone object
		 */
		public TimeZone getTimeZone() {
			return timeZone;
		}

		/**
		 * Gets the windows display name. Examples:
		 * <p>
		 * "(GMT +02:00) Helsinki, Kyiv, Riga, Sofia, Tallinn, Vilnius" <br>
		 * "(GMT -06:00) Central Time (US &amp; Canada)"
		 * </p>
		 *
		 * @return Windows display name
		 */
		public String getDisplayName() {
			return displayName;
		}

		/**
		 * Gets Windows standard name. Examples:
		 * <p>
		 * "FLE Standard Time" <br>
		 * "Central Standard Time"
		 * </p>
		 *
		 * @return Windows standard name
		 */
		public String getStandardDisplayName() {
			return standardDisplayName;
		}

		@Override
		public int compareTo(final NamedTimeZone o) {
			return NamedTimeZoneComparator.INSTANCE.compare(this, o);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
			result = prime * result + ((standardDisplayName == null) ? 0 : standardDisplayName.hashCode());
			result = prime * result + ((timeZone == null && timeZone.getID() != null) ? 0 : timeZone.getID().hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof NamedTimeZone)) {
				return false;
			}
			final NamedTimeZone other = (NamedTimeZone) obj;
			if (displayName == null) {
				if (other.displayName != null) {
					return false;
				}
			} else if (!displayName.equals(other.displayName)) {
				return false;
			}
			if (standardDisplayName == null) {
				if (other.standardDisplayName != null)
					return false;
			} else if (!standardDisplayName.equals(other.standardDisplayName)) {
				return false;
			}
			if (timeZone == null) {
				if (other.timeZone != null) {
					return false;
				}
			} else if (!StringUtils.equals(timeZone.getID(), other.timeZone.getID())) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return getDisplayName();
		}

	}

	private static final class TimeZoneMapping {
		private final String windowsStandardName;
		private final String olsonName;
		private final String windowsDisplayName;

		public TimeZoneMapping(final String windowsStandardName, final String olsonName, final String windowsDisplayName) {
			this.windowsStandardName = windowsStandardName;
			this.olsonName = olsonName;
			this.windowsDisplayName = windowsDisplayName;
		}

		public String getWindowsStandardName() {
			return windowsStandardName;
		}

		public String getOlsonName() {
			return olsonName;
		}

		public String getWindowsDisplayName() {
			return windowsDisplayName;
		}

		public String getId() {
			return getOlsonName();
		}

		public NamedTimeZone createNamedTimeZone() {
			final TimeZone timeZone = TimeZone.getTimeZone(getId());
			// windowsStandardName = "FLE Standard Time",
			// oslonName = "Europe/Sofia",
			// windowsDispalyName - "(GMT +02:00) Helsinki, Kyiv, Riga, Sofia, Tallinn, Vilnius"
			return new NamedTimeZone(timeZone, getWindowsDisplayName(), getWindowsStandardName());
		}
	}
}
