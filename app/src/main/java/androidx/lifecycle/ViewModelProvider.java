package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ViewModelProvider {
    private final Factory factory;
    private final ViewModelStore store;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AndroidViewModelFactory extends NewInstanceFactory {
        public static final Companion Companion = new Companion(null);
        private static AndroidViewModelFactory sInstance;
        private final Application application;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final AndroidViewModelFactory getInstance(Application application) {
                Intrinsics.checkNotNullParameter(application, "application");
                if (AndroidViewModelFactory.sInstance == null) {
                    AndroidViewModelFactory.sInstance = new AndroidViewModelFactory(application);
                }
                AndroidViewModelFactory androidViewModelFactory = AndroidViewModelFactory.sInstance;
                Intrinsics.checkNotNull(androidViewModelFactory);
                return androidViewModelFactory;
            }
        }

        public AndroidViewModelFactory(Application application) {
            Intrinsics.checkNotNullParameter(application, "application");
            this.application = application;
        }

        public static final AndroidViewModelFactory getInstance(Application application) {
            return Companion.getInstance(application);
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel create(Class modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            if (AndroidViewModel.class.isAssignableFrom(modelClass)) {
                try {
                    ViewModel viewModel = (ViewModel) modelClass.getConstructor(Application.class).newInstance(this.application);
                    Intrinsics.checkNotNullExpressionValue(viewModel, "{\n                try {\n…          }\n            }");
                    return viewModel;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(Intrinsics.stringPlus("Cannot create an instance of ", modelClass), e);
                } catch (InstantiationException e2) {
                    throw new RuntimeException(Intrinsics.stringPlus("Cannot create an instance of ", modelClass), e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException(Intrinsics.stringPlus("Cannot create an instance of ", modelClass), e3);
                } catch (InvocationTargetException e4) {
                    throw new RuntimeException(Intrinsics.stringPlus("Cannot create an instance of ", modelClass), e4);
                }
            }
            return super.create(modelClass);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Factory {
        ViewModel create(Class cls);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class KeyedFactory extends OnRequeryFactory implements Factory {
        public ViewModel create(Class cls) {
            throw null;
        }

        public abstract ViewModel create(String str, Class cls);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NewInstanceFactory implements Factory {
        public static final Companion Companion = new Companion(null);
        private static NewInstanceFactory sInstance;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final NewInstanceFactory getInstance() {
                if (NewInstanceFactory.sInstance == null) {
                    NewInstanceFactory.sInstance = new NewInstanceFactory();
                }
                NewInstanceFactory newInstanceFactory = NewInstanceFactory.sInstance;
                Intrinsics.checkNotNull(newInstanceFactory);
                return newInstanceFactory;
            }
        }

        public static final NewInstanceFactory getInstance() {
            return Companion.getInstance();
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel create(Class modelClass) {
            Intrinsics.checkNotNullParameter(modelClass, "modelClass");
            try {
                Object newInstance = modelClass.newInstance();
                Intrinsics.checkNotNullExpressionValue(newInstance, "{\n                modelC…wInstance()\n            }");
                return (ViewModel) newInstance;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(Intrinsics.stringPlus("Cannot create an instance of ", modelClass), e);
            } catch (InstantiationException e2) {
                throw new RuntimeException(Intrinsics.stringPlus("Cannot create an instance of ", modelClass), e2);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class OnRequeryFactory {
        public void onRequery(ViewModel viewModel) {
            Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        }
    }

    public ViewModelProvider(ViewModelStore store, Factory factory) {
        Intrinsics.checkNotNullParameter(store, "store");
        Intrinsics.checkNotNullParameter(factory, "factory");
        this.store = store;
        this.factory = factory;
    }

    public ViewModel get(Class modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return get(Intrinsics.stringPlus("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName), modelClass);
    }

    public ViewModel get(String key, Class modelClass) {
        ViewModel viewModel;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        ViewModel viewModel2 = this.store.get(key);
        if (!modelClass.isInstance(viewModel2)) {
            Factory factory = this.factory;
            if (factory instanceof KeyedFactory) {
                viewModel = ((KeyedFactory) factory).create(key, modelClass);
            } else {
                viewModel = factory.create(modelClass);
            }
            this.store.put(key, viewModel);
            Intrinsics.checkNotNullExpressionValue(viewModel, "viewModel");
            return viewModel;
        }
        Factory factory2 = this.factory;
        OnRequeryFactory onRequeryFactory = factory2 instanceof OnRequeryFactory ? (OnRequeryFactory) factory2 : null;
        if (onRequeryFactory != null) {
            Intrinsics.checkNotNullExpressionValue(viewModel2, "viewModel");
            onRequeryFactory.onRequery(viewModel2);
        }
        if (viewModel2 != null) {
            return viewModel2;
        }
        throw new NullPointerException("null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
    }
}
