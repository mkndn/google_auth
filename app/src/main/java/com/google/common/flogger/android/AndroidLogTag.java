package com.google.common.flogger.android;

import com.google.common.flogger.MetadataKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* compiled from: PG */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface AndroidLogTag {
    public static final MetadataKey TAG = MetadataKey.single("android_log_tag", String.class);
}
