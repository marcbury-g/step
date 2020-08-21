package com.example.step;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class TestMyServlet {
    @Mock
    Database mockDatabase;
    @Mock
    HttpServletRequest mockRequest; 
    @Mock
    HttpServletResponse mockResponse;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    // Test method naming:
    // Each test should explicitly name not only the method being tested, 
    // but also the expected outcome and sometimes specific details of the state or input being tested.
    public void testMyServlet_doGet_WithValueRequestParam_ReturnsParamValue() throws Exception {
        // Arrange
        // Setting up details of the external world specific to the situation under test
        when(mockRequest.getParameter("value")).thenReturn("my_value");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockResponse.getWriter()).thenReturn(writer);

        // Act
        // Make the actual call(s) to the class under test to trigger the behavior that is being tested.
        new MyServlet(mockDatabase).doGet(mockRequest, mockResponse);

        // Assert
        // Make assertions on the return values collected and to verify any interactions with mock 
        // objects or changes in visible state.
        verify(mockRequest, atLeast(1)).getParameter("value"); // only if you want to verify value was called...
        verify(mockDatabase, never()).getValue();
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("my_value"));
    }

    @Test
    // Test method naming:
    // Each test should explicitly name not only the method being tested, 
    // but also the expected outcome and sometimes specific details of the state or input being tested.
    public void testMyServlet_doGet_WithMissingRequestParam_ReturnsDatabaseValue() throws Exception {
        // Arrange
        // Setting up details of the external world specific to the situation under test
        when(mockDatabase.getValue()).thenReturn("database_value");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockResponse.getWriter()).thenReturn(writer);

        // Act
        // Make the actual call(s) to the class under test to trigger the behavior that is being tested.
        new MyServlet(mockDatabase).doGet(mockRequest, mockResponse);

        // Assert
        // Make assertions on the return values collected and to verify any interactions with mock 
        // objects or changes in visible state.
        verify(mockDatabase, atLeast(1)).getValue();
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("database_value"));
    }
}