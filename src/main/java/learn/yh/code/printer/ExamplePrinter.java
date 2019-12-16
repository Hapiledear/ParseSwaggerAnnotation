package learn.yh.code.printer;

import learn.yh.code.paramEnums.ArgOption;
import learn.yh.code.utils.MyWordUtils;
import learn.yh.vo.ApiImplicitParam;
import lombok.Data;
import org.apache.commons.lang3.text.WordUtils;

import java.util.*;

@Data
public class ExamplePrinter implements ApiImplicitParamListPrinter {

    private String tableName;
    private List<String> specList;

    private String tempStr = " public Criteria and$CodeEqualTo($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` =\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeNotEqualTo($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` <>\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeGreaterThan($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` >\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeGreaterThanOrEqualTo($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` >=\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeLessThan($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` <\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeLessThanOrEqualTo($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` <=\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeLike($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` like\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeNotLike($type value) {\n" +
            "            addCriterion(\"$table.`$co_de` not like\", value, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeIn(List<$type> values) {\n" +
            "            addCriterion(\"$table.`$co_de` in\", values, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeNotIn(List<$type> values) {\n" +
            "            addCriterion(\"$table.`$co_de` not in\", values, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeBetween($type value1, $type value2) {\n" +
            "            addCriterion(\"$table.`$co_de` between\", value1, value2, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }\n" +
            "\n" +
            "        public Criteria and$CodeNotBetween($type value1, $type value2) {\n" +
            "            addCriterion(\"$table.`$co_de` not between\", value1, value2, \"$Code\");\n" +
            "            return (Criteria) this;\n" +
            "        }";

    @Override
    public void init(Map<String,String> optionArgMap) {
        this.tableName = optionArgMap.get(ArgOption.table_name.getCode());
        this.specList = Arrays.asList(optionArgMap.get(ArgOption.names.getCode()).split(","));
    }

    @Override
    public Optional<String> paramListToString(List<ApiImplicitParam> apiImplicitParamList) {
        List<String> paramStringList = new ArrayList<>();
        apiImplicitParamList.forEach(apiImplicitParam -> {
            if (!specList.isEmpty() && !specList.contains(apiImplicitParam.getName())){
                return;
            }
            tempStr= tempStr.replaceAll("\\$type",apiImplicitParam.getDataType());
            tempStr= tempStr.replaceAll("\\$table",tableName);
            tempStr= tempStr.replaceAll("\\$Code",WordUtils.capitalize(apiImplicitParam.getName()));
            String result = tempStr.replaceAll("\\$co_de", MyWordUtils.humpToLine(apiImplicitParam.getName()));
             paramStringList.add(result);
        });

        return Optional.ofNullable(String.join("\n", paramStringList));
    }
}
