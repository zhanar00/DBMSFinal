package ProjectOpp;
import java.util.*;
import java.io.*;
class MainMenu {

    public void menu() {
        System.out.println("Press 1 : To Add an Employee Details");
        System.out.println("Press 2 : To See an Employee Details ");
        System.out.println("Press 3 : To Remove an Employee");
        System.out.println("Press 4 : To Update Employee Details");
        System.out.println("Press 5 : To Exit the EMS Portal");

    }
}class Employee_Add extends EmployDetail{

    public void createFile() {
        Scanner scan = new Scanner(System.in);

        EmployDetail detail = new EmployDetail();
        detail.getInfo();
        try {
            File f1 = new File("file" + detail.id + ".txt");
            if (f1.createNewFile()) {
                try (FileWriter myWriter = new FileWriter("file" + detail.id + ".txt")) {
                    myWriter.write("Employee ID:" + detail.id +
                            "\n" + "Employee Name :" + detail.name +
                            "\n" + "Employee age :" + detail.age +
                            "\n" + "Employee Contact :" + detail.contact +
                            "\n" + "Email Information :" + detail.email +
                            "\n" + "Employee position :" + detail.position +
                            "\n" + "Employee Salary :" + detail.salary);
                }
                System.out.println("\nEmployee has been Added :)\n");

            } else {
                System.out.println("\nEmployee already exists :(");
            }
            System.out.print("\nPress Enter to Continue...");
            scan.nextLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
class EmployDetail {

    String name;
    int age;
    int id;
    String email;
    String position;
    String salary;
    String contact;

    public void getInfo() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Employee's name --------: ");
        name = scan.nextLine();
        System.out.print("Enter Employee's age ---------: ");
        age = scan.nextInt();
        System.out.print("Enter Employee's ID ----------: ");
        id = scan.nextInt();
        System.out.print("Enter Employee's Email ID ----: ");
        scan.nextLine();
        email = scan.nextLine();
        System.out.print("Enter Employee's Position ----: ");
        position = scan.nextLine();
        System.out.print("Enter Employee contact Info --: ");
        contact = scan.nextLine();
        System.out.print("Enter Employee's Salary ------: ");
        salary = scan.nextLine();
    }
}
class Employee_Show {

    public void viewFile(String s) throws Exception {
        File file = new File("file" + s + ".txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
    }
}

interface Remove {
    void removeFile(int ID);
}

class Employee_Remove implements Remove{

    @Override
    public void removeFile(int ID) {
        File file = new File("file" + ID + ".txt");
        if (file.exists()) {
            if (file.delete())
            {
                System.out.println("\nEmployee has been removed Successfully");
            }
        } else
            System.out.println("\nEmployee does not exists :( ");

    }
}
class Employee_Update {

    public void updateFile(String s, String o, String n) throws IOException {
        File file = new File("file" + s + ".txt");
        Scanner scan = new Scanner(file);

        String newString = "";
        while (scan.hasNextLine()) {
            newString = newString + "\n" + scan.nextLine();
        }
        try (FileWriter fw = new FileWriter("file" + s + ".txt")) {
            newString = newString.replaceAll(o, n);
            fw.write(newString);
        }
    }
}

class CodeExit {

    public void out() {
        System.out.println("\n*****************************************");
        System.out.println("Thank You For Sharing Details :) ");
        System.exit(0);
    }
}
class EmployManagementSystem extends MainMenu {

    public static void main(String arv[]) {
        Scanner scan = new Scanner(System.in);
        Employee_Show show = new Employee_Show();

        int num = 0;

        MainMenu m = new MainMenu();
        m.menu();

        while (true) {
            System.out.print("\nPlease Enter choice : ");
            num = Integer.parseInt(scan.nextLine());
            while (num <= 0 || num >= 6) {
                System.out.print("\nWrong choice, Please Enter choice :");
                 num= Integer.parseInt(scan.nextLine());
            }

            switch (num) {
                case 1: {
                    Employee_Add add = new Employee_Add();
                    add.createFile();
                    m.menu();
                    break;
                }
                case 2: {
                    System.out.print("\nPlease Enter Employee's ID :");
                    String s = scan.nextLine();
                    try {
                        show.viewFile(s);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\nPress Enter to Continue...");
                    scan.nextLine();
                    m.menu();
                    break;
                }

                case 3: {
                    System.out.print("\nPlease Enter Employee's ID :");
                    String s = scan.nextLine();
                    Employee_Remove epr = new Employee_Remove();
                    epr.removeFile(Integer.parseInt(s));

                    System.out.print("\nPress Enter to Continue...");
                    scan.nextLine();
                    m.menu();
                    break;
                }
                case 4: {
                    System.out.print("\nPlease Enter Employee's ID :");
                    String srt = scan.nextLine();
                    try {
                        show.viewFile(srt);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    Employee_Update epu = new Employee_Update();
                    System.out.print("Please Enter the detail you want to Update :");
                    System.out.print("\nFor Example :\n");
                    System.out.println("If you want to Change the Name, then Enter Current Name and Press Enter. Then write the new Name then Press Enter. It will Update the Name.\n");
                    String s = scan.nextLine();
                    System.out.print("Please Enter the Updated Info :");
                    String n = scan.nextLine();
                    try {
                        epu.updateFile(srt, s, n);

                        System.out.print("\nPress Enter to Continue...");
                        scan.nextLine();
                        m.menu();
                        break;
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                case 5: {
                    CodeExit obj = new CodeExit();
                    obj.out();
                }
            }
        }
    }
}

