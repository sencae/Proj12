package Repository;


import Comparators.*;
import Person.Person;
import RepositorySearcher.*;
import RepositorySorters.*;
import org.joda.time.LocalDate;


public class Repository {
    private isorter sorter;
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
    }

    /**
     * @param p Person object
     */
    public void add(Person p) {
        addInRepos(p, size);
    }

    private void addInRepos(Person p, int size) {
        if (masPersons.length == size) {
            masPersons = resize();
        }
        masPersons[size] = p;
        this.size++;
    }

    /**
     * function to get a full list of persons
     */
    private void showPersons() {
        for (int i = 0; i < size; i++)
            System.out.println(masPersons[i].toString());
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
     * function to get one person
     *
     * @param index index of person to take
     */
    public void getPerson(int index) {
        if (index < 0 || index > masPersons.length)
            System.out.println("неверный индекс");
        else
            System.out.println(masPersons[index].toString());
    }

    /**
     * @param index index of person to take
     */
    public void getAgeOfPerson(int index) {

        System.out.println(masPersons[index].findAge().getYears());
    }

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

        Repository f = new Repository(new SorterByGender());
        f.add(new Person(1, LocalDate.now(), "aaaaaaa", 'm'));
        f.add(new Person(2, new LocalDate(1991, 12, 12), "bbbbb lll", 'w'));
        f.add(new Person(3, new LocalDate(1991, 11, 11), "bbfsabb lll", 'm'));
        f.add(new Person(4, new LocalDate(1993, 10, 4), "cccc aaa", 'w'));
        f.add(new Person(5, new LocalDate(1991, 12, 12), "bbbbb jjj", 'w'));
        f.showPersons();
        System.out.println();
        f.sortBy(new CompByGender());
        f.showPersons();
        Repository n = f.searchByFio("lll");
        System.out.println();
        n.showPersons();
    }

}


