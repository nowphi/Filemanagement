package header;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface FilePosToMdlFieldRelation {
	String mdlType() default "";
	int mdlField() default -1;
	String srcTbl() default "";
	int filePos() default -1;
}
