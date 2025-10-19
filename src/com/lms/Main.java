package com.lms;

import com.lms.model.Book;
import com.lms.model.Patron;
import com.lms.repository.BookRepository;
import com.lms.repository.LendingRepository;
import com.lms.repository.PatronRepository;
import com.lms.service.InventoryService;
import com.lms.service.LendingService;
import com.lms.service.PatronService;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Repositories (in-memory)
        BookRepository bookRepo = new BookRepository();
        PatronRepository patronRepo = new PatronRepository();
        LendingRepository lendingRepo = new LendingRepository();

        // Services
        InventoryService inventory = new InventoryService(bookRepo);
        PatronService patrons = new PatronService(patronRepo);
        LendingService lending = new LendingService(bookRepo, patronRepo, lendingRepo);

        // Seed data
        inventory.addBook(new Book("ISBN-001", "Clean Code", "Robert C. Martin", 2008, 3));
        inventory.addBook(new Book("ISBN-002", "Effective Java", "Joshua Bloch", 2018, 2));
        patrons.addPatron(new Patron("P1", "Alice", "alice@example.com"));
        patrons.addPatron(new Patron("P2", "Bob", "bob@example.com"));
        LOG.info(() -> "Search by author 'martin': " + patrons.allPatrons());


        // Search
        var booksByMartin = inventory.searchByAuthor("martin");
        LOG.info(() -> "Search by author 'martin': " + booksByMartin);

        // Checkout
        var record = lending.checkout("ISBN-001", "P1");
        LOG.info(() -> "Checkout record: " + record);

        // Return
        lending.returnBook(record.getRecordId());

        // Show inventory state
        inventory.allBooks().forEach(book -> LOG.info("Book: " + book));

        // Patron
        patrons.updatePatron(new Patron("P1", "Alice", "newalice@example.com"));
        LOG.info(() -> "Search by author 'martin': " + patrons.allPatrons());
        patrons.getBorrowHistory("P1").forEach(r -> LOG.info("History: " + r));
    }
}
