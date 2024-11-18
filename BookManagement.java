

package njava;

import java.util.Scanner;
import java.util.Scanner;

class Book {
    private String name;
    private String author;

    // constructor
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    // getters  to take val
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    // method to display book information
    public String displayInfo() {
        return "    Book Name: " + name + ", Author: " + author;
    }
}

class Category extends Book {
    private String category;

    // Constructor
    public Category(String name, String author, String category) {
        super(name, author);
        this.category = category;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Overriding the displayInfo method
    @Override
    public String displayInfo() {
        return super.displayInfo() + ", Category: " + category;
    }
}

public class BookManagement {
    private static Category[] bookList = new Category[100];
    private static int count = 0;
    private static Scanner scanner = new Scanner(System.in);

    // method to add a book
    public static void addBook(String name, String author, String category) {
        if (findBookByName(name) != null) {
            System.out.println("    Error: Book already exists.");
            return;
        }

        if (count < bookList.length) {
            bookList[count++] = new Category(name, author, category);
            System.out.println("    Book added successfully.");
        } else {
            System.out.println("    Book list is full.");
        }
    }

    // method to search a book by name
    public static void searchBook(String name) {
        Category book = findBookByName(name);
        if (book != null) {
            System.out.println("    Book found: " + book.displayInfo());
        } else {
            System.out.println("    Book not found.");
        }
    }

    // method to find a book by name
    private static Category findBookByName(String name) {
        for (int i = 0; i < count; i++) {
            if (bookList[i].getName().equalsIgnoreCase(name)) {
                return bookList[i];
            }
        }
        return null;
    }

    // method to list books by category
    public static void listBooksByCategory(String category) {
        boolean found = false;
        System.out.println("    Books in category '" + category + "':");
        for (int i = 0; i < count; i++) {
            if (bookList[i].getCategory().equalsIgnoreCase(category)) {
                System.out.println(bookList[i].displayInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("    No books found in this category.");
        }
    }

    // main method with menu
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n|------------ Menu ------------|\n");
            System.out.println("|   1. Add New Book            |");
            System.out.println("|   2. Search Book by Name     |");
            System.out.println("|   3. List Books by Category  |");
            System.out.println("|   4. Exit                    |\n");
            System.out.print("   > Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("\n");

            switch (choice) {
                case 1:
                    System.out.print("    Enter book name: ");
                    String name = scanner.nextLine();
                    System.out.print("    Enter author name: ");
                    String author = scanner.nextLine();
                    System.out.print("    Enter category: ");
                    String category = scanner.nextLine();
                    addBook(name, author, category);
                    break;

                case 2:
                    System.out.print("    Enter book name to search: ");
                    String searchName = scanner.nextLine();
                    searchBook(searchName);
                    break;

                case 3:
                    System.out.print("    Enter category to list books: ");
                    String listCategory = scanner.nextLine();
                    listBooksByCategory(listCategory);
                    break;

                case 4:
                    System.out.println("    Exiting the program.");
                    break;

                default:
                    System.out.println("    Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
