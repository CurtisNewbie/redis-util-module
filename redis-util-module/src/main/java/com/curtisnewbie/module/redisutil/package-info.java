/**
 * <h1>
 * Redis-Util-Module
 * </h1>
 * <p>
 * Simple module that uses Redis to provide functionalities like caching or distributed locking. It internally wraps
 * frameworks like Redisson. It's an internal module that can only work for very limited scenarios, it's used only to
 * make things a bit easier and consistent.
 * </p>
 * <h2>
 * To use this module:
 * </h2>
 * <p>
 * 1.Configure redis-server connection by setting properties or yaml files whichever you like.
 * <p>
 * For example:
 * <pre>
 * {@code
 * spring.redis.database=0
 * spring.redis.host=localhost
 * spring.redis.port=6379
 * spring.redis.password=youPw
 * spring.redis.timeout=60000
 * }
 * </pre>
 * <p>
 * 2. Inject {@link com.curtisnewbie.module.redisutil.RedisController} and use it
 * </p>
 * <br>
 * In case of serialization/deserialization problem (e.g., exceptions related to class casting, change codec as
 * follows:
 * <pre>
 * {@code
 * singleServerConfig:
 *  idleConnectionTimeout: 10000
 *  connectTimeout: 10000
 *  timeout: 3000
 *  retryAttempts: 3
 *  retryInterval: 1500
 *  subscriptionsPerConnection: 5
 *  address: "redis://127.0.0.1:6379"
 *  subscriptionConnectionMinimumIdleSize: 1
 *  subscriptionConnectionPoolSize: 10
 *  connectionMinimumIdleSize: 5
 *  connectionPoolSize: 10
 *  database: 0
 *  dnsMonitoringInterval: 5000
 *  threads: 0
 *  nettyThreads: 0
 *  codec: !<org.redisson.codec.JsonJacksonCodec> {}
 * }
 * </pre>
 * </p>
 *
 * @author yongjie.zhuang
 */
package com.curtisnewbie.module.redisutil;