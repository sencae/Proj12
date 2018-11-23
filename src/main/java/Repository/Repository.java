package Repository;


import Comparators.*;
import Person.Person;
import RepositorySearcher.DateOfBirthChecker;
import RepositorySearcher.FioPersonChecker;
import RepositorySearcher.GenderPersonChecker;
import RepositorySearcher.PersonChecker;
import RepositorySorters.*;
import org.joda.time.LocalDate;



public class Repository {
    private isorter sorter = new SorterByGender();
    private int size = 0;
    private Person[] masPersons = new Person[size];


    /**
     *
     * @param index index of person to remove
     */
    public void removePerson(int index){
        size--;
        if(index==0) {
            Person[] newMasPersons = new Person[masPersons.length - 1];
            System.arraycopy(masPersons,1,newMasPersons,0,newMasPersons.length);
                masPersons = newMasPersons;
        }
        else if(index==masPersons.length-1) {
            Person[] newMasPersons = new Person[masPersons.length - 1];
            if (newMasPersons.length >= 0)
                System.arraycopy(masPersons, 0, newMasPersons, 0, newMasPersons.length);
            masPersons = newMasPersons;
        }
        else {
            Person[] newMasPersons = new Person[masPersons.length - 1];
            for (int i =0;i<masPersons.length;i++)
            {
                if(i<index)
                    newMasPersons[i]=masPersons[i];
                else if(i==index) {continue;
                }
                else
                    newMasPersons[i-1]=masPersons[i];

            }
            masPersons=newMasPersons;
        }
        }

    /**
     *
     * @param p Person object
     */
    private void addInRepos(Person p) {
        if (size == 0 || masPersons.length == size) {
            size++;
            masPersons = resize();
        }
        masPersons[masPersons.length - 1] = p;
    }

    /**
     * function to get a full list of persons
     */
    public void showPersons() {
        for (Person p : masPersons)
            System.out.println(p.toString());
    }

    /**
     *
     * @return array with new size
     */
    private Person[] resize() {
        Person[] newMasPerson = new Person[size];
        System.arraycopy(masPersons, 0, newMasPerson, 0, masPersons.length);
        return newMasPerson;
    }

    /**
     * function to get one person
     * @param index index of person to take
     */
    public void getPerson(int index) {
        if(index<0||index>masPersons.length)
            System.out.println("неверный индекс");
        else
        System.out.println(masPersons[index].toString());
    }

    /**
     *
     * @param index index of person to take
     */
    public void getAgeOfPerson(int index){

        System.out.println(masPersons[index].findAge().getYears());
        }
    public void sortByFio() {
       sorter.sort(masPersons,new CompByFio());
    }
    public void sortByDoB(){
        sorter.sort(masPersons,new CompByDateOfBirth());
    }
    public void sortByGender(){
        sorter.sort(masPersons,new CompByGender());
    }
    private Repository search(PersonChecker p,Object value){
        Repository result = new Repository();
        for(int i = 0;i<size;i++){
            if(p.check(masPersons[i],value))
                result.addInRepos(masPersons[i]);
        }
        return result;
    }
    public Repository searchByFio(String fio){
        return search(new FioPersonChecker(),fio);
    }
    public Repository searchByGender(Character gender){
        return search(new GenderPersonChecker(),gender);
    }
    public Repository searchByDateOfBirth(LocalDate dateOfBirth){
        return search(new DateOfBirthChecker(),dateOfBirth);
    }
    public static void main(String[] args) {

        Repository f = new Repository();
        f.addInRepos(new Person(1,LocalDate.now(),"aaaaaaa",'m'));
        f.addInRepos(new Person(2,new LocalDate(1991,12,12),"bbbbb",'w'));
        f.addInRepos(new Person(3,new LocalDate(1991,11,11),"bbfsabb",'m'));
        f.addInRepos(new Person(4,new LocalDate(1993,10,4),"cccc",'w'));
        f.addInRepos(new Person(5,new LocalDate(1991,12,12),"bbbbb",'w'));
        System.out.println();
        f.sortByGender();
        f.showPersons();
Repository n = f.searchByDateOfBirth(new LocalDate(1991,12,12));
        System.out.println();
n.showPersons();
    }

}


