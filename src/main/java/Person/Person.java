package Person;

import org.joda.time.LocalDate;

public class Person {
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    /** Дата рождения персоны */
    private LocalDate dateOfBirth;
    /** ФИО персоны */
    private String fullName;
    /** Пол персоны */
    private char sex;

    @Override
    public String toString() {
        return "Person{" +
                "dateOfBirth=" + dateOfBirth +
                ", fullName='" + fullName + '\'' +
                ", sex=" + sex +
                '}';
    }

    private Person(){
    System.out.println("hello");
    }
    public Integer findAge(){
        return LocalDate.now().getYear() - this.dateOfBirth.getYear();
}
    public static void main(String[] args) {
        Person a = new Person();
        a.setDateOfBirth(LocalDate.parse("1990-12-12"));
        a.setFullName("Margo Rollup Blonsky");
        a.setSex('m');
        System.out.println(a.findAge());
        System.out.println(a.toString());
    }
}
