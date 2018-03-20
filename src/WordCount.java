import java.util.ArrayList;
import java.util.List;

public class WordCount {
    public static void main(String[] args){
        int i,j=0,z=0;
        List<String> argList=new ArrayList<>();
        String str="",fileNeedToCount=null,fileNeedToCount1=null,fileNeedToOutput=null,fileOfStopWord=null;
        boolean outputIsSpecified=false,isCountChar=false,isCountWord=false,isCountLine=false,
                isCountSeveralLines=false,isStopUse=false,isRecursive=false;
        for(i=0;i<args.length;++i){
            argList.add(args[i]);
            if(args[i].equals("-c")){
                isCountChar=true;//需要统计字符数
                ++j;
                ++z;
            }
            if(args[i].equals("-w")){
                isCountWord=true;//需要统计单词数
                ++j;
                ++z;
            }
            if(args[i].equals("-l")){
                isCountLine=true;//需要统计行数
                ++j;
                ++z;
            }
            if(args[i].equals("-a")){
                isCountSeveralLines=true;
                ++j;
                ++z;
            }
            if(args[i].equals("-s")){
                isRecursive=true;
                ++j;
                ++z;
            }

            if(args[i].contains(".c")){
                fileNeedToCount1=args[j];//获取需要统计数据的c文件名
                ++z;
            }
            if(args[i].equals("-e")){
                ++z;
                isStopUse=true;
                fileOfStopWord="stoplist.txt";
            }
            if(args[i].equals("-o")){
                outputIsSpecified=true;//指定输出
                if(((i+i)==args.length)||((i+1)<args.length&&!args[i+1].contains(".txt"))){
                    System.out.println("-o后必须接后缀名为txt的输出文件!");
                    System.exit(0);
                }else{
                    fileNeedToOutput=args[i+1];//获取指定输出文件名
                }
            }
        }

        for(String string:argList){
            if(string.contains(".c")){
                fileNeedToCount=string;
                if(fileNeedToCount==null){
                    System.out.println("请输入要统计数据的c文件名!");
                    System.exit(0);
                }else{
                    if(isStopUse){
                        if(!isCountWord){
                            System.out.println("停用单词只能在统计单词时使用!");
                            System.exit(0);
                        }

                    }
                    if(isCountChar){
                        str+=fileNeedToCount+",字符数:"+BasicFunc.numOfChar(fileNeedToCount)+"\n";
                    }
                    if(isCountWord){
                        if(!isStopUse) {
                            str += fileNeedToCount + ",单词数:" + BasicFunc.numOfWord(fileNeedToCount).size() + "\n";
                        }else{
                            int num=BasicFunc.numOfWord(fileNeedToCount).size()-ExtendedFunc.numOfStopWord(fileNeedToCount,fileOfStopWord);
                            str += fileNeedToCount + ",单词数:" + num + "\n";
                        }
                    }
                    if(isCountLine){
                        str+=fileNeedToCount+",行数:"+BasicFunc.numOfLine(fileNeedToCount)+"\n";
                    }
                    if(isCountSeveralLines){
                        str+=fileNeedToCount+ExtendedFunc.numOfSeveralLines(fileNeedToCount)+"\n";
                    }
                    if(outputIsSpecified)
                        BasicFunc.output(fileNeedToOutput,str);
                    else
                        BasicFunc.output("result.txt",str);
                }
            }
        }
    }
}
