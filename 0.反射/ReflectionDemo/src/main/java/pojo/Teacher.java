package pojo;

public class Teacher {
    private String name;
    private double salary;

    public Teacher(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void teach(){
        System.out.println("Teacher is teaching");
    }
}
