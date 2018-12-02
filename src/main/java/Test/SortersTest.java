package Test;

import Comparators.CompByDateOfBirth;
import Comparators.CompByFio;
import Comparators.CompByGender;
import Person.Person;
import RepositorySorters.SorterByDoB;
import RepositorySorters.SorterByFio;
import RepositorySorters.SorterByGender;
import RepositorySorters.isorter;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortersTest {
    Person[] person;

    @BeforeEach
    void before() {
        person = new Person[]{new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f')
        };
    }

    @Test
    void sortByFio() {
        isorter sorter = new SorterByFio();
        Person[] expected = new Person[]{new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f'),
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f')
        };
        sorter.sort(person, new CompByFio());
        Person[] actual = person;
        assertArrayEquals(expected, actual);
    }

    @Test
    void sortByDoB() {
        isorter sorter = new SorterByDoB();
        Person[] expected = new Person[]{
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'),
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'),
                new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
        };
        sorter.sort(person, new CompByDateOfBirth());
        Person[] actual = person;
        assertArrayEquals(expected, actual);
    }

    @Test
    void sortByGender() {
        isorter sorter = new SorterByGender();
        Person[] expected = new Person[]{
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f'),
                new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'),
        };
        sorter.sort(person, new CompByGender());
        Person[] actual = person;
        assertArrayEquals(expected, actual);
    }
}
