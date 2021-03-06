package com.blazemeter.api.exception;

import org.junit.Test;

import static org.junit.Assert.*;

public class InterruptRuntimeExceptionTest {
    @Test
    public void test() throws Exception {
        InterruptRuntimeException exception = new InterruptRuntimeException();
        assertNotNull(exception);
        exception = new InterruptRuntimeException("msg");
        assertEquals("msg", exception.getMessage());
        exception = new InterruptRuntimeException("msg1", new RuntimeException("nested exception"));
        assertEquals("nested exception", exception.getCause().getMessage());
    }
}