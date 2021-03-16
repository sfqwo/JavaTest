package helloapp;

import java.util.*;

public class prog {
    public static void main() {
        String brack = "[]";
        String number = "0123456789";
        String words = "";
        String temp = "";
        String tmp = "";
        ArrayList<String> list = new ArrayList<String>();

        Scanner in = new Scanner(System.in);
        System.out.print("Input a line: ");

        String input = in.nextLine();
        in.close();

        for (int i = 0; i < input.length(); i++) {
            int j = i;

            String elem = input.substring(i, i + 1);
            if (number.contains(elem)) {
                if (!temp.isEmpty()) {
                    tmp = temp;
                    temp = "";
                }
                while (number.contains(input.substring(j, j + 1))) {
                    temp += input.substring(j, j + 1);
                    j++;
                    if (j == input.length())
                        PrintError(1);
                }
                list.add(temp);
                temp = "";
                i = j - 1;
            }

            else if (brack.contains(elem)) {
                int index = brack.indexOf(elem);

                if ((index & 1) == 1) {
                    int m = 1;
                    int index_open_br = list.size() - m;

                    if (!temp.isEmpty()) {
                        list.add(temp);
                    }

                    if (index_open_br <= 0)
                        PrintError(2);

                    while (!brack.contains(list.get(index_open_br))) {
                        m++;
                        index_open_br = list.size() - m;
                    }

                    if (m > 2) {
                        for (int s = index_open_br + 1; s < list.size() + 1; s++) {
                            words += list.remove(index_open_br + 1);
                        }
                        list.add(words);
                    }

                    String word = list.remove(list.size() - 1);

                    String br = list.remove(list.size() - 1);
                    if (!(br.equals(brack.substring(index - 1, index))))
                        PrintError(2);

                    String k = list.remove(list.size() - 1);
                    list.add(stringMultiply(word, Integer.parseInt(k)));

                    words = "";
                    temp = "";
                } else
                    list.add(elem);
            } else if (elem.matches("^[a-zA-Z0-9]+$"))
                temp += elem;
            else
                PrintError(3);
        }

        String output = "";
        for (int t = 0; t < list.size(); t++) {
            if (!list.get(t).matches("^[a-zA-Z0-9]+$"))
                PrintError(4);
        }
        if (list.size() >= 1) {
            for (int t = 0; t < list.size(); t++) {
                output += list.get(t);
            }
        } else
            PrintError(5);

        if (!temp.isEmpty()) {
            output += temp;
        }
        if (!tmp.isEmpty()) {
            output = tmp + output;
        }

        System.out.println("Output: " + output);
    }

    public static String stringMultiply(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void PrintError(int n) {
        switch (n) {
        case 1:
            System.out.println("Last symbol cannot be a number\n");
            System.exit(1);
        case 2:
            System.out.println("Brackets are not balanced\n");
            System.exit(2);
        case 3:
            System.out.println("You entered wrong symbol!\n");
            System.exit(3);
        case 4:
            System.out.println("Wrong line!\n");
            System.exit(4);
        case 5:
            System.out.println("Error of input!");
            System.exit(5);
        }
    }
}