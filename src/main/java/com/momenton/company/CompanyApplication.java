package com.momenton.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.momenton.company.exception.HierarchyException;
import com.momenton.company.model.Employee;
import com.momenton.company.process.CompanyHierarchy;
import com.momenton.company.util.TreePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
public class CompanyApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyApplication.class);

	@Autowired
	private Gson gson;

	@Value("classpath:company.json")
	private Resource resource;

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
			Type listEmployeeObject = new TypeToken<ArrayList<Employee>>() {}.getType();
			List<Employee> employees = gson.fromJson(reader, listEmployeeObject);

			new TreePrinter(new CompanyHierarchy(employees).getHierarchy()).print(System.out);

		} catch (IOException e) {
			LOG.error("Error reading file", e);
		} catch (HierarchyException e) {
			LOG.error("Error in the hierarchy", e);
		}
	}
}
