package com.example.cache;

import com.example.memcached.Memcached;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class MultiCacheManagerConfig extends CachingConfigurerSupport {

    @Value("${memcached.addresses}")
    private String memcachedAddresses;

    @Value("${memcached.expiration.sec}")
    private int expirationSec;

    public String[] cacheNames = {
        "products", "books"
    };

    /**
     * We are using CachingConfigurerSupport to define out main caching
     * provider. In our case it's Caffeine cache. This will be the default cache provider
     * for our application. If we don't provide explicit cache manager, Spring Boot
     * will pick this as default cache provider.
     * @return CacheManager: the default cache manager.
     */
    @Primary
    @Bean // good to have but not strictly necessary
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Arrays.asList("books")); // optional; if set, @Cacheable can only use what's provided.
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

    @Bean
    public Caffeine < Object, Object > caffeineCacheBuilder() {
        return Caffeine.newBuilder()
            .initialCapacity(100)
            .maximumSize(500)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .recordStats();
    }

    @Bean
    public CacheManager memcachedCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        try {
            cacheManager.setCaches(internalCaches());
            return cacheManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<Memcached> internalCaches() throws IOException {
        final Collection<Memcached> caches = new ArrayList<>();
        caches.add(new Memcached("books", memcachedAddresses, expirationSec));
        return caches;
    }

    /**
     * Second cache provider which can work as fallback or will be used when invoked explicitly in the
     * code base.
     */
    @Bean
    CacheManager alternateCacheManager() {
        return new ConcurrentMapCacheManager(cacheNames);
    }

}
