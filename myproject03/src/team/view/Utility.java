package team.view;

import java.util.Scanner;

//实现键盘访问
//8-15-2020
public class Utility {
    private static Scanner scanner = new Scanner(System.in);

    //systim.in读取键盘
    public static char readMenuSelection() {
        char c;
        while (true) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4') {
                System.out.println("Wrong selection, please input a number again:");
            } else break;
        }
        return c;
    }

    public static void readReturn() {
        System.out.println("Press Enter to continue...");
        readKeyBoard(100, true);
    }

    public static int readInt() {
        int n;
        while (true) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Wrong number, please input again:");
            }
        }
        return n;
    }

    public static char readConfirmSelection() {
        char c;
        while (true) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Wrong selection, please input again:");
            }
        }
        return c;
    }

    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }
            if (line.length() > limit) {
                System.out.print("Length of input error( should be less than " + limit + "), please input again:");
                continue;
            }
            break;
        }
        return line;
    }
}
