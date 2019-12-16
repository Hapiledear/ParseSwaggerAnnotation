package learn.yh.code.printer;

import learn.yh.vo.ApiImplicitParam;
import lombok.Data;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ControllerPrinter implements ApiImplicitParamListPrinter {

    private String queryTemplate = "if (%s != null) {\n" +
            "            criteria.and%sEqualTo(%s);\n" +
            "        }\n";


    @Override
    public Optional<String> paramListToString(List<ApiImplicitParam> apiImplicitParamList) {
        List<String> controllerParamStringList = new ArrayList<>();
        apiImplicitParamList.forEach(apiImplicitParam -> {
            String result =  String.format(queryTemplate,apiImplicitParam.getName(), WordUtils.capitalize(apiImplicitParam.getName()),apiImplicitParam.getName());
            controllerParamStringList.add(result);
        });

        return Optional.ofNullable(String.join("\n", controllerParamStringList));
    }

}
