package kotlin.jvm.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ReflectionFactory {
    public String renderLambdaToString(FunctionBase functionBase) {
        String obj = functionBase.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring("kotlin.jvm.functions.".length()) : obj;
    }
}
