package team.domain;

public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDetails(){
        /**
         * 开始因为name长度不同，导致\t后，age栏不能对齐...
         * 解决方式为在\t前加一个空格，这样就可以在控制台输出时对齐
         * 原理： \t为补全 8 - 前一字符位数%8 的距离，即若之前一个字符个数为1，则\t补7；为10则补6；
         *       
         */
        return id +" \t" + name + " \t" + age + "\t\t" + salary;
    }
    @Override
    public String toString() {
        return getDetails();
    }
}
