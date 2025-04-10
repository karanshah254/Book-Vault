package com.book.jsf;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // Create default connector

        String webappDirLocation = "src/main/webapp/";
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        System.out.println("JSF app running at http://localhost:8080/");
        tomcat.start();
        tomcat.getServer().await();
    }
}