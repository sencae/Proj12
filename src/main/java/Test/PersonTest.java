package Test;

import Person.Person;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person p;

    @Test
    void findAge() {
        p = new Person(0, new LocalDate(2000, 12, 12), " ", 'm');
        Integer expected = 17;
        Integer actual = p.findAge();
        assertEquals(expected, actual);
    }
}