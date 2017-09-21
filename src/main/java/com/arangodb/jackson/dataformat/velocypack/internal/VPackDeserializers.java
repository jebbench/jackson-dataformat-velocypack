/*
 * DISCLAIMER
 *
 * Copyright 2017 ArangoDB GmbH, Cologne, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */

package com.arangodb.jackson.dataformat.velocypack.internal;

import java.io.IOException;
import java.text.ParseException;

import com.arangodb.velocypack.internal.util.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author Mark Vollmary
 *
 */
public class VPackDeserializers {

	public static final JsonDeserializer<java.util.Date> UTIL_DATE = new JsonDeserializer<java.util.Date>() {
		@Override
		public java.util.Date deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			try {
				return DateUtil.parse(p.getValueAsString());
			} catch (final ParseException e) {
				throw new IOException(e);
			}
		}
	};

	public static final JsonDeserializer<java.sql.Date> SQL_DATE = new JsonDeserializer<java.sql.Date>() {
		@Override
		public java.sql.Date deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			try {
				return new java.sql.Date(DateUtil.parse(p.getValueAsString()).getTime());
			} catch (final ParseException e) {
				throw new IOException(e);
			}
		}
	};

	public static final JsonDeserializer<java.sql.Timestamp> SQL_TIMESTAMP = new JsonDeserializer<java.sql.Timestamp>() {
		@Override
		public java.sql.Timestamp deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			try {
				return new java.sql.Timestamp(DateUtil.parse(p.getValueAsString()).getTime());
			} catch (final ParseException e) {
				throw new IOException(e);
			}
		}
	};

}
