package com.example.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {

    TqsStack<Integer> stack;

    @BeforeEach
    void setUp() {
         stack = new TqsStack<>();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    void popOnEmptyThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    void peekOnEmptyThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    void zeroSizeOnConstruction() {
        assertEquals(0, stack.size());
    }

    @ParameterizedTest(name="{0} Size After Pushing {0} Elements")
    @CsvSource({
            "1",
            "5",
            "9",
            "83"
    })
    void nSizeAfterPushingNElements(int size) {
        for(int i = 0; i < size; i++){
            stack.push(i * 9 + 10);
        }
        assertEquals(size, stack.size());
    }

    @ParameterizedTest(name="Pushing {0} and peeking {0} returns {0}")
    @CsvSource({
            "3",
            "11",
            "-12",
            "0"
    })
    void peekingXReturnsXAndSizeSame(int value) {
        stack.push(value);
        assertEquals(value, stack.peek());
        assertEquals(1, stack.size());
    }

    @ParameterizedTest(name="Pushing {0} and popping {0} returns {0}")
    @CsvSource({
            "3",
            "11",
            "-12",
            "0"
    })
    void pushingXAndPoppingXReturnsX(int value) {
        stack.push(value);
        assertEquals(value, stack.pop());
    }

    @Test
    void isEmptyOnConstruction() {
        assertTrue(stack.isEmpty());
    }
}