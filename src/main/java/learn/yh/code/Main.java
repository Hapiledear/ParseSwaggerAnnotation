package learn.yh.code;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.function.Consumer;

/**
 * Description: main函数
 * Created on:  2019/12/9 下午4:22
 *
 * @author yanghuang3@jd.com
 */
public class Main {

    public static void main(String[] args)throws IOException {
      /*  String text = "@ApiImplicitParams({\n"
                + "            @ApiImplicitParam(name = \"taskId\", value = \"任务id\", required = true, dataType = \"int\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"agencyId\", value = \"一级渠道id\", required = false, dataType = \"int\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"subAgencyId\", value = \"二级渠道id\", required = false, dataType = \"int\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"subAgencySource\", value = \"二级渠道来源\", dataType = \"int\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"linkKey\", value = \"任务链接内部Id\", required = false, dataType = \"string\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"linkShortUrl\", value = \"任务链接\", required = false, dataType = \"string\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"id\", value = \"订单Id\", required = false, dataType = \"int\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"referenceId\", value = \"订单号\", required = false, dataType = \"string\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"validState\", value = \"是否有效\", dataType = \"int\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"invalidReason\", value = \"失效原因,模糊查询\", dataType = \"string\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"searchJson\", value = \"关键词,按json格式传参.\",example=\"{\\\"column_01\\\":\\\"有效\\\",\\\"column_03\\\":\\\"xin\\\"}\", dataType = \"string\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"startTime\", value = \"数据时间开始yyyy-MM-dd HH:mm:ss\", required = true, dataType = \"date\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"endTime\", value = \"数据时间结束yyyy-MM-dd HH:mm:ss\", required = true, dataType = \"date\", paramType = \"query\"),\n"
                + "            @ApiImplicitParam(name = \"delete\", value = \"是否逻辑删除。0:未删除，1:删除，2:所有。默认查询未逻辑删除\", required = false, dataType = \"int\", paramType = \"query\")})";
        MyApiImplicitParamsVisitor implicitParamsVisitor = new MyApiImplicitParamsVisitor();
        implicitParamsVisitor.initAndExecute(text);*/

        initTerminal(new CommandLineConsumer());

    }

    private static void initTerminal(Consumer<String> lineConsumer) throws IOException{
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
        String prompt = "fog> ";
        System.out.println("请将@ApiImplicitParams的内容进行复制操作,然后执行命令.使用 Ctrl+C  退出本程序");
        while (true){
            String line;
            try{
                line = lineReader.readLine(prompt);
                lineConsumer.accept(line);
            }catch (UserInterruptException e){
                System.out.println(e);
            }catch (EndOfFileException e){
                System.out.println(e);
                return;
            }
        }
    }
}