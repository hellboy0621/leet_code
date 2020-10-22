package com.transformers.advanced.week01;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("BootstrapClassLoader");
        for (URL url : urls) {
            System.out.println(" -> " + url.toExternalForm());
        }
        // 扩展类加载器
        printClassLoader("ExtClassLoader", JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        // 应用类加载器
        printClassLoader("AppClassLoader", JvmClassLoaderPrintPath.class.getClassLoader());
    }

    private static void printClassLoader(String name, ClassLoader cl) {
        if (cl != null) {
            System.out.println(name + " -> " + cl.toString());
            printURLForClassLoader(cl);
        } else {
            System.out.println(name + " ClassLoader -> null");
        }
    }

    private static void printURLForClassLoader(ClassLoader cl) {
        Object ucp = insightField(cl, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps) {
            System.out.println(" -> " + p.toString());
        }
    }

    private static Object insightField(Object obj, String fName) {
        try {
            Field f = null;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * $ java JvmClassLoaderPrintPath
     * BootstrapClassLoader
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/resources.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/rt.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/sunrsasign.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/jsse.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/jce.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/charsets.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/jfr.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/classes
     * ExtClassLoader -> sun.misc.Launcher$ExtClassLoader@15db9742
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/access-bridge-64.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/cldrdata.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/dnsns.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/jaccess.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/jfxrt.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/localedata.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/nashorn.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/sunec.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/sunjce_provider.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/sunmscapi.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/sunpkcs11.jar
     *  -> file:/D:/develop/Java/jre1.8.0_261/lib/ext/zipfs.jar
     * AppClassLoader -> sun.misc.Launcher$AppClassLoader@73d16e93
     *  -> file:/D:/repo/java/bytecode/
     */
}
