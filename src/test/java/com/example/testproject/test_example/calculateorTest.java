package com.example.testproject.test_example;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class calculateorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void addTest(){
        int actual = calculator.add(1,2);
        int expected =3; //기대값
        assertThat(actual,is(expected));
    }

    @Test
    public void multipleTest(){
        int actual = calculator.multiple(1,2);
        int expectd =2;
        assertThat(actual,is(expectd));
    }

}
