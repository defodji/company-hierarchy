package com.momenton.company.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testSetId() {
        Employee employee = new Employee();
        employee.setId(1);
        assertEquals(1, employee.getId());
    }

    @Test
    void testSetName() {
        Employee employee = new Employee();
        employee.setName("John");
        assertEquals("John", employee.getName());
    }

    @Test
    void testSetManagerId() {
        Employee employee = new Employee();
        employee.setManagerId(1);
        assertEquals(1, employee.getManagerId());
    }

    @Test
    void testEquals() {
        Employee emp1 = new Employee("John", 1, null);
        Employee emp2 = new Employee("John", 1, 3);
        assertTrue(emp1.equals(emp2));
        assertEquals(emp1.hashCode(), emp1.hashCode());
    }
    @Test
    void testNotEquals() {
        Employee emp1 = new Employee("John", 1, 3);
        Employee emp2 = new Employee("John", 2, 3);
        assertFalse(emp1.equals(emp2));
    }

    @Test
    void testToString() {
        Employee employee = new Employee("John", 1, 3);
        assertEquals("John", employee.toString());
    }
}