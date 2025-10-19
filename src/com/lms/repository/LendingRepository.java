package com.lms.repository;

import com.lms.model.LendingRecord;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LendingRepository implements Repository<LendingRecord, String> {
    private final Map<String, LendingRecord> store = new ConcurrentHashMap<>();

    @Override public Optional<LendingRecord> findById(String id) { return Optional.ofNullable(store.get(id)); }
    @Override public void save(LendingRecord record) { store.put(record.getRecordId(), record); }
    @Override public void deleteById(String id) { store.remove(id); }
    @Override public List<LendingRecord> findAll() { return new ArrayList<>(store.values()); }
}
