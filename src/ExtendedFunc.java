import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtendedFunc {
    public static List<String> getAllFilePath(String fileDir) {
        List<String> fileList=new ArrayList<>();
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            System.exit(0);
        }
        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                String fstr=f.getName();
                if(fstr.substring(fstr.lastIndexOf(".")+1).equals("c")) {
                    fileList.add(f.getAbsolutePath());
                }
            } else if (f.isDirectory()) {
                getAllFilePath(f.getAbsolutePath());//用到递归
            }
        }
        return fileList;
    }

    public static String numOfSeveralLines(String fileName){
        int lineOfCode,lineOfEmpty=0,lineOfComments=0;
        boolean isInComment=false,lineIsEmpty=true;
        String str=",代码行/空行/注释行:";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在!");
            System.exit(0);
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(tempString.indexOf("{")==0||tempString.indexOf("}")==0
                        ||tempString.indexOf(" ")==0||tempString.indexOf("\n")==0
                        ||tempString.indexOf("\t")==0||tempString.indexOf("")==0){
                    for(int i=1;i<tempString.length()-1;++i){
                        if(tempString.charAt(i)!=' '||tempString.charAt(i)!='\t'){
                            lineIsEmpty=false;
                        }
                    }
                    if(lineIsEmpty){
                        ++lineOfEmpty;
                    }
                    lineIsEmpty=true;
                }
                if(tempString.contains("//")&&!isInComment){
                    ++lineOfComments;
                }
                if(tempString.contains("/*")){
                    isInComment=true;
                    ++lineOfComments;
                    continue;
                }else if(isInComment&&!tempString.contains("*/")){
                    ++lineOfComments;
                }
                if(isInComment&&tempString.contains("*/")){
                    ++lineOfComments;
                    isInComment=false;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        lineOfCode=BasicFunc.numOfLine(fileName)-lineOfEmpty-lineOfComments;
        return str+lineOfCode+"/"+lineOfEmpty+"/"+lineOfComments+"\n";
    }

    private static List<String> countStopList(String fileName){
        List<String> stopList=new ArrayList<>();
        String str="";
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在!");
            System.exit(0);
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                for(int i=0;i<tempString.length()-1;++i){
                    while(tempString.charAt(i)==' ')
                        ++i;
                    str+=tempString.charAt(i);
                    if(tempString.charAt(i+1)==' '||tempString.charAt(i+1)=='\0'){//有缺陷
                        stopList.add(str);
                        str="";
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return stopList;
    }

    public static int numOfStopWord(String fileName,String stopFileName){
        int numOfStopWord=0;
        List<String> stopList=countStopList(stopFileName);
        List<String> wordList=BasicFunc.numOfWord(fileName);
        for(String str1:stopList){
            for(String str2:wordList){
                if(str1.equals(str2))
                    ++numOfStopWord;
            }
        }

        return numOfStopWord;
    }
}
