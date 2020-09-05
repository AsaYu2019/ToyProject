package team.domain;

import team.service.Status;

public class Programmer extends Employee {


    private int memberId;
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer() {
        super();
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getDetails() + "\t" + "Programmer" + "\t" + status + "\t\t\t\t\t" + equipment.getDescription();
    }

    public String getDetailsForTeam(){
        return memberId + "/" + getDetails() + '\t' + "Programmer" + '\t' + getStatus();
    }
}
