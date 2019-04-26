package pe.financieraoh.projects.com.appfinancieraoh.domain;

public class UserModel {

    private String name;
    private String lastName;
    private int age;
    private String birthday;

    public UserModel() {

    }

    public UserModel(String name, String lastName, int age, String birthday) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
