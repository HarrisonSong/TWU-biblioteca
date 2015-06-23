package com.twu.biblioteca;

import com.twu.biblioteca.model.BookTest;
import com.twu.biblioteca.model.CustomerTest;
import com.twu.biblioteca.model.LibraryTest;
import com.twu.biblioteca.model.MovieTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by qiyuesong on 22/6/15.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({LibraryItemTest.class, BookTest.class, MovieTest.class, CustomerTest.class, LibraryItemTest.class,
        LibraryOperationParserTest.class, LibraryItemOperationTest.class, LibraryTest.class, MainMenuTest.class,
        CredentialTest.class, AuthenticatorTest.class, SystemMessagerTest.class, LibraryManagementSystemTest.class})
public class BibliotecaAppTestsSuite {
}
