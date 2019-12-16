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