package com.leon.junit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {TestA.class,TestB.class})
public class SuiteTest {
}
