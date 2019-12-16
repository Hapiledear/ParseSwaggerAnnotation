package learn.yh.vo;

import learn.yh.code.paramEnums.DataTypeEnum;
import lombok.Data;

/**
 * Description:
 * Created on:  2019/12/10 下午3:56
 *
 * @author yanghuang3@jd.com
 */
@Data
public class ApiImplicitParam {
    /**
     * Name of the parameter.
     * <p>
     * For proper Swagger functionality, follow these rules when naming your parameters based on paramType:
     * <ol>
     * <li>If {@code paramType} is "path", the name should be the associated section in the path.</li>
     * <li>For all other cases, the name should be the parameter name as your application expects to accept.</li>
     * </ol>
     */
    private String name;

    /**
     * A brief description of the parameter.
     */
    private String value;

    /**
     * Describes the default value for the parameter.
     */
    private String defaultValue;

    /**
     * Limits the acceptable values for this parameter.
     * <p>
     * There are three ways to describe the allowable values:
     * <ol>
     * <li>To set a list of values, provide a comma-separated list.
     * For example: {@code first, second, third}.</li>
     * <li>To set a range of values, start the value with "range", and surrounding by square
     * brackets include the minimum and maximum values, or round brackets for exclusive minimum and maximum values.
     * For example: {@code range[1, 5]}, {@code range(1, 5)}, {@code range[1, 5)}.</li>
     * <li>To set a minimum/maximum value, use the same format for range but use "infinity"
     * or "-infinity" as the second value. For example, {@code range[1, infinity]} means the
     * minimum allowable value of this parameter is 1.</li>
     * </ol>
     */
    private String allowableValues;

    /**
     * Specifies if the parameter is required or not.
     * <p>
     * Path parameters should always be set as required.
     */
    private boolean required = false;

    /**
     * Allows for filtering a parameter from the API documentation.
     * <p>
     * See io.swagger.core.filter.SwaggerSpecFilter for further details.
     */
    private String access;

    /**
     * Specifies whether the parameter can accept multiple values by having multiple occurrences.
     */
    private boolean allowMultiple = false;

    /**
     * The data type of the parameter.
     * <p>
     * This can be the class name or a primitive.
     */
    private String dataType;

    /**
     * The class of the parameter.
     * <p>
     * Overrides {@code dataType} if provided.
     */
    private Class<?> dataTypeClass = Void.class;

    /**
     * The parameter type of the parameter.
     * <p>
     * Valid values are {@code path}, {@code query}, {@code body},
     * {@code header} or {@code form}.
     */
    private String paramType;

    /**
     * a single example for non-body type parameters
     *
     * @return
     * @since 1.5.4
     */
    private String example;


    /**
     * Adds the ability to override the detected type
     *
     * @return
     * @since 1.5.11
     */
    private String type;

    /**
     * Adds the ability to provide a custom format
     *
     * @return
     * @since 1.5.11
     */
    private String format;

    /**
     * Adds the ability to set a format as empty
     *
     * @return
     * @since 1.5.11
     */
    private boolean allowEmptyValue = false;

    /**
     * adds ability to be designated as read only.
     *
     * @since 1.5.11
     */
    private boolean readOnly = false;

    /**
     * adds ability to override collectionFormat with `array` types
     *
     * @since 1.5.11
     */
    private String collectionFormat;

    private String dateTimeFormat;

    public String getDataType() {

        if (!dataTypeClass.equals(Void.class)) {
            return dataTypeClass.getName();
        } else {
            return DataTypeEnum.getJavaNameFromAplimplicitParamsName(dataType);
        }

    }
}
