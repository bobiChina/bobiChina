package com.cisco.webex.sftpTest;

import com.cisco.webex.testListRemove.Student;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author biebi@cisco.com
 * @date 2022/2/16
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(list);
//        testMethod();
        /*Map<String,String> map = new HashMap<>();
        map.put("1","12");
        map.put("2","34");

        List<String> collect = map.keySet().stream().map(confId -> map.get(confId))
                .collect(Collectors.toList());
        collect.forEach(s-> System.out.println(s));*/

        /*Map<Long,String> map = new HashMap<>();
        map.put(1l,"12");
        map.put(2l,"34");
        Queue<Long> queue = new ArrayDeque<>();
        queue.offer(1l);
        queue.offer(2l);
        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue.peek());*/


       /* List<Path> localFiles = new ArrayList<>();
        Path localPath = Paths.get("C:\\nbr2\\hm\\playback\\884444\\515\\503686515\\329\\212650750937943329");
        Files.walkFileTree(localPath, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) {
                        localFiles.add(path);
                        return FileVisitResult.CONTINUE;
                    }
                });*/

        //localFiles.stream().forEach(s-> System.out.println(s.toString()));
        //localFiles.stream().forEach(s-> System.out.println(s.getParent()));

        /*localFiles.stream().forEach(s-> System.out.println(s.toString().substring(0,s.toString().indexOf(s.getFileName().toString()))));
        localFiles.stream().forEach(s-> System.out.println(s.getFileName()));*/
        /*for (Path localFile : localFiles) {
            String s = localFile.toAbsolutePath().toString();
            String name = localFile.getFileName().toString();
            System.out.println(name);
            String sourcePath = s.substring(0,s.lastIndexOf(File.separator));
            System.out.println(s.substring(0,s.lastIndexOf(File.separator)));
            System.out.println(filePathReplace(sourcePath, "/tmp/em", 884444l)+File.separator+name);
           *//* String localFilePath = localPath.toString() + File.separator + localFile.getFileName();
            System.out.println(localFilePath + ":" + (Long) Files.getAttribute(localFile, "basic:size"));*//*
        }*/
    }
    public static String filePathReplace(String sourcePath,String targetPath,Long siteId){
        String afterSubStr = StringUtils.substringAfterLast(sourcePath, String.valueOf(siteId));
        return String.join("",targetPath,"/"+siteId,afterSubStr);
    }

    public static void testMethod(){
        Student student;
        student = new Student(12,"lisi");
        System.out.println(student);
    }
}
