package RepositorySearcher;

import Person.Person;

public class GenderPersonChecker implements iPersonChecker {
    @Override
    public boolean check(Person p, Object value) {
        return p.getGender().equals(value);
    }
}
