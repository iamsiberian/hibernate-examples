package com.lineate.api.core.domain.examples.typedef;

import java.util.Arrays;
import java.util.List;

public class TypeDefEntityUtils {
    public static User createUser(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    public static List<String> createAuthors(String ... authors) {
        return Arrays.asList(authors);
    }

    public static Book createBook(String name, List<String> authors, User user) {
        Book book = new Book();
        book.setName(name);
        book.setAuthors(authors);
        book.setUser(user);
        return book;
    }
}
