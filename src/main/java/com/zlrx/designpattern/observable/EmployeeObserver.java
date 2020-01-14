package com.zlrx.designpattern.observable;

public class EmployeeObserver implements Observer {

    public EmployeeObserver(Subject subject) {
        subject.register(this);
    }

    @Override
    public void updateSalary(int salary) {
        System.out.println("Max salary is updated to: " + salary);
    }
}
