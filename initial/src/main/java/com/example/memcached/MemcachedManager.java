package com.example.memcached;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.util.Assert;

import java.util.Collection;

public class MemcachedManager extends AbstractCacheManager {
    private final Collection<Memcached> internalCaches;

    public MemcachedManager(final Collection<Memcached> internalCaches) {
        this.internalCaches = internalCaches;
    }


    @Override
    protected Collection<? extends Cache> loadCaches() {
        Assert.notNull(internalCaches, "A collection caches is required and cannot be empty");
        return internalCaches;
    }
}