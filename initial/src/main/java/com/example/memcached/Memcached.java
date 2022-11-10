package com.example.memcached;

import net.spy.memcached.LocatorType;
import net.spy.memcached.WmClient;
import net.spy.memcached.WmConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.IOException;
import java.util.concurrent.Callable;

public class Memcached implements Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(Memcached.class);

    private final String name;

    private final WmClient cache;

    private final int expiration;

    public Memcached(String name, String mcRouterHosts, int expiration) throws IOException {
        this.name = name;
        this.expiration = expiration;
        cache = new WmClient(
                new WmConnectionFactory(
                        50,
                        8,
                        1*1024*1024,
                        true,
                        30000,
                        LocatorType.WEIGHT_ROUND_ROBIN
                ),
                5000,
                mcRouterHosts);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(final Object key) {
        Object value = null;
        try {
            value = cache.get(key.toString());
        } catch (final Exception e) {
            LOGGER.warn(e.getMessage());
        }
        if (value == null) {
            LOGGER.debug("cache miss for key: " + key);
            return null;
        }
        LOGGER.debug("cache hit for key: " + key);
        return new SimpleValueWrapper(value);
    }


    @Override
    public void put(final Object key, final Object value) {
        if (value != null) {
            cache.set(key.toString(), expiration, value);
            LOGGER.debug("cache put for key: " + key);
        }
    }


    @Override
    public void evict(final Object key) {
        this.cache.delete(key.toString());
        LOGGER.debug("cache delete for key: " + key);
    }

    @Override
    public void clear() {
        cache.flush();
        LOGGER.debug("cache clear completed");
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }
}
