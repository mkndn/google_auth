package com.google.protobuf;

import java.lang.reflect.Field;

/* compiled from: PG */
/* loaded from: classes.dex */
final class OneofInfo {
    private final Field caseField;
    private final int id;
    private final Field valueField;

    public OneofInfo(int i, Field field, Field field2) {
        this.id = i;
        this.caseField = field;
        this.valueField = field2;
    }

    public Field getCaseField() {
        return this.caseField;
    }

    public Field getValueField() {
        return this.valueField;
    }
}
