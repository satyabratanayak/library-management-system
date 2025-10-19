package com.lms.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class LendingRecord {
    private final String recordId = UUID.randomUUID().toString();
    private final String isbn;
    private final String memberId;
    private final LocalDate issueDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public LendingRecord(String isbn, String memberId, LocalDate issueDate, LocalDate dueDate) {
        this.isbn = Objects.requireNonNull(isbn, "isbn");
        this.memberId = Objects.requireNonNull(memberId, "memberId");
        this.issueDate = Objects.requireNonNull(issueDate, "issueDate");
        this.dueDate = Objects.requireNonNull(dueDate, "dueDate");
    }

    public String getRecordId() { return recordId; }
    public String getIsbn() { return isbn; }
    public String getMemberId() { return memberId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void markReturned(LocalDate date) {
        if (this.returnDate != null) return; // idempotent
        this.returnDate = Objects.requireNonNull(date);
    }

    @Override public String toString() {
        return "LendingRecord{" + "recordId='" + recordId + '\'' + ", isbn='" + isbn + '\'' +
                ", memberId='" + memberId + '\'' + ", issueDate=" + issueDate +
                ", dueDate=" + dueDate + ", returnDate=" + returnDate + '}';
    }
}
