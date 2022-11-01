package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Task {
    public Task addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        throw null;
    }

    public Task addOnCompleteListener(OnCompleteListener onCompleteListener) {
        throw null;
    }

    public Task addOnCompleteListener(Executor executor, OnCompleteListener onCompleteListener) {
        throw null;
    }

    public abstract Task addOnFailureListener(Executor executor, OnFailureListener onFailureListener);

    public abstract Task addOnSuccessListener(OnSuccessListener onSuccessListener);

    public abstract Task addOnSuccessListener(Executor executor, OnSuccessListener onSuccessListener);

    public Task continueWith(Executor executor, Continuation continuation) {
        throw null;
    }

    public abstract Exception getException();

    public abstract Object getResult();

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();
}
