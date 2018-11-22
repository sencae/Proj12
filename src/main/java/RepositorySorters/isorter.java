package RepositorySorters;

import Comparators.PersonComparator;
import Person.Person;

public interface isorter {
        void sort(Person[] p, PersonComparator c);
}
