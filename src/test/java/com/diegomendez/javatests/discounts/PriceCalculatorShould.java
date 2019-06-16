package com.diegomendez.javatests.discounts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceCalculatorShould {

    @Mock
    PriceCalculator priceCalculator;

    @Before
    public void setup(){
        priceCalculator = new PriceCalculator();
    }

    @Test
    public void should_return_0_there_are_not_privces() {

        assertThat(priceCalculator.getTotal(),  is(0.0));
    }

    @Test
    public void total_is_the_sum_of_prices() {

        priceCalculator.addPrice(10.2);
        priceCalculator.addPrice(15.5);

        assertThat(priceCalculator.getTotal(),  is(25.7));
    }

    @Test
    public void apply_discount_to_prices() {
        priceCalculator.addPrice(4.2);
        priceCalculator.addPrice(10.8);

        priceCalculator.setDiscount(50);

        assertThat(priceCalculator.getTotal(),  is(7.5));
    }
}