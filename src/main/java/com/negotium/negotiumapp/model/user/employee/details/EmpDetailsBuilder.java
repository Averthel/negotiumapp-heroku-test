package com.negotium.negotiumapp.model.user.employee.details;

import com.negotium.negotiumapp.model.user.employee.Employee;

public class EmpDetailsBuilder implements DetailsBuilder {

     int phoneNumber;
     String employeeEmail;
     double holiday;
     double hoursWorked;
     double salary;
     String position;
     String contractType;
     String negotiumRole;
     Employee employee;

    @Override
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setHoliday(double holiday) {
        this.holiday = holiday;
    }

    @Override
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Override
    public void setNegotiumRole(String negotiumRole) {
        this.negotiumRole = negotiumRole;
    }

    @Override
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeDetails getResult() {
        return new EmployeeDetails(this);
    }
}