package com.google.android.apps.authenticator.testability.com.google.android.gms.people;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PeopleClientUtil$$InjectAdapter extends Binding implements Provider {
    public PeopleClientUtil$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.com.google.android.gms.people.PeopleClientUtil", "members/com.google.android.apps.authenticator.testability.com.google.android.gms.people.PeopleClientUtil", false, PeopleClientUtil.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public PeopleClientUtil get() {
        return new PeopleClientUtil();
    }
}
