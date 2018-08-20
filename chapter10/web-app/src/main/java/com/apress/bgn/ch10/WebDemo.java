/*
Freeware License, some rights reserved

Copyright (c) 2018 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ch10;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

import static com.apress.bgn.ch10.LocationUtility.getRootFolder;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class WebDemo {

    public static void main(String... args) throws Exception {
        File root = getRootFolder();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir(root.getAbsolutePath());

        File webAppFolder = new File(root.getAbsolutePath(), "production/resources/dynamic");
        if (!webAppFolder.exists()) {
            System.err.println("Could not find JSP pages directory!");
        }
        StandardContext context = (StandardContext) tomcat.addWebapp("/demo", webAppFolder.getAbsolutePath());
        context.setParentClassLoader(WebDemo.class.getClassLoader());

        File webInfClasses = new File(root.getAbsolutePath(), "production/classes");
        WebResourceRoot resources = new StandardRoot(context);
        WebResourceSet resourceSet;

        if (webInfClasses.exists()) {
            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", webInfClasses.getAbsolutePath(), "/");
        } else {
            resourceSet = new EmptyResourceSet(resources);
        }
        resources.addPreResources(resourceSet);
        context.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }


}
