package RepositorySearcher;

import Person.Person;

public class FioPersonChecker implements PersonChecker {

    @Override
    public boolean check(Person p, Object value) {
        return p.getFullName().contains(value.toString());
    }
}
