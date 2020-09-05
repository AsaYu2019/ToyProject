package team.service;

import team.domain.*;

/**
 * @program myproject03
 * @description:将DATA中的数据封装到Employee[]数组中
 * @author: asayu
 * @create: 2020/08/17 19:33
 */
public class NameListService {
    private Employee[] employees;

    public NameListService() {
        //根据提供的DATA类构建相应大小的employee数组
        //根据DATA中的数据构建不同对象，包括employee，programmer，designer，architect
        //将对象存于数组中
        employees = new Employee[Data.EMPLOYEES.length];


        for (int i = 0; i < employees.length; i++) {
            int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);
            //因为employee并没有equipment，所以不能每个case都用，所以不能用Equipment equipment = creatEquipment(i);，i==0时会报错
            Equipment equipment; //放入switch创建的话会redundant， 故先在外先声明而不创建，再在内创建使用，就解决了在switch内部变量名冲突的问题。
            double bonus;
            int stock;
            switch (type) {
                case Data.EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case Data.PROGRAMMER:
                    /**
                     * 第一次test时creat报错：ArrayIndexOutOfBoundsException：Index 0 out of bounds for length 0
                     * 原因在于自己把DATA中employees[][]的第一个"马云"的职位（EMPLOYEE 10)误写为了PROGRAMMER(11）
                     * 致使在creat时要为其creat相应的equipment。但其相应位置的二维数组为空，则出现此类错误
                     * 即EQUIPMENT[0] == NULL,则EQUIPMENT[0][0]中，第二个0越界。
                     */
                    equipment = creatEquipment(i);// 因代码太多，故封装为方法，
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case Data.DESIGNER:
                    equipment = creatEquipment((i));//因为在外声明过，所以此时继续用equipment不会与programmer中的变量名冲突
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case Data.ARCHITECT:
                    equipment = creatEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    //获取指定index位置的员工的设备；
    private Equipment creatEquipment(int index) {
       // if(index == 0){return null;}

        int type = Integer.parseInt(Data.EQUIPMENTS[index][0]);
        String brand = Data.EQUIPMENTS[index][1];

        switch (type) {
            case Data.PC:
                String display = Data.EQUIPMENTS[index][2];
                return new PC(brand, display);
            case Data.NOTEBOOK:
                double price = Double.parseDouble(Data.EQUIPMENTS[index][2]);
                return new NoteBook(brand, price);
            case Data.PRINTER:
                return new Printer(brand, Data.EQUIPMENTS[index][2]);
        }
        return null;
    }


    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException { //throws抛出异常，不处理；try catch抛出异常并处理；
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        throw new TeamException("找不到指定的员工");
    }
}
