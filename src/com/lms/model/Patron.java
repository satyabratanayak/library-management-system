package com.lms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patron {
    private final String memberId;
    private String name;
    private String email;
    private final List<LendingRecord> borrowHistory = new ArrayList<>();

    public Patron(String memberId, String name, String email) {
        this.memberId = Objects.requireNonNull(memberId, "memberId");
        this.name = Objects.requireNonNull(name, "name");
        this.email = Objects.requireNonNull(email, "email");
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<LendingRecord> getBorrowHistory() { return borrowHistory; }

    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public void setEmail(String email) { this.email = Objects.requireNonNull(email); }

    public void addBorrowRecord(LendingRecord record) {
        borrowHistory.add(Objects.requireNonNull(record));
    }

    @Override public String toString() {
        return "Patron{" + "memberId='" + memberId + '\'' + ", name='" + name + '\'' +
                ", email='" + email + '\'' + ", historyCount=" + borrowHistory.size() + '}';
    }
}
