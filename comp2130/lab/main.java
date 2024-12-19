import java.util.Scanner;

public class StudentScores {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. collect the number of students
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();

        // 2. parallel arrays
        String[] students = new String[numStudents];
        double[] averages = new double[numStudents];

        // 3. array setup 
        double[][] subjectScores = new double[numStudents][3]; 

        // 4. collect data 
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter the name of student " + (i + 1) + ": ");
            students[i] = scanner.nextLine();

            for (int j = 0; j < 3; j++) {
                String subject = "";
                if (j == 0) subject = "C#";
                else if (j == 1) subject = "Java";
                else subject = "Python";
                // Validation
                double score = -1;
                while (score < 0 || score > 100) {
                    System.out.print("Enter " + subject + " score for " + students[i] + " (between 0 and 100): ");
                    score = scanner.nextDouble();
                    if (score < 0 || score > 100) {
                        System.out.println("Invalid score. Please enter a score between 0 and 100.");
                    }
                }
                subjectScores[i][j] = score;
            }
            scanner.nextLine(); 
        }

        // 5. calculate average for each student
        calculateAverages(subjectScores, averages, numStudents);

        // 6. display data 
        displayScores(students, subjectScores, numStudents);

        // 7. calculate and display the course averages
        double[] courseAverages = calculateCourseAverages(subjectScores, numStudents);
        System.out.printf("Course Average for C#: %.2f\n", courseAverages[0]);
        System.out.printf("Course Average for Java: %.2f\n", courseAverages[1]);
        System.out.printf("Course Average for Python: %.2f\n", courseAverages[2]);

        // 8. search for a student
        searchStudent(scanner, students, subjectScores, courseAverages, numStudents);

        // 9. calculate and display overall average
        double classAverage = calculateClassAverage(courseAverages);
        System.out.printf("Overall Class Average: %.2f\n", classAverage);
    }

    // calculate averages for each student
    public static void calculateAverages(double[][] subjectScores, double[] averages, int numStudents) {
        for (int i = 0; i < numStudents; i++) {
            double total = 0;
            for (int j = 0; j < 3; j++) {
                total += subjectScores[i][j];
            }
            averages[i] = total / 3; 
        }
    }

    // display the scores of all students
    public static void displayScores(String[] students, double[][] subjectScores, int numStudents) {
        System.out.println("\nStudent Scores:");
        System.out.printf("%-15s%-10s%-10s%-10s\n", "Student", "C#", "Java", "Python");
        for (int i = 0; i < numStudents; i++) {
            System.out.printf("%-15s%-10.2f%-10.2f%-10.2f\n", students[i], subjectScores[i][0], subjectScores[i][1], subjectScores[i][2]);
        }
    }

    // calculate course averages 
    public static double[] calculateCourseAverages(double[][] subjectScores, int numStudents) {
        double[] courseAverages = new double[3]; 
        for (int j = 0; j < 3; j++) {
            double total = 0;
            for (int i = 0; i < numStudents; i++) {
                total += subjectScores[i][j];
            }
            courseAverages[j] = total / numStudents;
        }
        return courseAverages;
    }

    // search for a specific student and compare their scores with class averages
    public static void searchStudent(Scanner scanner, String[] students, double[][] subjectScores, double[] courseAverages, int numStudents) {
        System.out.print("\nEnter the name of the student to search: ");
        String searchName = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < numStudents; i++) {
            if (students[i].equalsIgnoreCase(searchName)) {
                found = true;
                System.out.printf("Scores for %s:\n", students[i]);
                System.out.printf("C#: %.2f (%s class average)\n", subjectScores[i][0], subjectScores[i][0] >= courseAverages[0] ? "above" : "below");
                System.out.printf("Java: %.2f (%s class average)\n", subjectScores[i][1], subjectScores[i][1] >= courseAverages[1] ? "above" : "below");
                System.out.printf("Python: %.2f (%s class average)\n", subjectScores[i][2], subjectScores[i][2] >= courseAverages[2] ? "above" : "below");
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // calculate the overall average
    public static double calculateClassAverage(double[] courseAverages) {
        double total = 0;
        for (double avg : courseAverages) {
            total += avg;
        }
        return total / 3; 
    }
}
