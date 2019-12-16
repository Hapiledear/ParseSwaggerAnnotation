package learn.yh.code.paramEnums;

public enum  DataTypeEnum {
    STRING("string","String"),
    INT("int","Integer"),
    DATE("date","Date"),

    ;

    private String apilmplicitParamsName;
    private String javaName;

    DataTypeEnum(String apilmplicitParamsName, String javaName) {
        this.apilmplicitParamsName = apilmplicitParamsName;
        this.javaName = javaName;
    }

    public String getApilmplicitParamsName() {
        return apilmplicitParamsName;
    }

    public String getJavaName() {
        return javaName;
    }

    public static String getJavaNameFromAplimplicitParamsName(String aName){
        if (null == aName || aName.isEmpty()){
            return "";
        }else {
            for (DataTypeEnum typeEnum: DataTypeEnum.values()){
                if (typeEnum.getApilmplicitParamsName().equals(aName)){
                    return typeEnum.getJavaName();
                }
            }
            return "";
        }
    }

}
