package com.zlrx.designpattern.observable;

public class ManagerObserver implements Observer {

    public ManagerObserver(Subject subject) {
        subject.register(this);
    }

    @Override
    public void updateSalary(int salary) {
        System.out.println("All our employee's salary is raised to: " + salary);
    }

}
