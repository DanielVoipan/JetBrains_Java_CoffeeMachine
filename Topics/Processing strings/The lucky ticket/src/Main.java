import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();

        String[] splitNum = number.split("");
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < splitNum.length; i++) {
            if (i < 3) {
                sum1 += Integer.parseInt(splitNum[i]);
            } else {
                sum2 += Integer.parseInt(splitNum[i]);
            }
        }
        if (sum1 == sum2) {
            System.out.println("Lucky");
        } else {
            System.out.println("Regular");
        }
    }
}