package com.momenton.company.process;

import com.momenton.company.exception.HierarchyException;
import com.momenton.company.model.Employee;
import com.momenton.company.util.Node;
import com.momenton.company.util.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.momenton.company.exception.HierarchyException.INVALID_MANAGER;
import static com.momenton.company.exception.HierarchyException.INVALID_NUMBER_CEOS;
import static org.junit.jupiter.api.Assertions.*;

class CompanyHierarchyTest {

    @Test
    void testGetHierarchyWhenNoEmployee() {
        CompanyHierarchy companyHierarchy = new CompanyHierarchy(Collections.EMPTY_LIST);
        Exception exception = Assertions.assertThrows(HierarchyException.class,  () -> companyHierarchy.getHierarchy());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(INVALID_NUMBER_CEOS));
    }

    @Test
    void testGetHierarchyWhenOneCEOEmployee() throws Exception {
        CompanyHierarchy companyHierarchy = new CompanyHierarchy(Arrays.asList(new Employee("Test", 1, null)));
        Tree<Employee> hierarchy = companyHierarchy.getHierarchy();
        assertEquals(1, hierarchy.getRoot().getValue().getId());
        assertTrue(hierarchy.getRoot().getChildren().isEmpty());
    }

    @Test
    void testGetHierarchyWhenInvalidManager() {
        CompanyHierarchy companyHierarchy = new CompanyHierarchy((Arrays.asList(
                new Employee("Charles",3,  5),
                new Employee("Alan", 2, 1),
                new Employee("John", 1, null))));
        Exception exception = Assertions.assertThrows(HierarchyException.class,  () -> companyHierarchy.getHierarchy());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(INVALID_MANAGER));
    }

    @Test
    void testGetHierarchyWhenManyCEOs() {
        CompanyHierarchy companyHierarchy = new CompanyHierarchy((Arrays.asList(
                new Employee("Charles",3,  1),
                new Employee("Alan", 2, null),
                new Employee("John", 1, null))));
        Exception exception = Assertions.assertThrows(HierarchyException.class,  () -> companyHierarchy.getHierarchy());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(INVALID_NUMBER_CEOS));
    }

    @Test
    void testGetHierarchyWhenValid() throws Exception {
        CompanyHierarchy companyHierarchy = new CompanyHierarchy((Arrays.asList(
                new Employee("Alan", 100, 150),
                new Employee("Martin", 220, 100),
                new Employee("Jamie", 150, null),
                new Employee("Alex", 275, 100),
                new Employee("Steve", 400, 150),
                new Employee("David", 190, 400)
                )));
        Tree<Employee> hierarchy = companyHierarchy.getHierarchy();
        assertEquals("Jamie", hierarchy.getRoot().getValue().getName());
        List<Node<Employee>> supervisedByJamie = hierarchy.getRoot().getChildren();
        assertEquals(2, supervisedByJamie.size());
        assertEquals("Alan", supervisedByJamie.get(0).getValue().getName());
        assertEquals("Steve", supervisedByJamie.get(1).getValue().getName());

        List<Node<Employee>> supervisedByAlan = supervisedByJamie.get(0).getChildren();
        assertEquals(2, supervisedByAlan.size());
        assertEquals("Martin", supervisedByAlan.get(0).getValue().getName());
        assertEquals("Alex", supervisedByAlan.get(1).getValue().getName());
        assertTrue(supervisedByAlan.get(0).getChildren().isEmpty());
        assertTrue(supervisedByAlan.get(1).getChildren().isEmpty());

        List<Node<Employee>> supervisedBySteve = supervisedByJamie.get(1).getChildren();
        assertEquals(1, supervisedBySteve.size());
        assertEquals("David", supervisedBySteve.get(0).getValue().getName());
        assertTrue(supervisedBySteve.get(0).getChildren().isEmpty());
    }

}