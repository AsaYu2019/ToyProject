package team.service;

import team.domain.Architect;
import team.domain.Designer;
import team.domain.Employee;
import team.domain.Programmer;

/**
 * @Description:
 * @Author: asayu
 * @Creat date:8/19/20 1:43 PM
 **/
public class TeamService {
    private static int counter = 1; //prepare for member ID
    private final int MAX_MEMBER = 5; //限制每个团队的最大人数
    private Programmer[] team = new Programmer[MAX_MEMBER]; //prepare for storing members
    private int total; //total member of team

    public TeamService() {
    }

    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];//若直接return team，也许原team未满，会return多余的null
        for (int i = 0; i < team.length; i++) {     //故建立新数组储存仅有的几个member并return之。
            team[i] = this.team[i];
        }
        return team;
    }

    /**
     * 添加失败情形：
     * 1.team已满
     * 2.该成员不是开发人员
     * 3.该员工已在team中
     * 4.该员工已存在于其他team中
     * 5.该员工正在休假
     * 6.团队中最多只有一名架构师
     * 7.团队中最多只有两名设计师
     * 8.团队中最多只能有三名程序员
     *
     */
    public void addMember(Employee e) throws TeamException{
        if(total >= MAX_MEMBER){
            throw new TeamException("This team is full. Adding failed.");
        }
        if(!(e instanceof Programmer)){
            throw new TeamException("This member is not a developer. Adding failed.");
        }
        if(isExist(e)){
            throw new TeamException("This member is in team already. Adding failed.");
        }
        Programmer p = (Programmer) e; //如果e只是Employee， 则在之前的几个if中就已经抛出异常了，故这里肯定可以cast成Programmer
        if("BUSY".equals(p.getStatus().getNAME())){ //如果用p.getStatus().getNAME().equals("BUSY"),则p.getStatus().getNAME()可能出现空指针异常
            throw new TeamException("This member is in another team already. Adding failed");
        }else if("VACATION".equals(p.getStatus().getNAME())){
            throw new TeamException("This member is on vocation. Adding failed");
        }
        //获取team中programmer，designer，architect的数量
        int numOfArch = 0, numOfDes = 0,numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect){
                numOfArch++;
            }else if(team[i] instanceof Designer){
                numOfDes++;
            }else{
                numOfPro++;
            }
        }

        /**
         * 不能用if(e instanceof Architect && numOfArch >= 1)这样的三个if-else-if
         * 如：若team中已有且只有2个designer，此时想添加architect，第一个if判断：是否是architect？是，数量是否已大于等于1？否
         * 如此，不进入第一层if抛出异常，但会进行下一个else if 判断。
         * 第二个else if判断：是否为Designer？因architect为designer的子类，故为true；数量是否大于等于2？true
         * 因此，进入第二个else if语句，抛出异常，添加不成功
         * 而实际上是应该添加成功的，所以这种写法运行罗辑有误。
         */
        if(e instanceof Architect){
            if(numOfArch >= 1){
                throw new TeamException("There is already an Architect in this team. Adding Failed");
            }
            /**
             * 因一个architect已经进经判断进入了第一个if，故不会再进行else if 判断其是否为designer，故不会抛出设计师数量异常。
             */
        }else if(e instanceof  Designer){
            if(numOfDes >= 2) {
                throw new TeamException("There are already two Designers in this team. Adding Failed");
            }
        }else if(e instanceof Programmer){
                if(numOfPro >= 3) {
                throw new TeamException("There are already three programmer in this team. Adding Failed");
            }
        }
        //所有不能添加的情况都没发生,则加入team
        team[total] = p;
        total++;
        //加入team后其memberID 和 Status发生改变
        p.setMemberId(counter++);
        p.setStatus(Status.BUSY);

    }
    private boolean isExist(Employee e){
        for (int i = 0; i < total; i++) {
            if(team[i].getId() == e.getId()){
                return true;
            }
        }
        return false;
    }
    public void removeMember(int memberID) throws TeamException {
        int i= 0;
        for (i = 0; i < total; i++) {
            if(team[i].getMemberId() == memberID){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if(i == total){ //若没有输入正确，则一定是遍历完后退出循环的，而此时退出循环的条件为i = total;
            throw new TeamException("Couldn't find this member. Adding Failed");
        }

        for (int j = i + 1; j < total; j++) {
            team[j - 1] = team[j];
        }
        total--;
        team[total] = null;

    }
}
