package com.hospital.util;
public class Query {
    
    public static String insertPatients="INSERT INTO patients ( Name, Age, Gender, Illness) VALUES(?,?,?,?);";
    public static String insertDoctors="INSERT INTO doctors (Name, Age, Gender, Speciality) VALUES (?,?,?,?);";
    public static String insertReceptionists = "INSERT INTO receptionists (name, phone_number, email, address) VALUES (?,?,?,?);";
    public static String insertAppointments= "INSERT INTO appointments ( DoctorID, DoctorName, PatientID, PatientName) VALUES (?,?,?,?);"; 
    public static String insertBilling= "INSERT INTO billings(PatientID, TotalAmount, PatientName ) VALUES(?,?,?)";
    public static String displayDoctors= "Select * from doctors;";   
    public static String displayPatients = "Select * from patients;";
    public static String displayReceptionists = "SELECT * from receptionists;";
    public static String displayAppointments= "SELECT `AppointmentID`, `DoctorID`, `DoctorName`, `PatientID`, `PatientName`, `Date & Time` FROM appointments;";
    public static String displayBills = "SELECT `BillNo`, `PatientID`, `PatientName`, `Date & Time`, `TotalAmount`, `Status` from billings;";
    public static String signup = "INSERT into users (username, password) VALUES (?,?)";
    public static String login = "SELECT * from users where username = ? AND password = ? AND role = ? ";
    public static String peekUserPass = "SELECT * from users where username = ? AND password = ?";
    public static String deleteDoctor = "DELETE from doctors where DoctorID= ? AND Name = ? ";
    public static String deletePatient = "DELETE from patients WHERE PatientID = ? AND Name = ?;";
    public static String deleteReceptionist = "DELETE from receptionists WHERE receptionist_id = ? AND name = ?;";
    public static String deleteAppointment="DELETE from appointments WHERE AppointmentID = ? AND PatientID = ? AND DoctorID = ? ;";
    public static String checkPatientId = "Select * from patients where PatientID = ? AND Name = ?; ";
}
