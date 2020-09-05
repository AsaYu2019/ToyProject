package team.view;

import team.domain.Employee;
import team.domain.Programmer;
import team.service.NameListService;
import team.service.TeamException;
import team.service.TeamService;

/**
 * @Description:
 * @Author: asayu
 * @Creat:8/20/20 8:40 PM
 **/
public class TeamView {
    private NameListService listsvc  = new NameListService();
    private TeamService teamsvc = new TeamService();

    private void enterMainMenu() throws TeamException {
        boolean loopFlag = true;
        char menu = 0;
        while(loopFlag) {
            if(menu != '1'){
                listAllEmployees();
            }
            System.out.println("1-Team List 2-Add Team Member 3-Delete Team Member 4-Quit Select please（1-4）：");
            menu = Utility.readMenuSelection();
            switch (menu) {
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("Quit Sure？N/Y：");
                    char isExit = Utility.readConfirmSelection();
                    if(isExit == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }
        }
    }

    private void listAllEmployees(){
        System.out.println("------------------------------Staff Scheduling Software--------------------------------\n");
        System.out.println("ID\tName \tAge \tSalary \t\tPosition \tStatus \tBonus \tStocks \tEquipment");

        Employee[] employees = listsvc.getAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
        System.out.println("-------------------------------------------------------------------------------");

    }

    private void getTeam(){
        System.out.println("----------------------------------Team Member List-----------------------------------");
        Programmer[] team = teamsvc.getTeam();
        if(team == null || team.length == 0){
            System.out.println("There is no member in team now");
        }else{
            System.out.println("TID/ID\tName \tAge \tSalary \t\tPosition \tStatus \tBonus \tStocks\n");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("-------------------------------------------------------------------------------");


    }

    private void addMember() {
        System.out.println("Add member to team");
        System.out.println("Select member's ID you want to add：");
        int id = Utility.readInt();
        try{
            Employee emp = listsvc.getEmployee(id);
            teamsvc.addMember(emp);
            System.out.println("Add successful");
        } catch (TeamException e) {
            System.out.println("Add failed, because:" + e.getMessage());
        }
        Utility.readReturn();

    }

    private void deleteMember() {
        System.out.println("----------------------------------Delete Team Member-----------------------------------");
        System.out.println("Please input the member's TeamID that you want to delete：");
        int id = Utility.readInt();
        try {
            teamsvc.removeMember(id);
        } catch (TeamException e) {
            System.out.println("Delete failed, because：" + e.getMessage());
        }
        Utility.readReturn();
    }

    public static void main(String[] args) throws TeamException {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }
}
