package learn.yh.code.printer;

import learn.yh.vo.ApiImplicitParam;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class HelpPrinter implements ApiImplicitParamListPrinter {


    @Override
    public Optional<String> paramListToString(List<ApiImplicitParam> apiImplicitParamList) {
        return Optional.of("命令错误！没有找到相关的命令");
    }

    @Override
    public void printAndCopyOnToClipboard(List<ApiImplicitParam> apiImplicitParamList) {
        System.out.println(paramListToString(apiImplicitParamList));
    }
}
