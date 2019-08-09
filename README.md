# WordCount
统计c文件的字符数、单词数、行数...

字符数

单词数 空格或逗号分割开视为单词，水平制表符'\t',换行符隔开视为单词

行数

结果输出到默认文件result.txt

扩展功能

快速处理多个文件

基本功能

wc.exe -c file.c //返回文件file.c的字符数

wc.exe -w file.c //返回文件file.c的单词总数

wc.exe -l file.c //返回文件file.c的总行数

wc.exe -o outputFile.txt //将结果输出到指定文件outputFile.txt

扩展功能

wc.exe -s  //递归处理目录下符合条件的文件

wc.exe -a file.c //返回更复杂的数据（代码行/空行/注释行）

wc.exe -e stopList.txt //停用词表，统计文件单词总数时，不统计该表中的单词

高级功能

wc.exe -x  //该参数单独使用，如果命令行有该参数，则程序会显示图形界面，

用户可以通过界面选取单个文件，程序就会显示文件的字符数、单词数、行数等全部统计信息。


### Add Something
