package RepositorySearcher;

import Person.Person;

public class FioPersonChecker implements iPersonChecker {

    @Override
    public boolean check(Person p, Object value) {
        return p.getFullName().contains(value.toString());
    }
}
