package com.google.protobuf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GeneratedExtensionRegistryLoader {
    private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());
    private static String LITE_CLASS_NAME = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExtensionRegistryLite load(Class cls) {
        String format;
        ClassLoader classLoader = GeneratedExtensionRegistryLoader.class.getClassLoader();
        if (cls.equals(ExtensionRegistryLite.class)) {
            format = LITE_CLASS_NAME;
        } else if (!cls.getPackage().equals(GeneratedExtensionRegistryLoader.class.getPackage())) {
            throw new IllegalArgumentException(cls.getName());
        } else {
            format = String.format("%s.BlazeGenerated%sLoader", cls.getPackage().getName(), cls.getSimpleName());
        }
        try {
            try {
                try {
                    try {
                        try {
                            return (ExtensionRegistryLite) cls.cast(((GeneratedExtensionRegistryLoader) Class.forName(format, true, classLoader).getConstructor(new Class[0]).newInstance(new Object[0])).getInstance());
                        } catch (InvocationTargetException e) {
                            throw new IllegalStateException(e);
                        }
                    } catch (InstantiationException e2) {
                        throw new IllegalStateException(e2);
                    }
                } catch (IllegalAccessException e3) {
                    throw new IllegalStateException(e3);
                }
            } catch (NoSuchMethodException e4) {
                throw new IllegalStateException(e4);
            }
        } catch (ClassNotFoundException e5) {
            Iterator it = ServiceLoader.load(GeneratedExtensionRegistryLoader.class, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add(cls.cast(((GeneratedExtensionRegistryLoader) it.next()).getInstance()));
                } catch (ServiceConfigurationError e6) {
                    logger.logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load " + cls.getSimpleName(), (Throwable) e6);
                }
            }
            if (arrayList.size() == 1) {
                return (ExtensionRegistryLite) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (ExtensionRegistryLite) cls.getMethod("combine", Collection.class).invoke(null, arrayList);
            } catch (IllegalAccessException e7) {
                throw new IllegalStateException(e7);
            } catch (NoSuchMethodException e8) {
                throw new IllegalStateException(e8);
            } catch (InvocationTargetException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }

    protected abstract ExtensionRegistryLite getInstance();
}
