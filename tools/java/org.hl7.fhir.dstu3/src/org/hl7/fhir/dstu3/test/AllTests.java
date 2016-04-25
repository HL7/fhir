package org.hl7.fhir.dstu3.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BaseDateTimeTypeTest.class, FluentPathTests.class, InstanceValidatorTests.class,
    ShexGeneratorTests.class, StructureMapTests.class })
public class AllTests {

}
