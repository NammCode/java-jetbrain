package project.medium.numeralconverter;

import java.util.Scanner;

public class AppDriver {

    public static char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    public static void main(String[] args) {
        System.out.println("Input:");
        Scanner scanner = new Scanner(System.in);
        int sourceBase = 0, targetBase = 0;
        try {
            sourceBase = scanner.nextInt();
            if(sourceBase < 1 || sourceBase > 36) {
                System.out.println("error");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("error");
            System.exit(0);
        }
        String number = scanner.next();

        try {
            targetBase = scanner.nextInt();
            if(targetBase < 1 || targetBase > 36) {
                System.out.println("error");
                System.exit(0);
            }
        } catch (Exception e){
            System.out.println("error");
            System.exit(0);
        }

        System.out.println("Output:");
        if(number.contains(".")) {
            String[] numbers = new String[2];
            numbers = number.split("\\.");
            String leftNumber = numbers[0];
            String rightNumber = numbers[1];
            leftNumber = leftConvert(sourceBase, leftNumber, targetBase);
            rightNumber = rightConvert(sourceBase, rightNumber, targetBase);
            System.out.println(leftNumber + "." + rightNumber);
        } else {
            number = leftConvert(sourceBase, number, targetBase);
            System.out.println(number);
        }

    }

    public static String leftConvert(int sourceBase, String number, int targetBase){
        String output = "";
        int decimal = 0;
        if(sourceBase == 1) {
            decimal = number.length();
            output = Integer.toString(decimal, targetBase);
        } else if(targetBase == 1) {
            decimal = Integer.parseInt(number, sourceBase);
            for (int i = 0; i < decimal; i++) {
                output += "1";
            }
        }
        else {
            decimal = Integer.parseInt(number, sourceBase);
            output = Integer.toString(decimal, targetBase);
        }
        return output;
    }

    public static String rightConvert(int sourceBase, String number, int targetBase){
        String output = "";
        int decimal = 0;
        if(sourceBase == 1) {
            decimal = number.length();
            output = Integer.toString(decimal, targetBase);
        } else if(targetBase == 1) {
            decimal = Integer.parseInt(number, sourceBase);
            for (int i = 0; i < decimal; i++) {
                output += "1";
            }
        } else {
            // convert sourceBase to 10 base
            double right = 0;
            char[] array = number.toCharArray();
            for (int i = 0; i < array.length; i++) {
                int topNumber = 0;
                for (int j = 0; j < digits.length; j++) {
                    if(array[i] == digits[j]) {
                        topNumber = j;
                    }
                }
                right += topNumber / Math.pow(sourceBase, (i+1));
            }
            // convert 10 base to targetBase
            for (int i = 0; i < 5; i++) {
                double symbol = right * targetBase;
                String doubleAsText = Double.toString(symbol);
                for (int j = 0; j < digits.length; j++) {
                    if(Integer.parseInt(doubleAsText.split("\\.")[0]) == j) {
                        output += digits[j];
                    }
                }
                String symbolNoneZero = doubleAsText.split("\\.")[1];
                right = Double.parseDouble("0." + symbolNoneZero);
            }

        }
        return output;
    }


}
