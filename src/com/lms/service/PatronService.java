package com.lms.service;

import com.lms.model.LendingRecord;
import com.lms.model.Patron;
import com.lms.repository.PatronRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class PatronService {
    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public void addPatron(Patron patron) { patronRepository.save(patron); }

    public void updatePatron(Patron patron) {
        patronRepository.findById(patron.getMemberId()).orElseThrow(() ->
                new NoSuchElementException("Patron not found: " + patron.getMemberId()));
        patronRepository.save(patron);
    }

    public Patron getPatron(String memberId) {
        return patronRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Patron not found: " + memberId));
    }

    public List<LendingRecord> getBorrowHistory(String memberId) {
        return getPatron(memberId).getBorrowHistory();
    }

    public List<Patron> allPatrons() { return patronRepository.findAll(); }
}
