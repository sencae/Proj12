package Test;


import Person.Person;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    private Person p;
@Before
    public void initTest(){
    p = new Person(new LocalDate(1991,12,12),"bbbbb",'w');
}

    @Test

    public void findAge() {
assertEquals(26,p.findAge().getYears());
    }
}