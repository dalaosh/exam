package com.mc.escommon.mdFile;

/**
 * @author machao
 * * @datetime 2025/1/14 23:42
 * * inform
 */
class MdFileTest {
    public static void main(String[] args) {
        // 包含 Markdown 语法的字符串
        String markdownContent = "# 这是一个标题\n" +
                "这是一段使用 *斜体* 和 **粗体** 的文本。\n" +
                "还有一个 [链接](https://example.com)。\n" +
                "```java\n" +
                "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n" +
                "```";
        String newMarkdownContent = "# 新的标题\n" +
                "这是一段新的 **Markdown** 内容。\n" +
                "包含一些 [链接](https://example.com) 和 `代码`：\n" +
                "```java\n" +
                "public class Hello {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n" +
                "```";
        String admin = MdFile.setMdFile(markdownContent);
        System.out.println(admin);
        System.out.println("*************************");
        String mdFile = MdFile.getMdFile(admin);
        System.out.println(mdFile);
        System.out.println("*************************");
        MdFile.rewriteFile(admin,newMarkdownContent);
        System.out.println("*************************");
        String newFile = MdFile.getMdFile(admin);
        System.out.println(newFile);
        System.out.println("*************************");
        MdFile.deleteFile(admin);

    }
}