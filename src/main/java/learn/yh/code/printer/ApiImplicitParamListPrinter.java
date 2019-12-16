package learn.yh.code.printer;

import learn.yh.code.utils.ClipboardUtil;
import learn.yh.vo.ApiImplicitParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ApiImplicitParamListPrinter {
    /**
     * 将paramList转换为字符串
     *
     */
    public Optional<String> paramListToString(List<ApiImplicitParam> apiImplicitParamList);

    /**
     * 将字符串输出到控制台，并复制到剪切板
     */
    public default void printAndCopyOnToClipboard(List<ApiImplicitParam> apiImplicitParamList){
        String strOut = paramListToString(apiImplicitParamList).orElse("context is empty");
        System.out.println(strOut);
        ClipboardUtil.setSysClipboardText(strOut);
        System.out.println("已将内容复制到剪切板！！！");
    }

    /**
     * 将额外的参数透传过来
     */
    public default void init(Map<String,String> optionArgMap){};

}
