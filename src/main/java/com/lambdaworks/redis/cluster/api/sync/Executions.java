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
package com.lambdaworks.redis.cluster.api.sync;

import java.util.*;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.lambdaworks.redis.cluster.models.partitions.RedisClusterNode;

/**
 * Result holder for a command that was executed synchronously on multiple nodes. This API is subject to incompatible changes in
 * a future release. The API is exempt from any compatibility guarantees made by lettuce. The current state implies nothing
 * about the quality or performance of the API in question, only the fact that it is not "API-frozen."
 *
 * The NodeSelection command API and its result types are a base for discussions.
 *
 *
 * @author Mark Paluch
 * @since 4.0
 */
public interface Executions<T> extends Iterable<T> {

    /**
     *
     * @return map between {@link RedisClusterNode} and the {@link CompletionStage}
     */
    Map<RedisClusterNode, T> asMap();

    /**
     *
     * @return collection of nodes on which the command was executed.
     */
    Collection<RedisClusterNode> nodes();

    /**
     *
     * @param redisClusterNode the node
     * @return the completion stage for this node
     */
    T get(RedisClusterNode redisClusterNode);

    /**
     *
     * @return iterator over the {@link CompletionStage}s
     */
    @Override
    default Iterator<T> iterator() {
        return asMap().values().iterator();
    }

    /**
     *
     * @return a {@code Spliterator} over the elements in this collection
     */
    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(iterator(), nodes().size(), 0);
    }

    /**
     * @return a sequential {@code Stream} over the elements in this collection
     */
    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
