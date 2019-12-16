package learn.yh.code.printer;

import learn.yh.vo.ApiImplicitParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class RequestPrinter implements ApiImplicitParamListPrinter{

    private String requestTemplate = "@RequestParam(value = \"%s\", required = %s) %s %s";
    private String requestDateTemplate = "@RequestParam(value = \"%s\", required = %s) @DateTimeFormat(pattern = \"%s\") Date %s";


    @Override
    public Optional<String> paramListToString(List<ApiImplicitParam> apiImplicitParamList) {
        List<String> requestParamStringList = new ArrayList<>();
        if (apiImplicitParamList.isEmpty()){
            System.out.println("apiImplicitParamList is empty");
            return Optional.empty();
        }else {
            apiImplicitParamList.forEach(apiImplicitParam -> {
                String result = "";
                if ("Date".equals(apiImplicitParam.getDataType())){
                    result = String.format(requestDateTemplate,apiImplicitParam.getName(),apiImplicitParam.isRequired(),apiImplicitParam.getDateTimeFormat(),apiImplicitParam.getName());
                }else {
                    result =  String.format(requestTemplate,apiImplicitParam.getName(),apiImplicitParam.isRequired(),apiImplicitParam.getDataType(),apiImplicitParam.getName());
                }
                requestParamStringList.add(result);
            });
        }

        return Optional.ofNullable(String.join(",\n", requestParamStringList));
    }

}
