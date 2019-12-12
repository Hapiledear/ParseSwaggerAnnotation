package learn.yh.code;

import org.apache.commons.cli.*;

import java.util.function.Consumer;

public class CommandLineConsumer implements Consumer<String> {

    private  CommandLineParser parser = null;
    private Options options;

    public CommandLineConsumer() {
        options  = new Options();
        options.addOption(Operation.help.getCode(), false, "展示帮助文档");
        options.addOption(Operation.request.getCode(), false, "将 Swagger Annotations 转换为 request代码");
        options.addOption(Operation.query.getCode(), false, "将 Swagger Annotations 转换为 example代码");

        parser = new DefaultParser();


    }

    @Override
    public void accept(String s) {
        String[]args = s.split(" ");
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(Operation.request.getCode())) {
                MyApiImplicitParamsVisitor implicitParamsVisitor = new MyApiImplicitParamsVisitor();
                implicitParamsVisitor.initAndExecute(ClipboardUtil.getSysClipboardText());
                implicitParamsVisitor.toRequestParamString();
                implicitParamsVisitor.printRequest();
            } else if (cmd.hasOption(Operation.query.getCode())){
                MyApiImplicitParamsVisitor implicitParamsVisitor = new MyApiImplicitParamsVisitor();
                implicitParamsVisitor.initAndExecute(ClipboardUtil.getSysClipboardText());
                implicitParamsVisitor.toControllerQueryString();
                implicitParamsVisitor.printQuery();
            } else if (cmd.hasOption(Operation.help.getCode())){
                options.getOptions().forEach(option -> System.out.println("-"+option.getOpt()+"   "+option.getDescription()));
            }
            else {
                System.out.println("command not found");
            }
        } catch (ParseException e) {
            System.err.println(e);
        }
    }
}
