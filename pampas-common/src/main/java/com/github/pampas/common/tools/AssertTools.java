package com.github.pampas.common.tools;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-05-22
 * Time: 11:36
 */
public class AssertTools {

    /**
     * Assert that a string is not empty, it must not be {@code null} and it must not be empty.
     *
     * @param string  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null} or the underlying string is empty
     */
    public static void notEmpty(String string, String message) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an object is not {@code null} .
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an array has elements; that is, it must not be {@code null} and must have at least one element.
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array is {@code null} or has no elements
     */
    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * collection is not empty
     *
     * @param coll    the coll
     * @param message the message
     */
    public static void notEmpty(Collection coll, String message) {
        if (coll == null || coll.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Map is not empty
     *
     * @param coll    the coll
     * @param message the message
     */
    public static void notEmpty(Map coll, String message) {
        if (coll == null || coll.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an array has elements; that is, it must not be {@code null} and must have at least one element.
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array is {@code null} or has no elements
     */
    public static void notEmpty(int[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that an array has no null elements.
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }


    /**
     * Assert that a {@link Collection} has no null elements.
     *
     * @param c       the collection to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the {@link Collection} contains a {@code null} element
     */
    public static void noNullElements(Collection<?> c, String message) {
        if (c != null) {
            for (Object element : c) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    /**
     * Assert that {@code value} is {@literal true}.
     *
     * @param value   the value to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array contains a {@code null} element
     */
    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance, but not involving any parameters to the
     * calling method.
     *
     * @param condition a boolean expression
     * @param message   the exception message to use if the assertion fails
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void assertState(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
