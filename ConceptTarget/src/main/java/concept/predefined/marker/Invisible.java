package concept.predefined.marker;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Methods with this annotation wont show their value in html tables
 * 
 * @author Manuel
 */
@Retention(RUNTIME)
@Target(METHOD)
@Inherited
public @interface Invisible {

}
