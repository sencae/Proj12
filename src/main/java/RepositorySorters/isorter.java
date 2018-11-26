package RepositorySorters;

import Comparators.iPersonComparator;
import Person.Person;

public interface isorter {
    void sort(Person[] p, iPersonComparator c);
}
