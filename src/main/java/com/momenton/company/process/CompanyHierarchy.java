package com.momenton.company.process;

import com.momenton.company.exception.HierarchyException;
import com.momenton.company.model.Employee;
import com.momenton.company.util.Node;
import com.momenton.company.util.Tree;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.momenton.company.exception.HierarchyException.INVALID_MANAGER;
import static com.momenton.company.exception.HierarchyException.INVALID_NUMBER_CEOS;

public class CompanyHierarchy {
    private List<Employee> employeeList;

    public CompanyHierarchy(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Tree<Employee> getHierarchy() throws HierarchyException {
        List<Employee> employees = new ArrayList<>(employeeList);
        Tree<Employee> tree = new Tree<>(findCEO(employees));
        insertSupervisedInTree(tree.getRoot(), employees);
        if (!employees.isEmpty()) {
            throw new HierarchyException(INVALID_MANAGER);
        }
        return tree;
    }

    private Employee findCEO(List<Employee> employees) throws HierarchyException {
        List<Employee> possibleCEOs = findSupervisedBy(employees, null);
        if (CollectionUtils.isEmpty(possibleCEOs) || possibleCEOs.size() > 1) {
            throw new HierarchyException(INVALID_NUMBER_CEOS);
        }
        employees.removeAll(possibleCEOs);
        return possibleCEOs.get(0);
    }

    private void insertSupervisedInTree(Node<Employee> employeeNode, List<Employee> employees) {
        List<Employee> supervisedBy = findSupervisedBy(employees, employeeNode.getValue().getId());
        supervisedBy.forEach(employeeNode::addChild);
        employees.removeAll(supervisedBy);
        if (!employeeNode.getChildren().isEmpty() && !employees.isEmpty()) {
            employeeNode.getChildren().forEach(childNode -> insertSupervisedInTree(childNode, employees));
        }
    }

    private List<Employee> findSupervisedBy(List<Employee> listEmployees, Integer managerId) {
        return listEmployees.stream().filter(employee -> hasManager(employee, managerId)).collect(Collectors.toList());
    }

    private boolean hasManager(Employee employee, Integer managerId) {
        return (employee.getManagerId() == null && managerId == null) ||  employee.getManagerId().equals(managerId);
    }
}
