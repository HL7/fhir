package org.hl7.fhir.r4.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FluentPathTests.class, NarrativeGeneratorTests.class, /*ShexGeneratorTests.class, StructureMapTests.class, */ TurtleTests.class })
public class AllTests {

}
