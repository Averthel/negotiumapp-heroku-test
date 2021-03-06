package com.negotium.negotiumapp.model.user.employee.details;

import com.negotium.negotiumapp.model.user.employee.Employee;

public interface DetailsBuilder {

    void setPhoneNumber(int phoneNumber);
    void setEmployeeEmail(String employeeEmail);
    void setHoliday(double holiday);
    void setHoursWorked(double hoursWorked);
    void setSalary(double salary);
    void setPosition(String position);
    void setContractType(String contractType);
    void setNegotiumRole(String negotiumRole);
    void setEmployee(Employee employee);
}