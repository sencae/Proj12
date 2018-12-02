package Test;


import Comparators.CompByFio;
import Person.Person;
import Repository.Repository;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    Repository repository;

    @BeforeEach
    void makeRepository() {
        repository = new Repository();
        repository.add(new Person(1, LocalDate.now(), "aaaaaaa", 'm'));
        repository.add(new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'));
        repository.add(new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'));
        repository.add(new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'));
        repository.add(new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f'));
    }

    @Test
    void removeLastPerson() {
        Person[] expected = new Person[]{new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f')};
        repository.removePerson(4);
        assertArrayEquals(expected, repository.getMasPersons());
    }

    @Test
    void removeFirstPerson() {
        Person[] expected = new Person[]{
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f')};
        repository.removePerson(0);
        assertArrayEquals(expected, repository.getMasPersons());
    }

    @Test
    void removePersonInCenter() {
        Person[] expected = new Person[]{new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f')};
        repository.removePerson(2);
        assertArrayEquals(expected, repository.getMasPersons());
    }

    @Test
    void getAgeOfPerson() {
        Integer expected = 26;
        Integer actual = repository.getAgeOfPerson(1);
        assertEquals(expected, actual);
    }

    @Test
    void getAgeOfPerson_ifNow() {
        Integer expected = 0;
        Integer actual = repository.getAgeOfPerson(0);
        assertEquals(expected, actual);
    }

    @Test
    void searchByFio() {
        Person[] expected = new Person[]{new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f')
        };
        Person[] actual = repository.searchByFio("aaa").getMasPersons();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByFio_ifNoInput() {
        Person[] expected = new Person[]{new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f')
        };
        Person[] actual = repository.searchByFio("").getMasPersons();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByGender_ifMale() {
        Person[] expected = new Person[]{new Person(1, LocalDate.now(), "aaaaaaa", 'm'),
                new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm')
        };
        Person[] actual = repository.searchByGender('m').getMasPersons();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByGender_ifFemale() {
        Person[] expected = new Person[]{
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f')
        };
        Person[] actual = repository.searchByGender('f').getMasPersons();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByDateOfBirth() {
        Person[] expected = new Person[]{
                new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'f'),
                new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'f')
        };
        Person[] actual = repository.searchByDateOfBirth(new LocalDate(1991, 12, 12)).getMasPersons();
        assertArrayEquals(expected, actual);
    }

}