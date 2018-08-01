package com.vectree.billing.dao;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vectree.billing.domain.Account;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AccountCacheDaoTest {
    private static final int MAX_CAPACITY = 10;
    private static final Logger logger = LoggerFactory.getLogger(AccountCacheDaoTest.class);

    CacheLoader<Integer, Account> loaderAccount = new CacheLoader<Integer, Account>() {
        @Override
        public Account load(Integer key) {
            logger.info("loaded " + key);
            return new Account();
        }
    };
    LoadingCache<Integer, Account> cacheAccountBuilder = CacheBuilder.newBuilder().maximumSize(MAX_CAPACITY).build(loaderAccount);

    @Test
    public void testGetCacheBuilderForAccount() throws ExecutionException {
        assertEquals(0, cacheAccountBuilder.size());
        Account account = cacheAccountBuilder.get(1);
        assertEquals(account, cacheAccountBuilder.get(1));
        assertEquals(1, cacheAccountBuilder.size());
    }

    @Test
    public void testCapacityCacheBuilderForAccount() throws ExecutionException {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            cacheAccountBuilder.get(i);
        }
        assertEquals(MAX_CAPACITY, cacheAccountBuilder.size());

        for (int i = MAX_CAPACITY; i < MAX_CAPACITY * 2; i++) {
            cacheAccountBuilder.get(i);
        }
        assertEquals(MAX_CAPACITY, cacheAccountBuilder.size());
    }
}
