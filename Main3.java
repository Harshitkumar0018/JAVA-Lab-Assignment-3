import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager3 manager = new StudentManager3("students_lab3.txt");
        boolean running = true;

        while (running) {
            System.out.println("===== Student Menu (Lab 3) =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Save and Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter Roll No (Integer): ");
                        Integer roll = Integer.valueOf(sc.nextLine().trim());

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        System.out.print("Enter Marks: ");
                        Double marks = Double.valueOf(sc.nextLine().trim());

                        Thread loader = new Thread(new Loader("Loading"));
                        loader.start();
                        loader.join();

                        manager.addStudent(roll, name, email, course, marks);
                        System.out.println("Student Added Successfully.");
                        break;

                    case "2":
                        manager.viewAll();
                        break;

                    case "3":
                        System.out.print("Enter name to search: ");
                        String sName = sc.nextLine();

                        Thread loader2 = new Thread(new Loader("Searching"));
                        loader2.start();
                        loader2.join();

                        Student found = manager.searchByName(sName);
                        System.out.println(found);
                        manager.printGrade(found);
                        break;

                    case "4":
                        System.out.print("Enter name to delete: ");
                        String dName = sc.nextLine();

                        Thread loader3 = new Thread(new Loader("Deleting"));
                        loader3.start();
                        loader3.join();

                        manager.deleteByName(dName);
                        System.out.println("Student Deleted Successfully.");
                        break;

                    case "5":
                        Thread loader4 = new Thread(new Loader("Saving"));
                        loader4.start();
                        loader4.join();

                        manager.saveToFile();
                        System.out.println("Data Saved. Exiting...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid Choice! Try Again.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Number Format!");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Validation Error: " + e.getMessage());
            }
            catch (StudentNotFoundException e) {
                System.out.println("Student Error: " + e.getMessage());
            }
            catch (InterruptedException e) {
                System.out.println("Thread Interrupted.");
            }
        }
    }
}
