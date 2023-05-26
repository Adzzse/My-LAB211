package viewer;

import controller.HospitalManagement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] options = {"Create a nurse", "Find a nurse", "Update a nurse", "Delete a nurse",
            "Add a patient", "Display patients", "Sort patients", "Save data", "Load data", "Quit"};
        String choice = "";
        HospitalManagement hm = new HospitalManagement();

        System.out.println(
                "Note: \nEach patient is cared by two nurse and one nurse takes care of maximum 2 patients.!!!");
        do {
            System.out.println("\nHospital Management System");
            choice = Menu.getChoice(options); // show Menu options
            Scanner sc = new Scanner(System.in);
            switch (choice) {
                case "1": {
//                    hm.createNurse();
                    hm.createNurse("N01", "1", 1, "1", "1", "12344567899", "123", "1", 1, 0);
                    hm.createNurse("N02", "2", 2, "2", "2", "22344567899", "223", "2", 2, 0);
                    hm.createNurse("N03", "3", 3, "3", "3", "32344567899", "323", "3", 3, 0);
                    hm.createNurse("N04", "4", 4, "4", "4", "42344567899", "423", "4", 4, 0);
                    System.out.println("Done");
                    do {
                        System.out.println("Continue adding a new nurse? (Y/N)");
                        String ans = sc.nextLine();
                        if (ans.equalsIgnoreCase("n")) {
                            break;
                        } else if (ans.equalsIgnoreCase("y")) {
//                            hm.createNurse();
                            System.out.println("Done");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } while (true);
                    break;
                }
                case "2": {
                    String name;
                    System.out.println("Please enter nurse's name:");
                    name = sc.nextLine();
                    hm.findNurse(name);
                    break;
                }
                case "3":
                    hm.updateNurse();
                    break;
                case "4":
                    hm.deleteNurse();
                    break;
                case "5":
                    hm.createPatient();

                    break;
                case "6":
                    hm.displayPatients();
                    break;
                case "7": {

                    break;
                }
                case "8": {
                    hm.saveNursesToFile();
                    hm.savePatientsToFile();
                    break;
                }
                case "9": {
                    System.out.println("Enter the file name to load nurses:");
                    String loadNursesFileName = sc.nextLine();
                    hm.loadNursesFromFile(loadNursesFileName);

                    System.out.println("Enter the file name to load patients:");
                    String loadPatientsFileName = sc.nextLine();
                    hm.loadPatientsFromFile(loadPatientsFileName);
                    break;
                }
                case "10": {
                    break;
                }
                default: {
                    System.out.println("Invalid choice.");
                }
            }
        } while (!choice.equals("10"));
    }
}
