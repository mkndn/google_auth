package dagger;

import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.FailoverLoader;
import dagger.internal.Keys;
import dagger.internal.Linker;
import dagger.internal.Loader;
import dagger.internal.ModuleAdapter;
import dagger.internal.Modules;
import dagger.internal.SetBinding;
import dagger.internal.ThrowingErrorHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ObjectGraph {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class DaggerObjectGraph extends ObjectGraph {
        private final DaggerObjectGraph base;
        private final Map injectableTypes;
        private final Linker linker;
        private final Loader plugin;
        private final List setBindings;
        private final Map staticInjections;

        DaggerObjectGraph(DaggerObjectGraph daggerObjectGraph, Linker linker, Loader loader, Map map, Map map2, List list) {
            this.base = daggerObjectGraph;
            this.linker = (Linker) checkNotNull(linker, "linker");
            this.plugin = (Loader) checkNotNull(loader, "plugin");
            this.staticInjections = (Map) checkNotNull(map, "staticInjections");
            this.injectableTypes = (Map) checkNotNull(map2, "injectableTypes");
            this.setBindings = (List) checkNotNull(list, "setBindings");
        }

        private static Object checkNotNull(Object obj, String str) {
            if (obj == null) {
                throw new NullPointerException(str);
            }
            return obj;
        }

        private Binding getInjectableTypeBinding(ClassLoader classLoader, String str, String str2) {
            Binding requestBinding;
            Class moduleClassDeclaringInjects = getModuleClassDeclaringInjects(str);
            if (moduleClassDeclaringInjects == null) {
                throw new IllegalArgumentException("No inject registered for " + str + ". You must explicitly add it to the 'injects' option in one of your modules.");
            }
            synchronized (this.linker) {
                requestBinding = this.linker.requestBinding(str2, moduleClassDeclaringInjects, classLoader, false, true);
                if (requestBinding != null && requestBinding.isLinked()) {
                }
                this.linker.linkRequested();
                requestBinding = this.linker.requestBinding(str2, moduleClassDeclaringInjects, classLoader, false, true);
            }
            return requestBinding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ObjectGraph makeGraph(DaggerObjectGraph daggerObjectGraph, Loader loader, Object... objArr) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            StandardBindings standardBindings = daggerObjectGraph == null ? new StandardBindings() : new StandardBindings(daggerObjectGraph.setBindings);
            BindingsGroup overridesBindings = new OverridesBindings();
            ArrayList loadModules = Modules.loadModules(loader, objArr);
            int size = loadModules.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                Modules.ModuleWithAdapter moduleWithAdapter = (Modules.ModuleWithAdapter) loadModules.get(i);
                ModuleAdapter moduleAdapter = moduleWithAdapter.getModuleAdapter();
                for (int i2 = 0; i2 < moduleAdapter.injectableTypes.length; i2++) {
                    linkedHashMap.put(moduleAdapter.injectableTypes[i2], moduleAdapter.moduleClass);
                }
                for (int i3 = 0; i3 < moduleAdapter.staticInjections.length; i3++) {
                    linkedHashMap2.put(moduleAdapter.staticInjections[i3], null);
                }
                try {
                    moduleAdapter.getBindings(moduleAdapter.overrides ? overridesBindings : standardBindings, moduleWithAdapter.getModule());
                    i++;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(moduleAdapter.moduleClass.getSimpleName() + ": " + e.getMessage(), e);
                }
            }
            Linker linker = new Linker(daggerObjectGraph != null ? daggerObjectGraph.linker : null, loader, new ThrowingErrorHandler());
            linker.installBindings(standardBindings);
            linker.installBindings(overridesBindings);
            return new DaggerObjectGraph(daggerObjectGraph, linker, loader, linkedHashMap2, linkedHashMap, standardBindings.setBindings);
        }

        @Override // dagger.ObjectGraph
        public Object inject(Object obj) {
            String membersKey = Keys.getMembersKey(obj.getClass());
            getInjectableTypeBinding(obj.getClass().getClassLoader(), membersKey, membersKey).injectMembers(obj);
            return obj;
        }

        private Class getModuleClassDeclaringInjects(String str) {
            Class cls = null;
            for (DaggerObjectGraph daggerObjectGraph = this; daggerObjectGraph != null; daggerObjectGraph = daggerObjectGraph.base) {
                cls = (Class) daggerObjectGraph.injectableTypes.get(str);
                if (cls != null) {
                    break;
                }
            }
            return cls;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OverridesBindings extends BindingsGroup {
        OverridesBindings() {
        }
    }

    ObjectGraph() {
    }

    public static ObjectGraph create(Object... objArr) {
        return DaggerObjectGraph.makeGraph(null, new FailoverLoader(), objArr);
    }

    public abstract Object inject(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StandardBindings extends BindingsGroup {
        private final List setBindings;

        public StandardBindings() {
            this.setBindings = new ArrayList();
        }

        public StandardBindings(List list) {
            this.setBindings = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SetBinding setBinding = new SetBinding((SetBinding) it.next());
                this.setBindings.add(setBinding);
                put(setBinding.provideKey, setBinding);
            }
        }
    }
}
