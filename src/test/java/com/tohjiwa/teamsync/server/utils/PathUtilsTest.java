package com.tohjiwa.teamsync.server.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathUtilsTest {

    @Test
    void getPath_with_Classpath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPath("classpath:certs/private-test.pem");

        assertTrue(result.isPresent());
        assertEquals(this.getClass().getClassLoader().getResource("certs/private-test.pem").getPath(), result.get());
    }

    @Test
    void getPath_with_WithoutSeparator() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPath("certs/private-test.pem");

        assertTrue(result.isPresent());
        assertEquals("certs/private-test.pem", result.get());
    }

    @Test
    void getPath_with_ClasspathWithoutPath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPath("classpath:");

        assertTrue(result.isEmpty());
    }

    @Test
    void getPath_with_FullWindowPath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPath("C:\\certs\\private-test.pem");

        assertTrue(result.isPresent());
        assertEquals("C:\\certs\\private-test.pem", result.get());
    }

    @Test
    void getPath_with_MultiSeparator() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPath("classpath:clazz:certs/private-test.pem");

        assertTrue(result.isPresent());
        assertEquals("classpath:clazz:certs/private-test.pem", result.get());
    }

    @Test
    void isClassPath_with_Classpath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.isClassPath("classpath:certs/private-test.pem");

        assertTrue(result);
    }

    @Test
    void isClassPath_with_ClasspathWithoutPath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.isClassPath("classpath:");

        assertTrue(result);
    }

    @Test
    void isClassPath_with_NotClasspathString() {
        var pathUtils = new PathUtils();
        var result = pathUtils.isClassPath("cc:certs/private-test.pem");

        assertFalse(result);
    }

    @Test
    void isClassPath_with_WithoutSeparator() {
        var pathUtils = new PathUtils();
        var result = pathUtils.isClassPath("certs/private-test.pem");

        assertFalse(result);
    }

    @Test
    void isClassPath_with_MoreThanOneSeparator() {
        var pathUtils = new PathUtils();
        var result = pathUtils.isClassPath("classpath:path:certs/private-test.pem");

        assertFalse(result);
    }

    @Test
    void getPathPure_with_ClassPath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPurePath("classpath:certs/private-test.pem");

        assertTrue(result.isPresent());
        assertEquals("certs/private-test.pem", result.get());
    }

    @Test
    void getPathPure_with_WithoutPath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPurePath("classpath:");

        assertTrue(result.isEmpty());
    }

    @Test
    void getPathPure_with_WithoutClassPath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPurePath("certs/private-test.pem");

        assertTrue(result.isPresent());
        assertEquals("certs/private-test.pem", result.get());
    }

    @Test
    void getPathPure_with_WithSeparatorButNotClasspath() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPurePath("clazz:certs/private-test.pem");

        assertTrue(result.isEmpty());
    }

    @Test
    void getPathPure_with_MoreThanOneSeparator() {
        var pathUtils = new PathUtils();
        var result = pathUtils.getPurePath("classpath:clazz:certs/private-test.pem");

        assertTrue(result.isEmpty());
    }
}