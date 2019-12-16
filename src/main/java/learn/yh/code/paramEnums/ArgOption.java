package learn.yh.code.paramEnums;

import org.apache.commons.lang3.StringUtils;

public enum  ArgOption {
    table_name("tb"),
    names("names")
    ;
    private String code;

    ArgOption(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ArgOption getByCode(String code){
        if (StringUtils.isEmpty(code)){
            return null;
        }
        for (ArgOption operation : ArgOption.values()){
            if (code.equals(operation.getCode())){
                return operation;
            }
        }
        return null;
    }
}
