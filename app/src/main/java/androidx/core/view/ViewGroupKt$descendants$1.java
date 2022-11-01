package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@DebugMetadata(c = "androidx.core.view.ViewGroupKt$descendants$1", f = "ViewGroup.kt", l = {118, 120}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ViewGroupKt$descendants$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ ViewGroup $this_descendants;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewGroupKt$descendants$1(ViewGroup viewGroup, Continuation continuation) {
        super(2, continuation);
        this.$this_descendants = viewGroup;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ViewGroupKt$descendants$1 viewGroupKt$descendants$1 = new ViewGroupKt$descendants$1(this.$this_descendants, continuation);
        viewGroupKt$descendants$1.L$0 = obj;
        return viewGroupKt$descendants$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope sequenceScope, Continuation continuation) {
        return ((ViewGroupKt$descendants$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x008b -> B:19:0x008d). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0094 -> B:22:0x0097). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        SequenceScope sequenceScope;
        ViewGroup viewGroup;
        int childCount;
        int i;
        ViewGroupKt$descendants$1 viewGroupKt$descendants$1;
        int i2;
        int i3;
        View view;
        ViewGroup viewGroup2;
        ViewGroupKt$descendants$1 viewGroupKt$descendants$12;
        int i4;
        ViewGroup viewGroup3;
        SequenceScope sequenceScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                sequenceScope = (SequenceScope) this.L$0;
                viewGroup = this.$this_descendants;
                childCount = viewGroup.getChildCount();
                i = 0;
                viewGroupKt$descendants$1 = this;
                if (i >= childCount) {
                    View childAt = viewGroup.getChildAt(i);
                    Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(index)");
                    viewGroupKt$descendants$1.L$0 = sequenceScope;
                    viewGroupKt$descendants$1.L$1 = viewGroup;
                    viewGroupKt$descendants$1.L$2 = childAt;
                    viewGroupKt$descendants$1.I$0 = i;
                    viewGroupKt$descendants$1.I$1 = childCount;
                    viewGroupKt$descendants$1.label = 1;
                    if (sequenceScope.yield(childAt, viewGroupKt$descendants$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ViewGroupKt$descendants$1 viewGroupKt$descendants$13 = viewGroupKt$descendants$1;
                    viewGroup2 = viewGroup;
                    i2 = childCount;
                    i3 = i;
                    view = childAt;
                    viewGroupKt$descendants$12 = viewGroupKt$descendants$13;
                    if (view instanceof ViewGroup) {
                        i4 = i2;
                        viewGroup = viewGroup2;
                        viewGroupKt$descendants$1 = viewGroupKt$descendants$12;
                        int i5 = i4;
                        i = i3 + 1;
                        childCount = i5;
                        if (i >= childCount) {
                        }
                    } else {
                        Sequence descendants = ViewGroupKt.getDescendants((ViewGroup) view);
                        viewGroupKt$descendants$12.L$0 = sequenceScope;
                        viewGroupKt$descendants$12.L$1 = viewGroup2;
                        viewGroupKt$descendants$12.L$2 = null;
                        viewGroupKt$descendants$12.I$0 = i3;
                        viewGroupKt$descendants$12.I$1 = i2;
                        viewGroupKt$descendants$12.label = 2;
                        if (sequenceScope.yieldAll(descendants, viewGroupKt$descendants$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        viewGroup3 = viewGroup2;
                        sequenceScope2 = sequenceScope;
                        sequenceScope = sequenceScope2;
                        viewGroupKt$descendants$1 = viewGroupKt$descendants$12;
                        ViewGroup viewGroup4 = viewGroup3;
                        i4 = i2;
                        viewGroup = viewGroup4;
                        int i52 = i4;
                        i = i3 + 1;
                        childCount = i52;
                        if (i >= childCount) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            case 1:
                i2 = this.I$1;
                i3 = this.I$0;
                view = (View) this.L$2;
                viewGroup2 = (ViewGroup) this.L$1;
                ResultKt.throwOnFailure(obj);
                sequenceScope = (SequenceScope) this.L$0;
                viewGroupKt$descendants$12 = this;
                if (view instanceof ViewGroup) {
                }
                break;
            case 2:
                i2 = this.I$1;
                i3 = this.I$0;
                viewGroup3 = (ViewGroup) this.L$1;
                sequenceScope2 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                viewGroupKt$descendants$12 = this;
                sequenceScope = sequenceScope2;
                viewGroupKt$descendants$1 = viewGroupKt$descendants$12;
                ViewGroup viewGroup42 = viewGroup3;
                i4 = i2;
                viewGroup = viewGroup42;
                int i522 = i4;
                i = i3 + 1;
                childCount = i522;
                if (i >= childCount) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
