package learn.yh.code;

/**
 * Description: main函数
 * Created on:  2019/12/9 下午4:22
 *
 * @author yanghuang3@jd.com
 */
public class Main {

    public static void main(String[] args) {
        String text = "@ApiImplicitParams({\n"
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
        implicitParamsVisitor.initAndExecute(text);


        /*Options options = new Options();
        options.addOption(Operation.PRASE.getCode(),false,"parse Swagger Annotations");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd  = parser.parse(options,args);
            if (cmd.hasOption(Operation.PRASE.getCode())){
                MyApiImplicitParamsVisitor implicitParamsVisitor = new MyApiImplicitParamsVisitor();
                implicitParamsVisitor.initAndExecute(cmd.getArgList().get(0));
            }else {
                System.out.println("command not found");
            }
        } catch (ParseException e) {
            System.err.println(e);
        }*/
    }
}
