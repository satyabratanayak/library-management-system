package com.lms.service;

import com.lms.model.Book;
import com.lms.model.LendingRecord;
import com.lms.model.Patron;
import com.lms.repository.BookRepository;
import com.lms.repository.LendingRepository;
import com.lms.repository.PatronRepository;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class LendingService {
    private static final Logger LOG = Logger.getLogger(LendingService.class.getName());

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final LendingRepository lendingRepository;

    public LendingService(BookRepository bookRepository,
                          PatronRepository patronRepository,
                          LendingRepository lendingRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.lendingRepository = lendingRepository;
    }

    /** Checks out one copy of a book to a patron, creates a lending record, and updates availability. */
    public LendingRecord checkout(String isbn, String memberId) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new NoSuchElementException("Book not found: " + isbn));
        Patron patron = patronRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Patron not found: " + memberId));

        if (!book.isAvailable()) throw new IllegalStateException("No available copies for: " + isbn);

        book.checkoutOne();
        bookRepository.save(book);

        LocalDate issue = LocalDate.now();
        LocalDate due = issue.plusDays(14);
        LendingRecord record = new LendingRecord(isbn, memberId, issue, due);
        lendingRepository.save(record);
        patron.addBorrowRecord(record);

        LOG.info(() -> "Checked out ISBN=" + isbn + " to member=" + memberId + " recordId=" + record.getRecordId());
        return record;
    }

    /** Returns a borrowed book, marks record returned, and updates availability. */
    public void returnBook(String recordId) {
        LendingRecord record = lendingRepository.findById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Lending record not found: " + recordId));

        if (record.getReturnDate() != null) {
            LOG.info(() -> "Record already returned: " + recordId);
            return; // idempotent
        }

        record.markReturned(LocalDate.now());
        lendingRepository.save(record);

        Book book = bookRepository.findById(record.getIsbn())
                .orElseThrow(() -> new NoSuchElementException("Book not found for record: " + record.getIsbn()));
        book.returnOne();
        bookRepository.save(book);

        LOG.info(() -> "Returned recordId=" + recordId + " isbn=" + record.getIsbn());
    }
}
