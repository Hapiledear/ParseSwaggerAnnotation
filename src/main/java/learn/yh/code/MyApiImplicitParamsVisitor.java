package learn.yh.code;

import learn.yh.activity.ApiImplicitParamsBaseVisitor;
import learn.yh.activity.ApiImplicitParamsLexer;
import learn.yh.activity.ApiImplicitParamsParser;
import learn.yh.vo.ApiImplicitParam;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Description:
 * Created on:  2019/12/10 下午3:50
 *
 * @author yanghuang3@jd.com
 */
public class MyApiImplicitParamsVisitor extends ApiImplicitParamsBaseVisitor {

    private List<ApiImplicitParam> apiImplicitParamList;

    private String requestTemplate = "@RequestParam(value = \"%s\", required = %s) %s %s";
    private String requestDateTemplate = "@RequestParam(value = \"%s\", required = %s) @DateTimeFormat(pattern = \"%s\") Date %s";

    public void initAndExecute(String context){
        CodePointCharStream charStream =  CharStreams.fromString(context);
        ApiImplicitParamsLexer lexer = new ApiImplicitParamsLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        ApiImplicitParamsParser paramsParser = new ApiImplicitParamsParser(commonTokenStream);
        ApiImplicitParamsParser.ApiImplicitParamsContext apiImplicitParamsContext = paramsParser.apiImplicitParams();
        this.visitApiImplicitParams(apiImplicitParamsContext);
    }

    public Optional<String> toRequestParamString(){
        List<String> requestParamStringList = new ArrayList<>();

        if (apiImplicitParamList.isEmpty()){
            System.out.println("apiImplicitParamList is empty");
            return Optional.empty();
        }else {
            apiImplicitParamList.forEach(apiImplicitParam -> {
                String result = "";
                if ("Date".equals(apiImplicitParam.getDataType())){
                    result = String.format(requestDateTemplate,apiImplicitParam.getName(),apiImplicitParam.isRequired(),null,apiImplicitParam.getName());
                }else {
                    result =  String.format(requestTemplate,apiImplicitParam.getName(),apiImplicitParam.isRequired(),apiImplicitParam.getDataType(),apiImplicitParam.getName());
                }
              requestParamStringList.add(result);
            });
        }

        return Optional.ofNullable(String.join(",\n", requestParamStringList));
    }


    @Override
    public Object visitApiImplicitParams(ApiImplicitParamsParser.ApiImplicitParamsContext ctx) {
        apiImplicitParamList = new ArrayList<>(ctx.getChildCount());
        ctx.apiImplicitParam().forEach(this::visitApiImplicitParam);
        return null;
    }

    @Override
    public Object visitApiImplicitParam(ApiImplicitParamsParser.ApiImplicitParamContext ctx) {
        ApiImplicitParam apiImplicitParam = new ApiImplicitParam();
        if (!ctx.name().isEmpty()){
            apiImplicitParam.setName(visitName(ctx.name(0)));
        }
        if (!ctx.value().isEmpty()){
            apiImplicitParam.setValue(visitValue(ctx.value(0)));
        }
        if (!ctx.defaultValue().isEmpty()){
            apiImplicitParam.setDefaultValue(visitDefaultValue(ctx.defaultValue(0)));
        }
        if (!ctx.allowableValues().isEmpty()){
            apiImplicitParam.setDefaultValue(visitAllowableValues(ctx.allowableValues(0)));
        }
        if (!ctx.required().isEmpty()){
            apiImplicitParam.setRequired(visitRequired(ctx.required(0)));
        }
        if (!ctx.dataType().isEmpty()){
            apiImplicitParam.setDataType(visitDataType(ctx.dataType(0)));
        }
        if (!ctx.dataTypeClass().isEmpty()){
            apiImplicitParam.setDataTypeClass(visitDataTypeClass(ctx.dataTypeClass(0)));
        }
        if (!ctx.paramType().isEmpty()){
            apiImplicitParam.setParamType(visitParamType(ctx.paramType(0)));
        }
        if (!ctx.example().isEmpty()){
            apiImplicitParam.setExample(visitExample(ctx.example(0)));
        }
        if (!ctx.readOnly().isEmpty()){
            apiImplicitParam.setReadOnly(visitReadOnly(ctx.readOnly(0)));
        }

        apiImplicitParamList.add(apiImplicitParam);
        return null;
    }

    @Override
    public String visitName(ApiImplicitParamsParser.NameContext ctx) {
        return textToString(ctx.text_inline());
    }

    @Override
    public String visitValue(ApiImplicitParamsParser.ValueContext ctx) {
        // todo 解析出timeFormat
        return textToString(ctx.text_inline());
    }

    @Override
    public String visitDefaultValue(ApiImplicitParamsParser.DefaultValueContext ctx) {
        return textToString(ctx.text_inline());
    }

    @Override
    public String visitAllowableValues(ApiImplicitParamsParser.AllowableValuesContext ctx) {
        return textToString(ctx.text_inline());
    }

    @Override
    public Boolean visitRequired(ApiImplicitParamsParser.RequiredContext ctx) {
        Boolean required = false;
        required = ctx.TRUE() != null;
        return required;
    }

    @Override
    public String visitDataType(ApiImplicitParamsParser.DataTypeContext ctx) {
        return textToString(ctx.text_inline());
    }

    @Override
    public Class visitDataTypeClass(ApiImplicitParamsParser.DataTypeClassContext ctx) {
        try {
            Class clazz = Class.forName(ctx.class_name().getText());
            return clazz;
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public String visitParamType(ApiImplicitParamsParser.ParamTypeContext ctx) {
        return textToString(ctx.text_inline());
    }

    @Override
    public String visitExample(ApiImplicitParamsParser.ExampleContext ctx) {
        return textToString(ctx.text_inline());
    }

    @Override
    public Boolean visitReadOnly(ApiImplicitParamsParser.ReadOnlyContext ctx) {
        Boolean readOnly = false;
        readOnly = ctx.TRUE() != null;
        return readOnly;
    }

    private String textToString(ApiImplicitParamsParser.Text_inlineContext context){
        return context.text().stream().map(RuleContext::getText).collect(Collectors.joining());
    }
}
