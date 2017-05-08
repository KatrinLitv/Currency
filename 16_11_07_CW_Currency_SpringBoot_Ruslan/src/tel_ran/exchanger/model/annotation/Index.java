package tel_ran.exchanger.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
//@Inherited
public @interface Index {
//    String unique() default "false";
    boolean unique() default false;
}
