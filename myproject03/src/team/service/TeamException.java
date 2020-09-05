package team.service;

/**
 * @program myproject03
 * @description: self-defined exception, needs to extend Exception
 * @author: asayu
 * @create: 2020/08/18 10:27
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -338754677383839847L; //为了在传输对象的时候两端保证能识别出来唯一的类；

    public TeamException() {
        super();
    }

    public TeamException(String message) {
        super(message);
    }

    public void printStackTrace(String message) {
        super.printStackTrace();
    }
}
