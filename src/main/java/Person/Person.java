package Person;


import org.joda.time.LocalDate;
import org.joda.time.Years;


public class Person {


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Character getGender() {
        return gender;
    }

    private void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private LocalDate dateOfBirth;
    private String fullName;
    private Character gender;

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", fullName='" + fullName + '\'' +
                ", sex=" + gender +
                '}';
    }

    /**
     * @param i Id
     * @param a dateOfBirth
     * @param s fullName
     * @param f gender
     */
    public Person(Integer i, LocalDate a, String s, Character f) {
        this.setId(i);
        this.setGender(f);
        this.setFullName(s);
        this.setDateOfBirth(a);
    }

    public Person() {
        this.id = 0;
        this.gender = ' ';
        this.dateOfBirth = LocalDate.parse("2000-12-12");
        this.fullName = "";
    }

    /**
     * @return person's age
     */
    public Years findAge() {
        return Years.yearsBetween(dateOfBirth, LocalDate.now());
    }


}
