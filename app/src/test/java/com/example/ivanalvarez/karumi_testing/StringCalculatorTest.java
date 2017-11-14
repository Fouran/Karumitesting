package com.example.ivanalvarez.karumi_testing;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by ivanalvarez on 13/11/17.
 */

class NegativeNumberException extends Exception{

    ArrayList<String> negatives = new ArrayList<>();

    public NegativeNumberException(ArrayList<String> negativeNumbers) {
        negatives = negativeNumbers;
    }

}

class StringCalculator {
    final private static String SEPARATORS_USED_TO_SPLIT = "[,\\n]";

    public int sum (String param) throws NegativeNumberException{
        String[] possibleNumbers = param.split(SEPARATORS_USED_TO_SPLIT);
        int sum = 0;
        ArrayList<String> negativeNumbers = new ArrayList<>();

        for (String s:possibleNumbers){
            try{
                int number = Integer.parseInt(s);
                if (number > -1)
                    sum += number;
                else
                    negativeNumbers.add(s);

            }catch (NumberFormatException ignore){}

        }

        if (negativeNumbers.size() == 0)
            return sum;
        else{
            throw new NegativeNumberException(negativeNumbers);
        }
    }
}


public class StringCalculatorTest {

    @Test
    public void test_empty_string_return_0() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        int sum = stringCalculator.sum("");

        assertEquals(0, sum);
    }

    @Test
    public void test_return_number_passed_as_param() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        int sum = stringCalculator.sum("1");

        assertEquals(1, sum);
    }

    @Test
    public void test_return_sum_of_two_numbers_separated_with_comma() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        int sum = stringCalculator.sum("1,2");

        assertEquals(3, sum);
    }

    @Test
    public void test_return_sum_of_four_numbers_separated_with_comma() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        int sum = stringCalculator.sum("1,2,3,4");

        assertEquals(10, sum);
    }

    @Test
    public void test_return_sum_of_two_numbers_separated_with_return() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        int sum = stringCalculator.sum("1,2\n3");

        assertEquals(6, sum);
    }

    @Test
    public void test_return_error_when_negative_numbers_is_found_and_array_with_those_numbers() throws Exception {
        StringCalculator stringCalculator = new StringCalculator();
        int sum = stringCalculator.sum("1,2,-3,4");

    }
}
