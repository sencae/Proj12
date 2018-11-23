package RepositorySearcher;

import Person.Person;

public class DateOfBirthChecker implements PersonChecker {
    @Override
    public boolean check(Person p, Object value) {
        return p.getDateOfBirth().equals(value);
    }
}
