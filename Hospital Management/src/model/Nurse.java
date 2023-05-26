package model;

//staffID, name, age, gender, address, phone, department, shift, salary.
public class Nurse extends Person {

    private String staffID;
    private String department;
    private String shift;
    private double salary;
    private int patientCount;// ?????

    public Nurse(String staffID, String name, int age, String gender, String address, String phone, String department, String shift, double salary, int patientCount) {
        super(name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
        this.patientCount = patientCount;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    public void iPatient() {
        patientCount++;
    }

    public void dPatient() {
        patientCount--;
    }

    @Override
    public String toString() {
        return "Nurse{" + "StaffID=" + staffID + ", Name=" + super.getName()
                + ", Age=" + super.getAge() + ", Gender=" + super.getGender()
                + ", Address=" + super.getAddress() + ", Phone number=" + super.getPhone()
                + ", Department=" + department + ", Shift=" + shift + ", Salary=" + salary + '}';
    }

}
