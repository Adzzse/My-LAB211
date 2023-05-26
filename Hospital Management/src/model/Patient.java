package model;

import java.util.Date;

//id, name, age, gender, address, phone, diagnosis, admissionDate, dischargeDate, nurseAssigned
public class Patient extends Person {

    private String id;
    private String diagnosis;
    private Date admissionDate;
    private Date dischargeDate;
    private Nurse nurseAssigned;
    private int nurseCount;// ?????

    public Patient(String id, String name, int age, String gender, String address, String phone, String diagnosis, Date admissionDate, Date dischargeDate, Nurse nurseAssigned, int nurseCount) {
        super(name, age, gender, address, phone);
        this.id = id;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
        this.nurseCount = nurseCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Nurse getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(Nurse nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }

    public int getNurseCount() {
        return nurseCount;
    }

    public void setNurseCount(int nurseCount) {
        this.nurseCount = nurseCount;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", Name=" + super.getName()
                + ", Age=" + super.getAge() + ", Gender=" + super.getGender()
                + ", diagnosis=" + diagnosis + ", admissionDate=" + admissionDate
                + ", dischargeDate=" + dischargeDate + ", nurseAssigned=" + nurseAssigned + '}';
    }

}
