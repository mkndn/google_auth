package dagger.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Modules {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ModuleWithAdapter {
        private final Object module;
        private final ModuleAdapter moduleAdapter;

        ModuleWithAdapter(ModuleAdapter moduleAdapter, Object obj) {
            this.moduleAdapter = moduleAdapter;
            this.module = obj;
        }

        public Object getModule() {
            return this.module;
        }

        public ModuleAdapter getModuleAdapter() {
            return this.moduleAdapter;
        }
    }

    private static void collectIncludedModulesRecursively(Loader loader, ModuleAdapter moduleAdapter, List list, HashSet hashSet) {
        Class[] clsArr;
        for (Class cls : moduleAdapter.includes) {
            if (!hashSet.contains(cls)) {
                ModuleAdapter moduleAdapter2 = loader.getModuleAdapter(cls);
                list.add(new ModuleWithAdapter(moduleAdapter2, moduleAdapter2.newModule()));
                hashSet.add(cls);
                collectIncludedModulesRecursively(loader, moduleAdapter2, list, hashSet);
            }
        }
    }

    public static ArrayList loadModules(Loader loader, Object[] objArr) {
        int length = objArr.length;
        ArrayList arrayList = new ArrayList(length);
        HashSet hashSet = new HashSet(length);
        for (int i = length - 1; i >= 0; i--) {
            Object obj = objArr[i];
            if (obj instanceof Class) {
                Class cls = (Class) obj;
                if (hashSet.add(cls)) {
                    ModuleAdapter moduleAdapter = loader.getModuleAdapter(cls);
                    arrayList.add(new ModuleWithAdapter(moduleAdapter, moduleAdapter.newModule()));
                }
            } else if (hashSet.add(obj.getClass())) {
                arrayList.add(new ModuleWithAdapter(loader.getModuleAdapter(obj.getClass()), obj));
            }
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            collectIncludedModulesRecursively(loader, ((ModuleWithAdapter) arrayList.get(i2)).getModuleAdapter(), arrayList, hashSet);
        }
        return arrayList;
    }
}
