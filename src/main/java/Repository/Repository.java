package Repository;


import Comparators.*;
import Person.Person;
import RepositorySearcher.*;
import RepositorySorters.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;


public class Repository {
    private final static Logger logger = LogManager.getLogger(Repository.class);
    private isorter sorter = new BubbleSorter();
    final private int DEFAULT_SIZE = 10;
    private int size;
    private Person[] masPersons = new Person[DEFAULT_SIZE];

    public Repository() {
    }

    public Repository(isorter s) {
        this.sorter = s;
    }

    /**
     * @param index index of person to remove
     */
    public void removePerson(int index) {
        try {
            size--;
            if (index == 0) {
                Person[] newMasPersons = new Person[masPersons.length - 1];
                System.arraycopy(masPersons, 1, newMasPersons, 0, newMasPersons.length);
                masPersons = newMasPersons;
            } else if (index == masPersons.length - 1) {
                Person[] newMasPersons = new Person[masPersons.length - 1];
                if (newMasPersons.length >= 0)
                    System.arraycopy(masPersons, 0, newMasPersons, 0, newMasPersons.length);
                masPersons = newMasPersons;
            } else {
                Person[] newMasPersons = new Person[masPersons.length - 1];
                for (int i = 0; i < masPersons.length; i++) {
                    if (i < index)
                        newMasPersons[i] = masPersons[i];
                    else if (i == index) {
                        continue;
                    } else
                        newMasPersons[i - 1] = masPersons[i];

                }
                masPersons = newMasPersons;
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error(e.toString());
            for (StackTraceElement s : e.getStackTrace())
                logger.error(s.toString());
        }
    }

    /**
     * @param p Person object
     */
    public void add(Person p) {
        if (masPersons.length == size) {
            masPersons = resize();
        }
        masPersons[size] = p;
        logger.info("add new " + p.toString());
        this.size++;
    }

    /**
     * @return array of persons in repository
     */
    public Person[] getMasPersons() {
        Person[] result = new Person[size];
        System.arraycopy(masPersons, 0, result, 0, result.length);
        return result;
    }

    /**
     * @return array with new size
     */
    private Person[] resize() {
        Person[] newMasPerson = new Person[size + 1];
        System.arraycopy(masPersons, 0, newMasPerson, 0, masPersons.length);
        return newMasPerson;
    }


    /**
     * @param index index of person to take
     */
    public Integer getAgeOfPerson(int index) {
        return masPersons[index].findAge();
    }

    /**
     * @param comparator comparator to sort
     */
    public void sortBy(iPersonComparator comparator) {
        sorter.sort(masPersons, comparator);
    }

    private Repository search(iPersonChecker p, Object value) {
        Repository result = new Repository();
        for (int i = 0; i < size; i++) {
            if (p.check(masPersons[i], value))
                result.add(masPersons[i]);
        }
        return result;
    }

    public Repository searchByFio(String fio) {
        return search(new FioPersonChecker(), fio);
    }

    public Repository searchByGender(Character gender) {
        return search(new GenderPersonChecker(), gender);
    }

    public Repository searchByDateOfBirth(LocalDate dateOfBirth) {
        return search(new DateOfBirthChecker(), dateOfBirth);
    }

    public static void main(String[] args) {

    }
}


