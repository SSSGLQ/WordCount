import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BasicFunc {
    /**
     * 统计字符数
     * @param fileName
     * @return
     */
    public static int numOfChar(String fileName){
        int numOfChar=0;
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在!");
            System.exit(0);
        }
        Reader reader = null;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                ++numOfChar;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfChar;
    }

    /**
     * 统计单词数
     * @param fileName
     * @return
     */
    public static List<String> numOfWord(String fileName){
        int numOfWord=0;
        String str="";
        List<String> wordList=new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在!");
            System.exit(0);
        }
        Reader reader = null;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            boolean isTheHeadOfLine=true;
            while ((tempchar = reader.read()) != -1) {
                if((char)tempchar==' '){
                    if(!isTheHeadOfLine){
                        ++numOfWord;
                        wordList.add(str);
                        str="";
                    }
                    while((tempchar=reader.read())==' ');
                }
                if((char)tempchar==',') {
                    if(!isTheHeadOfLine) {
                        ++numOfWord;
                        wordList.add(str);
                        str="";
                    }
                    while((tempchar=reader.read())==',');
                }
                if((char)tempchar=='\n') {
                    ++numOfWord;
                    wordList.add(str);
                    str="";
                    isTheHeadOfLine=true;
                    continue;
                }
                str+=(char)tempchar;
                if(isTheHeadOfLine){
                    isTheHeadOfLine=false;
                }
            }
            reader.close();
            if(numOfChar(fileName)!=0){
                ++numOfWord;
                wordList.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordList;
    }

    /**
     * 统计行数
     * @param fileName
     * @return
     */
    public static int numOfLine(String fileName){
        int numOfLine=0;
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在!");
            System.exit(0);
        }
        Reader reader = null;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if((char)tempchar=='\n') {
                    ++numOfLine;
                }
            }
            reader.close();
            if(numOfChar(fileName)!=0){
                ++numOfLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfLine;
    }

    /**
     * 输出结果到result.txt或指定的输出文件
     * @param fileName
     * @param str
     */
    public static void output(String fileName,String str){
        AppendToFile.appendMethod(fileName, str);
    }
}
