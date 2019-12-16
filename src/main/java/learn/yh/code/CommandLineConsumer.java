package learn.yh.code;

import learn.yh.code.paramEnums.ArgOption;
import learn.yh.code.paramEnums.Operation;
import learn.yh.code.printer.ApiImplicitParamListPrinter;
import learn.yh.code.utils.ClipboardUtil;
import learn.yh.code.utils.ReflactionUtils;
import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandLineConsumer implements Consumer<String> {

    private  CommandLineParser parser = null;
    private  Options options;

    public CommandLineConsumer() {
        options  = new Options();
        options.addOption(Operation.help.getCode(), false, "展示帮助文档");
        options.addOption(Operation.request.getCode(), false, "将 Swagger Annotations 转换为 request代码");
        options.addOption(Operation.query.getCode(), false, "将 Swagger Annotations 转换为 controller中的查询代码");
        options.addOption(Operation.example.getCode(), false, "将 Swagger Annotations 转换为 example中的查询代码.第一个参数指定表名；第二个参数指定Names");

        options.addOption(ArgOption.table_name.getCode(), true, "转换为 example中的查询代码时指定表名");
        options.addOption(ArgOption.names.getCode(), true, "转换为 example中的查询代码指定符合名称的参数进行转换");

        parser = new DefaultParser();
    }

    @Override
    public void accept(String s) {
        String[]args = s.split(" ");
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption(Operation.help.getCode())){
                options.getOptions().forEach(option -> System.out.println("-"+option.getOpt()+"   "+option.getDescription()));
            }else{
                Map<String,String> optionArgMap = getOptionArgMap(cmd.getOptions());

                MyApiImplicitParamsVisitor implicitParamsVisitor = new MyApiImplicitParamsVisitor();
                implicitParamsVisitor.initAndExecute(ClipboardUtil.getSysClipboardText());
                Operation operation =  Operation.getByCode(cmd.getOptions()[0].getOpt());
                ApiImplicitParamListPrinter paramListPrinter =  ReflactionUtils.reflactAndNewInstance(operation.getClazz());
                paramListPrinter.init(optionArgMap);
                paramListPrinter.printAndCopyOnToClipboard(implicitParamsVisitor.getApiImplicitParamList());
            }
        } catch (ParseException e) {
            System.err.println(e);
        }
    }

    private Map<String, String> getOptionArgMap(Option[] options) {
        Map<String, String> argMap = new HashMap<>(options.length);
        for (Option option:options){
            ArgOption argOption = ArgOption.getByCode(option.getOpt());
            if (null != argOption){
                argMap.put(option.getOpt(),option.getValue());
            }
        }
        return argMap;
    }
}
