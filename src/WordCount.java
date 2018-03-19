
public class WordCount {
    public static void main(String[] args){
        int i,j=0;
        String str="",fileNeedToCount=null,fileNeedToOutput=null;
        boolean outputIsSpecified=false,isCountChar=false,isCountWord=false,isCountLine=false;
        for(i=0;i<args.length;++i){
            if(args[i].equals("-c")){
                isCountChar=true;//需要统计字符数
                ++j;
            }
            if(args[i].equals("-w")){
                isCountWord=true;//需要统计单词数
                ++j;
            }
            if(args[i].equals("-l")){
                isCountLine=true;//需要统计行数
                ++j;
            }
            if(args[i].indexOf(".c")!=1){
                fileNeedToCount=args[j];//获取需要统计数据的c文件名
            }
            if(args[i].equals("-o")){
                outputIsSpecified=true;//指定输出
                ++i;
                if((i==args.length)||(i<args.length&&!args[i].contains(".txt"))){
                    System.out.println("-o后必须接后缀名为txt的输出文件!");
                    System.exit(0);
                }else{
                    fileNeedToOutput=args[i];//获取指定输出文件名
                }
            }
        }
        if(fileNeedToCount==null){
            System.out.println("请输入要统计数据的c文件名!");
            System.exit(0);
        }else{
            if(isCountChar){
                str+=fileNeedToCount+",字符数:"+BasicFunc.numOfChar(fileNeedToCount)+"\n";
            }
            if(isCountWord){
                str+=fileNeedToCount+",单词数:"+BasicFunc.numOfWord(fileNeedToCount)+"\n";
            }
            if(isCountLine){
                str+=fileNeedToCount+",行数:"+BasicFunc.numOfLine(fileNeedToCount)+"\n";
            }
            if(outputIsSpecified)
                BasicFunc.output(fileNeedToOutput,str);
            else
                BasicFunc.output("result.txt",str);
        }
    }
}
