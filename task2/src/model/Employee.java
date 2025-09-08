package model;

public class Employee {

    public enum Position{
        ENGINEER, MANAGER, DIRECTOR, PRODUCTOWNER;
    }

    private final String name;
    private final int age;
    private final Position position;

    public Employee(String name, int age, Position position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return "Employee [name=" + name + ", age=" + age + ", position=" + position + "]\n";
    }
}
