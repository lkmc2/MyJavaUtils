package com.lin.getter_setter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * @author lkmc2
 * @date 2018/8/21
 * @description 生成getter和setter的工具类
 */

public class GetterAndSetterUtils {

    public static void generateGetterAndSetter() {
        File startFile = new File(".");

        try {
            walkFile(startFile);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void walkFile(File startFile) throws IOException, ClassNotFoundException {
        if (startFile.isFile()) {
            if (startFile.getName().endsWith(".java")) {
                createGetterAndSetterForClazz(startFile);
            }
        }
        if (startFile.isDirectory()) {
            for (File file : Objects.requireNonNull(startFile.listFiles())) {
                walkFile(file);
            }
        }
    }

    private static void createGetterAndSetterForClazz(File file) throws ClassNotFoundException, IOException {
            String packageName = getPackageName(file);
            Class<?> clazz = Class.forName(packageName);

            boolean isExist = clazz.isAnnotationPresent(Generate.class);

            if (isExist) {
                Generate annotation = clazz.getDeclaredAnnotation(Generate.class);
                System.out.println(String.format("扫描到类：%s，注解值为：%s", clazz.getSimpleName(), annotation));

                List<String> lines = Files.readAllLines(file.toPath()); // 读取原文件的所有文本

                createDefaultConstructor(clazz, annotation, lines); // 创建默认构造器
                createParamsConstructor(clazz, annotation, lines); // 创建带参构造器
                createAllGetterAndSetter(clazz, annotation, lines); // 生成所有变量的getter和setter方法

//                lines.forEach(System.out::println);
                Files.write(file.toPath(), lines); // 将新生成的文本写回文件
                System.out.println(clazz.getSimpleName() + "类生成getter和setter完成");
            }
    }

    private static void createAllGetterAndSetter(Class<?> clazz, Generate annotation, List<String> lines) {
        if (!annotation.createGetter() && !annotation.createSetter()) {
            return;
        }

        for (Field field : clazz.getDeclaredFields()) {
            createGetterMethod(annotation, lines, field);
            createSetterMethod(annotation, lines, field);
        }
    }

    private static void createParamsConstructor(Class<?> clazz, Generate annotation, List<String> lines) {
        if (annotation.createParamsConstructor()) {
            StringBuilder paramsBuilder = new StringBuilder();
            StringBuilder assignBuilder = new StringBuilder();

            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                String typeName = field.getType().getSimpleName();
                paramsBuilder.append(String.format("%s %s, ", typeName, fieldName));
                assignBuilder.append(String.format("\t\tthis.%s = %s;\n", fieldName, fieldName));
            }
            paramsBuilder.setLength(paramsBuilder.length() - 2);

            String constructorText = String.format("\n\tpublic %s(%s) {\n"
                                       + "%s"
                                      + "\t}", clazz.getSimpleName(), paramsBuilder.toString(), assignBuilder.toString());

            addTextToLines(lines, constructorText);
        }
    }

    private static void addTextToLines(List<String> lines, String constructorText) {
        lines.add(lines.size() - 1, constructorText);
    }

    private static void createDefaultConstructor(Class<?> clazz, Generate annotation, List<String> lines) {
        if (annotation.createDefaultConstructor()) {
            String clazzName = clazz.getSimpleName();
            String constructorText =  String.format("\n\tpublic %s() {\n"
                                                  + "    }", clazzName);
            addTextToLines(lines, constructorText);
        }
    }

    private static void createGetterMethod(Generate annotation, List<String> lines, Field field) {
        if (annotation.createGetter()) {
            String getterMethodText = generateGetterMethod(field);
            addTextToLines(lines, getterMethodText);
        }
    }

    private static void createSetterMethod(Generate annotation, List<String> lines, Field field) {
        if (annotation.createSetter()) {
            String setterMethodText = generateSetterMethod(field);
            addTextToLines(lines, setterMethodText);
        }
    }

    private static String generateGetterMethod(Field field) {
        String fieldName = field.getName();
        String typeName = field.getType().getSimpleName();
        return String.format("\n\tpublic %s get%s() {\n"
                + "        return %s;\n"
                + "\t}", typeName, toTitle(fieldName), fieldName);
    }

    private static String generateSetterMethod(Field field) {
        String fieldName = field.getName();
        String typeName = field.getType().getSimpleName();
        return String.format("\n\tpublic void set%s(%s %s) {\n"
                + "        this.%s = %s;\n"
                + "\t}", toTitle(fieldName), typeName, fieldName, fieldName, fieldName);
    }

    private static String toTitle(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }


    private static String getPackageName(File file) {
        String fileName = file.getPath();
        int startSplitIndex = fileName.indexOf("com");
        int endSplitIndex = fileName.lastIndexOf(".");

        if (startSplitIndex < 0 || endSplitIndex < 0) {
            throw new RuntimeException("获取文件包名失败，文件名为：" + fileName);
        }

        fileName = fileName.substring(startSplitIndex, endSplitIndex).replaceAll("\\\\", ".");
        return fileName;
    }

    public static void main(String[] args) {
        GetterAndSetterUtils.generateGetterAndSetter();
    }

}
