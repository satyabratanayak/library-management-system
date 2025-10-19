package com.lms.model;

import java.util.Objects;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, String author, int publicationYear, int totalCopies) {
        this.isbn = Objects.requireNonNull(isbn, "isbn");
        this.title = Objects.requireNonNull(title, "title");
        this.author = Objects.requireNonNull(author, "author");
        this.publicationYear = publicationYear;
        if (totalCopies < 0) throw new IllegalArgumentException("totalCopies cannot be negative");
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    // getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    // setters / mutators
    public void setTitle(String title) { this.title = Objects.requireNonNull(title); }
    public void setAuthor(String author) { this.author = Objects.requireNonNull(author); }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    /** Admin update to copy counts (keeps available within [0, total]). */
    public void setTotalCopies(int newTotal) {
        if (newTotal < 0) throw new IllegalArgumentException("totalCopies cannot be negative");
        int delta = newTotal - this.totalCopies;
        this.totalCopies = newTotal;
        this.availableCopies = Math.max(0, Math.min(this.availableCopies + delta, this.totalCopies));
    }

    /** Decrements available copies when checking out one copy. */
    public void checkoutOne() {
        if (availableCopies <= 0) throw new IllegalStateException("No available copies");
        availableCopies--;
    }

    /** Increments available copies when returning one copy. */
    public void returnOne() {
        if (availableCopies >= totalCopies) throw new IllegalStateException("All copies already available");
        availableCopies++;
    }

    @Override public String toString() {
        return "Book{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' +
                ", author='" + author + '\'' + ", year=" + publicationYear +
                ", total=" + totalCopies + ", available=" + availableCopies + '}';
    }
}
