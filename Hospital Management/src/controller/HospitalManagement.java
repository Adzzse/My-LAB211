package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Nurse;
import model.Patient;

/**
 *
 * @author Admin
 */
public class HospitalManagement {

    private Scanner sc;
    private Map<String, Nurse> nurses;
    private Map<String, Patient> patients;
     private int saveCount;

    public HospitalManagement() {
        nurses = new HashMap<>();
        patients = new HashMap<>();
        sc = new Scanner(System.in);
        saveCount=0;
    }

    public void createNurse(String staffID, String name, int age, String gender, String address, String phone, String department, String shift, double salary, int patientCount) {
        Nurse nurse = new Nurse(staffID, name, age, gender, address, phone, department, shift, salary, patientCount);
        nurses.put(staffID, nurse);
        System.out.println("Nurse added successfully!");
    }

    public void createNurse() {
        System.out.println("Please input the following information for the nurse:");
        String staffID = "";
        while (staffID.equals("")) {
            try {
                System.out.print("Staff ID: ");
                staffID = sc.nextLine();
                if (staffID.equals("")) {
                    Validation.isEmpty(staffID, "Patient ID");
                }
                if (isStaffIDUnique(staffID)) {

                } else {
                    System.out.println("Staff ID already exists.");
                    staffID = "";
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid staff ID.");
            }
        }
        String name = "";
        while (name.equals("")) {
            System.out.print("Name: ");
            name = sc.nextLine();
            Validation.isEmpty(name, "Name");
        }
        int age = 0;
        while (age == 0) {
            String ageStr = "";
            while (ageStr.equals("")) {
                System.out.print("Age: ");
                ageStr = sc.nextLine();
                Validation.isEmpty(ageStr, "Age");
            }
            try {
                age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    System.out.println("Age must > 0.");
                    age = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid age.");
            }
        }
        String gender = "";
        while (gender.equals("")) {
            System.out.print("Gender: ");
            gender = sc.nextLine();
            Validation.isEmpty(gender, "Gender");
        }
        String address = "";
        while (address.equals("")) {
            System.out.print("Address: ");
            address = sc.nextLine();
            Validation.isEmpty(address, "Address");
        }
        String phone = "";
        while (phone.equals("")) {
            System.out.print("Phone: ");
            phone = sc.nextLine();
            if (phone.equals("")) {
                Validation.isEmpty(phone, "Phone");
            }
            if (!Validation.isPhoneValid(phone)) {
                System.out.println("Invalid phone format. Please enter a valid phone number.");
                phone = "";
            }
        }
        String department = "";
        while (department.equals("")) {
            System.out.print("Department: ");
            department = sc.nextLine();
            Validation.isEmpty(department, "Department");
            if (department.length() < 3 || department.length() > 50) {
                System.out.println("Department must be between 3 and 50 characters.");
                department = "";
            }
        }
        String shift = "";
        while (shift.equals("")) {
            System.out.print("Shift: ");
            shift = sc.nextLine();
            Validation.isEmpty(shift, "Shift");
        }
        double salary = 0.0;
        while (salary == 0.0) {
            String salStr = "";
            while (salStr.equals("")) {
                System.out.print("Salary: ");
                salStr = sc.nextLine();
                Validation.isEmpty(salStr, "Salary");
            }
            try {
                salary = Double.parseDouble(salStr);
                if (salary <= 0) {
                    System.out.println("Salary must be a positive number.");
                    salary = 0.0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for salary.");
            }
        }
        int patientCount = 0;

        Nurse nurse = new Nurse(staffID, name, age, gender, address, phone, department, shift, salary, patientCount);
        nurses.put(staffID, nurse);
        System.out.println("Nurse added successfully!");
    }

    public void findNurse(String name) {
        boolean found = false;
        for (Nurse nurse : nurses.values()) {
            if (nurse.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Nurse found:");
                System.out.println(nurse.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("The nurse does not exist.");
        }
    }

    public void updateNurse() {
        try {
            System.out.print("Enter the staff ID to update: ");
            String staffID = sc.nextLine();
            Nurse nurseToUpdate = nurses.get(staffID);

            // Check exist
            if (nurseToUpdate == null) {
                System.out.println("The nurse does not exist.");
                return;
            }

            // Found
            System.out.println("Enter new details for the nurse:");

            System.out.print("Name: ");
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) {
                nurseToUpdate.setName(name);
            }
//
            System.out.print("Age: ");
            String ageStr = sc.nextLine().trim();
            if (!ageStr.isEmpty()) {
                int age = 0;
                while (age == 0) {
                    try {
                        age = Integer.parseInt(ageStr);
                        if (age <= 0) {
                            System.out.println("Age must > 0.");
                            age = 0;
                            System.out.print("Age: ");
                            ageStr = sc.nextLine().trim();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid age.");
                    }
                }
                nurseToUpdate.setAge(age);
            }

            System.out.print("Gender: ");
            String gender = sc.nextLine().trim();
            if (!gender.isEmpty()) {
                nurseToUpdate.setGender(gender);
            }

            System.out.print("Address: ");
            String address = sc.nextLine().trim();
            if (!address.isEmpty()) {
                nurseToUpdate.setAddress(address);
            }

            System.out.print("Phone: ");
            String phone = sc.nextLine().trim();
            if (phone.isEmpty()) {

            } else {
                while (!Validation.isPhoneValid(phone)) {
                    System.out.println("Invalid phone number format.");
                    System.out.print("Phone: ");
                    phone = sc.nextLine().trim();
                }
                nurseToUpdate.setPhone(phone);
            }

            System.out.print("Department: ");
            String department = sc.nextLine().trim();
            if (phone.isEmpty()) {

            } else {
                while (department.isEmpty() || department.length() < 3 || department.length() > 50) {
                    System.out.println("Department must be between 3 and 50 characters.");
                    System.out.print("Department: ");
                    department = sc.nextLine().trim();
                }
                nurseToUpdate.setDepartment(department);
            }

            System.out.print("Shift: ");
            String shift = sc.nextLine().trim();
            if (!shift.isEmpty()) {
                nurseToUpdate.setShift(shift);
            }

            System.out.print("Salary: ");
            String salaryStr = sc.nextLine().trim();
            if (!salaryStr.isEmpty()) {
                try {
                    double salary = Double.parseDouble(salaryStr);
                    nurseToUpdate.setSalary(salary);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid salary value.");
                }
            }

            System.out.println("Nurse details updated successfully!");
        } catch (Exception e) {
            System.out.println("Nurse details updated failed!");
        }
    }

    public void deleteNurse() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Staff ID of the nurse to be deleted: ");
        String staffID = sc.nextLine();
        if (!nurses.containsKey(staffID)) {
            System.out.println("The nurse does not exist.");
            return;
        }
        Nurse nurse = nurses.get(staffID);
        boolean canDelete = true;
        for (Patient p : patients.values()) {
            if (p.getNurseAssigned().equals(staffID)) {
                System.out.println("The nurse cannot be deleted as she has a task (look after a patient).");
                canDelete = false;
                break;
            }
        }
        if (canDelete) {
            System.out.println("Are you sure you want to delete the nurse? (y/n)");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                nurses.remove(staffID);
                System.out.println("Nurse deleted successfully.");
            } else {
                System.out.println("Nurse not deleted.");
            }
        }
    }

    public void createPatient(String id, String name, int age, String gender, String address, String phone, String diagnosis, Date admissionDate, Date dischargeDate, Nurse nurseAssigned, int nurseCount) {
        Patient patient = new Patient(id, name, age, gender, address, phone, diagnosis, admissionDate, dischargeDate, nurseAssigned, nurseCount);
        patients.put(id, patient);
        System.out.println("Patient added successfully!");
    }

    public void createPatient() {
        System.out.println("Please input the following information for the patient:");
        String id = "";
        while (id.equals("")) {
            try {
                System.out.print("Patient ID: ");
                id = sc.nextLine();
                if (id.equals("")) {
                    Validation.isEmpty(id, "Patient ID");
                }
                if (isIDUnique(id)) {
                } else {
                    System.out.println("Patient ID already exists.");
                    id = "";
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid integer for patient ID.");
            }
        }
        String name = "";
        while (name.equals("")) {
            System.out.print("Name: ");
            name = sc.nextLine();
            Validation.isEmpty(name, "Name");
        }
        int age = 0;
        while (age == 0) {
            String ageStr = "";
            while (ageStr.equals("")) {
                System.out.print("Age: ");
                ageStr = sc.nextLine();
                Validation.isEmpty(ageStr, "Age");
            }
            try {
                age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    System.out.println("Age must > 0.");
                    age = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid age.");
            }
        }
        String gender = "";
        while (gender.equals("")) {
            System.out.print("Gender: ");
            gender = sc.nextLine();
            Validation.isEmpty(gender, "Gender");
        }
        String address = "";
        while (address.equals("")) {
            System.out.print("Address: ");
            address = sc.nextLine();
            Validation.isEmpty(address, "Address");
        }
        String phone = "";
        while (phone.equals("")) {
            System.out.print("Phone: ");
            phone = sc.nextLine();
            if (phone.equals("")) {
                Validation.isEmpty(phone, "Phone");
            }
            if (!Validation.isPhoneValid(phone)) {
                System.out.println("Invalid phone format. Please enter a valid phone number.");
                phone = "";
            }
        }
        String diagnosis = "";
        while (diagnosis.equals("")) {
            System.out.print("Diagnosis: ");
            diagnosis = sc.nextLine();
            Validation.isEmpty(diagnosis, "Diagnosis");
        }
        Date admissionDate = null;
        System.out.print("Admission Date (dd/MM/yyyy): ");
        while (admissionDate == null) {
            try {
                String date = "";
                date = sc.next().trim();
                admissionDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                System.out.println(admissionDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a date in dd/MM/YYYY format.");
            }
        }
        Date dischargeDate = null;
        System.out.print("Discharge Date (dd/MM/yyyy): ");
        while (dischargeDate == null) {
            try {
                String date = "";
                date = sc.next().trim();
                dischargeDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                System.out.println(dischargeDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a date in dd/MM/YYYY format.");
            }
        }
        sc.nextLine();
        int nurseCount = 0;
        Nurse nurseAssigned = null;
        while (nurseAssigned == null || nurseCount < 2) {
            System.out.print("2 nurses assigned (by Staff ID): ");
            String aStaffID = sc.nextLine();

            if (nurses.containsKey(aStaffID)) {
                nurseAssigned = nurses.get(aStaffID);

                if (nurseAssigned.getPatientCount() < 2) {
                    nurseAssigned.iPatient();
                    nurseCount++;
                } else {
                    System.out.println("Maximum patient count reached");
                }
            } else {
                System.out.println("Invalid nurse ID. Please enter a valid nurse ID.");
            }
        }
        Patient patient = new Patient(id, name, age, gender, address, phone, diagnosis, admissionDate, dischargeDate, nurseAssigned, nurseCount);
        patients.put(id, patient);
        System.out.println("Patient added successfully!");
    }

    public void displayPatients() {
        System.out.println("Please input the start and end dates to view the list of patients.");
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (startDate == null) {
            try {
                System.out.print("Start date (dd/MM/yyyy): ");
                startDate = sdf.parse(sc.nextLine().trim());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }

        while (endDate == null || endDate.before(startDate)) {
            try {
                System.out.print("End date (dd/MM/yyyy): ");
                endDate = sdf.parse(sc.nextLine().trim());
                if (endDate.before(startDate)) {
                    System.out.println("End date should not be earlier than start date.");
                    endDate = null;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }

        System.out.println("LIST OF PATIENTS");
        System.out.printf("%-8s%-11s%-20s%-15s%-15s%s%n", "No.", "Patient Id", "Admission Date", "Full name", "Phone", "Diagnosis");
        int count = 1;
        for (Patient patient : patients.values()) {
            if (patient.getAdmissionDate().after(startDate) && patient.getAdmissionDate().before(endDate)) {
                System.out.printf("%-8d%-11s%-20s%-15s%-15s%s%n", count, patient.getId(), sdf.format(patient.getAdmissionDate()), patient.getName(), patient.getPhone(), patient.getDiagnosis());
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No patients found in the given date range.");
        }
    }

    // Save nurses to binary file
    public void saveNursesToFile() {
        try {
            String fileName = "Save" + ++saveCount;
            String filePath = "D:\\Code\\Lab211\\Hospital Management\\src\\Data\\" + fileName + ".dat";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(nurses);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Nurses data saved to " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to save nurses data: " + e.getMessage());
        }
    }

    // Save patients to binary file
    public void savePatientsToFile() {
        try {
            String fileName = "Save" + ++saveCount;
            String filePath = "D:\\Code\\Lab211\\Hospital Management\\src\\Data\\" + fileName + ".dat";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(patients);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Patients data saved to " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to save patients data: " + e.getMessage());
        }
    }

    // Load nurses from binary file
    public HashMap<String, Nurse> loadNursesFromFile(String fileName) {
        HashMap<String, Nurse> loadedNurses = null;
        try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            loadedNurses = (HashMap<String, Nurse>) objectIn.readObject();
            System.out.println("Nurses loaded from " + fileName + " successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading nurses from file: " + e.getMessage());
        }
        return loadedNurses;
    }

    // Load patients from binary file
    public HashMap<String, Patient> loadPatientsFromFile(String fileName) {
        HashMap<String, Patient> loadedPatients = null;
        try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            loadedPatients = (HashMap<String, Patient>) objectIn.readObject();
            System.out.println("Patients loaded from " + fileName + " successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading patients from file: " + e.getMessage());
        }
        return loadedPatients;
    }

//
//  
//
    public boolean isStaffIDUnique(String staffID) {
        for (Nurse nurse : nurses.values()) {
            if (nurse.getStaffID().equals(staffID)) {
                return false; // staffID is already taken
            }
        }
        return true; // staffID is unique
    }

    public boolean isIDUnique(String id) {
        for (Patient patient : patients.values()) {
            if (patient.getId().equals(id)) {
                return false; // staffID is already taken
            }
        }
        return true; // staffID is unique
    }
}
