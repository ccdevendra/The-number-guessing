import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("📘 Student Grade Calculator");
        System.out.print("Enter number of subjects: ");

        int n = getValidInteger(input);

        int totalMarks = 0;

        for (int i = 1; i <= n; i++) {
            int marks;
            while (true) {
                System.out.print("Enter marks for subject " + i + " (0–100): ");
                marks = getValidInteger(input);
                if (marks >= 0 && marks <= 100) {
                    break;
                } else {
                    System.out.println("❌ Invalid input! Marks must be between 0 and 100.");
                }
            }
            totalMarks += marks;
        }

        double average = (double) totalMarks / n;

        char grade;
        String remark;
        if (average >= 90) {
            grade = 'A';
            remark = "Excellent 🎉";
        } else if (average >= 75) {
            grade = 'B';
            remark = "Very Good 👍";
        } else if (average >= 60) {
            grade = 'C';
            remark = "Good 🙂";
        } else if (average >= 40) {
            grade = 'D';
            remark = "Pass ⚡";
        } else {
            grade = 'F';
            remark = "Fail ❌";
        }

        System.out.println("\n===== 📊 Results =====");
        System.out.println("Total Marks: " + totalMarks + " / " + (n * 100));
        System.out.println("Average Percentage: " + String.format("%.2f", average) + "%");
        System.out.println("Grade: " + grade + " (" + remark + ")");

        input.close();
    }

    // ✅ Safe integer input
    private static int getValidInteger(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("❌ Invalid input! Please enter a number: ");
                input.next(); // clear wrong input
            }
        }
    }
}
