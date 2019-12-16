package learn.yh.code.paramEnums;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * Created on:  2019/12/9 下午8:35
 *
 * @author yanghuang3@jd.com
 */
public enum Operation {
    help("h","nul"),
    request("r","learn.yh.code.printer.RequestPrinter"),
    query("q","learn.yh.code.printer.ControllerPrinter"),
    example("e","learn.yh.code.printer.ExamplePrinter")
    ;
    private String code;
    private String clazz;

    Operation(String code, String clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public String getCode() {
        return code;
    }

    public String getClazz() {
        return clazz;
    }

    public static Operation getByCode(String code){
        if (StringUtils.isEmpty(code)){
            return help;
        }
        for (Operation operation : Operation.values()){
            if (code.equals(operation.getCode())){
                return operation;
            }
        }
        return help;
    }
}
