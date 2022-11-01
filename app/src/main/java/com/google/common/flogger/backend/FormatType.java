package com.google.common.flogger.backend;

import java.math.BigDecimal;
import java.math.BigInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum FormatType {
    GENERAL(false, true) { // from class: com.google.common.flogger.backend.FormatType.1
        @Override // com.google.common.flogger.backend.FormatType
        public boolean canFormat(Object obj) {
            return true;
        }
    },
    BOOLEAN(false, false) { // from class: com.google.common.flogger.backend.FormatType.2
        @Override // com.google.common.flogger.backend.FormatType
        public boolean canFormat(Object obj) {
            return obj instanceof Boolean;
        }
    },
    CHARACTER(false, false) { // from class: com.google.common.flogger.backend.FormatType.3
        @Override // com.google.common.flogger.backend.FormatType
        public boolean canFormat(Object obj) {
            if (obj instanceof Character) {
                return true;
            }
            if ((obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short)) {
                return Character.isValidCodePoint(((Number) obj).intValue());
            }
            return false;
        }
    },
    INTEGRAL(true, false) { // from class: com.google.common.flogger.backend.FormatType.4
        @Override // com.google.common.flogger.backend.FormatType
        public boolean canFormat(Object obj) {
            return (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof BigInteger);
        }
    },
    FLOAT(true, true) { // from class: com.google.common.flogger.backend.FormatType.5
        @Override // com.google.common.flogger.backend.FormatType
        public boolean canFormat(Object obj) {
            return (obj instanceof Double) || (obj instanceof Float) || (obj instanceof BigDecimal);
        }
    };
    
    private final boolean isNumeric;
    private final boolean supportsPrecision;

    FormatType(boolean z, boolean z2) {
        this.isNumeric = z;
        this.supportsPrecision = z2;
    }

    public abstract boolean canFormat(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean supportsPrecision() {
        return this.supportsPrecision;
    }
}
