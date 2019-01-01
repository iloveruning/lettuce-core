/*
 * Copyright 2011-2019 the original author or authors.
 *
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
 */
package com.lambdaworks.redis.output;

/**
 * Streaming API for multiple Keys. You can implement this interface in order to receive a call to {@code onKey} on every key.
 * Key uniqueness is not guaranteed.
 *
 * @param <K> Key type.
 * @author Mark Paluch
 * @since 3.0
 */
@FunctionalInterface
public interface KeyStreamingChannel<K> {
    /**
     * Called on every incoming key.
     *
     * @param key the key
     */
    void onKey(K key);
}
