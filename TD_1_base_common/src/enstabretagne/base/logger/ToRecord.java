/*
 *
 */
package enstabretagne.base.logger;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// TODO: Auto-generated Javadoc

/**
 * The Interface ToRecord.
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface ToRecord {

    /**
     * Name.
     *
     * @return the string
     */
    public String name();
}
