package com.zlrx.designpattern.observable;

//The subject object maintain a list of dependent objects and notifies them if it state is changed
public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        EmployeeObserver employee = new EmployeeObserver(company);
        ManagerObserver manager = new ManagerObserver(company);
        company.updateMaxSalary(1300);
        company.unregister(employee);
        company.updateMaxSalary(1600);
        company.register(employee);

        Runnable changeSalary = new ChangeSalay(company);
        Thread thread = new Thread(changeSalary);
        thread.start();
    }


}
