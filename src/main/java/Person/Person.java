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
        if (i != null && a != null && !s.isEmpty() && f != null) {
            this.setId(i);
            this.setGender(f);
            this.setFullName(s);
            this.setDateOfBirth(a);
        }
    }


    /**
     * @return person's age
     */
    public Integer findAge() {
        return Years.yearsBetween(dateOfBirth, LocalDate.now()).getYears();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;
        if (!dateOfBirth.equals(person.dateOfBirth)) return false;
        if (!fullName.equals(person.fullName)) return false;
        if (!gender.equals(person.gender)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }
}
