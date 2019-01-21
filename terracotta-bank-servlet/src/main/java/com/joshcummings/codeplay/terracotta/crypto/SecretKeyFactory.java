/*
 * Copyright 2015-2019 Josh Cummings
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
 */
package com.joshcummings.codeplay.terracotta.crypto;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class SecretKeyFactory {
	private final javax.crypto.SecretKeyFactory delegate;

	public SecretKeyFactory(javax.crypto.SecretKeyFactory delegate) {
		this.delegate = delegate;
	}

	public static SecretKeyFactory getInstance(String algorithm) {
		try {
			return new SecretKeyFactory(javax.crypto.SecretKeyFactory.getInstance(algorithm));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public SecretKey generatePublic(KeySpec keySpec) {
		try {
			return this.delegate.generateSecret(keySpec);
		} catch (InvalidKeySpecException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public <T extends KeySpec> T getKeySpec(SecretKey key, Class<T> clazz) {
		try {
			return (T) this.delegate.getKeySpec(key, clazz);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}