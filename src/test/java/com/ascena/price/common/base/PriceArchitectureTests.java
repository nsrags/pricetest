package com.ascena.price.common.base;

import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class PriceArchitectureTests {
	JavaClasses importedClasses = new ClassFileImporter().importPackages("com.ascena.price");

	@Test
	public void service_rules() {
		classes().that().areAnnotatedWith(Service.class).or().haveNameMatching(".*Service").should()
				.resideInAnyPackage("..service..").check(importedClasses);
	}

	@Test
	public void dao_rules() {
		classes().that().resideInAPackage("..dao").should().haveSimpleNameEndingWith("DAO").check(importedClasses);
	}
	
	@Test
	public void config_rules() {
		classes().that().resideInAPackage("..config").should().haveSimpleNameEndingWith("Config")
		.check(importedClasses);
	}

	@Test
	public void controller_rules() {
		classes().that().areAnnotatedWith(RestController.class).or().haveNameMatching(".*Controller").should()
				.resideInAnyPackage("..controller..").check(importedClasses);
	}

	@Test
	public void util_rules() {
		classes().that().resideInAnyPackage("..util..").should().haveNameMatching(".*Utils").check(importedClasses);
	}

	@Test
	public void layer_rules() {
		layeredArchitecture().layer("Controller").definedBy("..controller..").layer("Service").definedBy("..service..")
				.layer("dao").definedBy("..dao..")

				.whereLayer("Controller").mayNotBeAccessedByAnyLayer().whereLayer("Service")
				.mayOnlyBeAccessedByLayers("Controller").whereLayer("dao").mayOnlyBeAccessedByLayers("Service");
	}

}
