package RepositorySorters;

import Comparators.iPersonComparator;
import Person.Person;

public interface Isorter {
    void sort(Person[] p, iPersonComparator c);
}
