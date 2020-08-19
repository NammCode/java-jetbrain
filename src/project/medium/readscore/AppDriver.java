package project.medium.readscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AppDriver {

    public static void main(String[] args) throws FileNotFoundException {
        double word = 0, sentence = 0, character = 0, syllable = 0, polysyllable = 0, vowel = 0;
        int age1 = 0, age2 = 0, age3 = 0, age4 = 0;
        double score1 = 0, score2 = 0, score3 = 0, score4 = 0, ageAvg = 0;
        String text = "";

        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            text += scanner.nextLine();
        }
        String[] sentences = text.split("[?!\\.]");
        sentence = sentences.length;
        String[] words = text.split("\\s+");
        word = words.length;
        for (int i = 0; i < word; i++) {
            int test = 0;
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].toLowerCase().charAt(j);
                if (ch != ' ') {
                    character++;
                }
                if (ch == 'a'|| ch == 'e'|| ch == 'i' ||ch == 'o' ||ch == 'u' || ch == 'y') {
                    syllable++;
                    test++;
                    vowel++;
                    if (j != words[i].length() - 1){
                        char next = words[i].toLowerCase().charAt(j+1);
                        if (next == 'a'|| next == 'e'|| next == 'i' ||next == 'o' ||next == 'u' || next == 'y') {
                            syllable--;
                            test--;
                        }
                    }
                }
            }
            if (words[i].toLowerCase().endsWith("e")){
                vowel--;
                syllable--;
                test--;
            }
            if (vowel == 0) {
                syllable++;
                test++;
            }
            vowel = 0;
            if (test > 2) polysyllable++;
            test = 0;
        }

        System.out.println("The text is:\n" + text + "\n");
        System.out.printf("Words: %.0f\n", word);
        System.out.printf("Sentences: %.0f\n", sentence);
        System.out.printf("Characters: %.0f\n", character);
        System.out.printf("Syllables: %.0f\n", syllable);
        System.out.printf("Polysyllables: %.0f\n", polysyllable);

        score1 = countARI(sentence, word, character);
        age1 = countAges(score1);
        score2 = countFK(sentence, word, syllable);
        age2 = countAges(score2);
        score3 = countSMOG(sentence, word, polysyllable);
        age3 = countAges(score3);
        score4 = countCL(sentence, word, character);
        age4 = countAges(score4);
        ageAvg = (double) (age1 + age2 + age3 + age4) / 4;

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String option = scanner1.next();
        System.out.println();

        switch (option.toLowerCase() ) {
            case "ari":
                System.out.printf("Automated Readability Index: %.2f (about %d year olds).\n", score1, age1);
                break;
            case "fk":
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).\n", score2, age2);
                break;
            case "smog":
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).\n", score3, age3);
                break;
            case "cl":
                System.out.printf("Coleman–Liau index: %.2f (about %d year olds).\n", score4, age4);
                break;
            case "all":
                System.out.printf("Automated Readability Index: %.2f (about %d year olds).\n", score1, age1);
                System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).\n", score2, age2);
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).\n", score3, age3);
                System.out.printf("Coleman–Liau index: %.2f (about %d year olds).\n", score4, age4);
                break;
        }

        System.out.printf("\nThis text should be understood in average by %.2f year olds.", ageAvg);

        scanner.close();
        scanner1.close();
    }

    public static int countAges (double score){
        int ages = 0;
        switch ((int) Math.round(score)) {
            case 1:
                ages = 6;
                break;
            case 2:
                ages = 7;
                break;
            case 3:
                ages = 9;
                break;
            case 4:
                ages = 10;
                break;
            case 5:
                ages = 11;
                break;
            case 6:
                ages = 12;
                break;
            case 7:
                ages = 13;
                break;
            case 8:
                ages = 14;
                break;
            case 9:
                ages = 15;
                break;
            case 10:
                ages = 16;
                break;
            case 11:
                ages = 17;
                break;
            case 12:
                ages = 18;
                break;
            case 13:
                ages = 24;
                break;
            case 14:
                ages = 24;
                break;
            default:
                break;
        }
        return ages;
    }

    public static double countARI(double sentence, double word, double character) {
        double score = 0;
        double a = character / word;
        double b = word / sentence;
        score += 4.71 * a + 0.5 * b - 21.43;
        return score;
    }

    public static double countFK(double sentence, double word, double syllables) {
        double score = 0;
        double a = word / sentence;
        double b = syllables / word;
        score += 0.39 * a + 11.8 * b - 15.59;
        return score;
    }

    public static double countSMOG(double sentence, double word, double polysyllable) {
        double score = 3.1291;
        score += 1.043 * Math.sqrt(polysyllable * (30 / sentence));
        return score;
    }

    public static double countCL(double sentence, double word, double character) {
        double score = 0;
        double l = (character * 100) / word;
        double s = (sentence * 100) / word;
        score = 0.0588 * l - 0.296 * s - 15.8;
        return score;
    }

}
