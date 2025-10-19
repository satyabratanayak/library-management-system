package com.lms.repository;

import com.lms.model.Patron;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PatronRepository implements Repository<Patron, String> {
    private final Map<String, Patron> store = new ConcurrentHashMap<>();

    @Override public Optional<Patron> findById(String memberId) { return Optional.ofNullable(store.get(memberId)); }
    @Override public void save(Patron patron) { store.put(patron.getMemberId(), patron); }
    @Override public void deleteById(String memberId) { store.remove(memberId); }
    @Override public List<Patron> findAll() { return new ArrayList<>(store.values()); }
}
