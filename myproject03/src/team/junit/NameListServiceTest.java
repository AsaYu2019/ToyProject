package team.junit;

import org.junit.Test;
import team.domain.Employee;
import team.service.NameListService;
import team.service.TeamException;

/**
 * @program myproject03
 * @description: test NameListService class
 * @author: asayu
 * @create: 2020/08/18 11:13
 */
public class NameListServiceTest {

    @Test
    public void testGetAllEmployees() {
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }
    @Test
    public void testGetEmployee(){
        NameListService service = new NameListService();
        int id = 101;
        try{
            Employee employee = service.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            e.printStackTrace(e.getMessage());
        }

    }


}
