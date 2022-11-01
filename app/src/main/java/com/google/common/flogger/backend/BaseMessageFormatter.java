package com.google.common.flogger.backend;

import com.google.common.flogger.parameter.DateTimeFormat;
import com.google.common.flogger.parameter.Parameter;
import com.google.common.flogger.parameter.ParameterVisitor;
import com.google.common.flogger.parser.MessageBuilder;
import com.google.common.flogger.util.Checks;
import java.util.Calendar;
import java.util.Date;
import java.util.Formattable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BaseMessageFormatter extends MessageBuilder implements ParameterVisitor {
    protected final Object[] args;
    private int literalStart;
    protected final StringBuilder out;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.common.flogger.backend.BaseMessageFormatter$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$flogger$backend$FormatChar;

        static {
            int[] iArr = new int[FormatChar.values().length];
            $SwitchMap$com$google$common$flogger$backend$FormatChar = iArr;
            try {
                iArr[FormatChar.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$common$flogger$backend$FormatChar[FormatChar.DECIMAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$common$flogger$backend$FormatChar[FormatChar.BOOLEAN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$common$flogger$backend$FormatChar[FormatChar.HEX.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$common$flogger$backend$FormatChar[FormatChar.CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    protected BaseMessageFormatter(TemplateContext templateContext, Object[] objArr, StringBuilder sb) {
        super(templateContext);
        this.literalStart = 0;
        this.args = (Object[]) Checks.checkNotNull(objArr, "arguments");
        this.out = (StringBuilder) Checks.checkNotNull(sb, "buffer");
    }

    private static void appendFormatted(StringBuilder sb, Object obj, FormatChar formatChar, FormatOptions formatOptions) {
        switch (AnonymousClass1.$SwitchMap$com$google$common$flogger$backend$FormatChar[formatChar.ordinal()]) {
            case 1:
                if (!(obj instanceof Formattable)) {
                    if (formatOptions.isDefault()) {
                        sb.append(MessageUtils.safeToString(obj));
                        return;
                    }
                } else {
                    MessageUtils.safeFormatTo((Formattable) obj, sb, formatOptions);
                    return;
                }
                break;
            case 2:
            case 3:
                if (formatOptions.isDefault()) {
                    sb.append(obj);
                    return;
                }
                break;
            case 4:
                if (formatOptions.filter(128, false, false).equals(formatOptions)) {
                    MessageUtils.appendHex(sb, (Number) obj, formatOptions);
                    return;
                }
                break;
            case 5:
                if (formatOptions.isDefault()) {
                    if (obj instanceof Character) {
                        sb.append(obj);
                        return;
                    }
                    int intValue = ((Number) obj).intValue();
                    if ((intValue >>> 16) == 0) {
                        sb.append((char) intValue);
                        return;
                    } else {
                        sb.append(Character.toChars(intValue));
                        return;
                    }
                }
                break;
        }
        String defaultFormatString = formatChar.getDefaultFormatString();
        if (!formatOptions.isDefault()) {
            int i = formatChar.getChar();
            if (formatOptions.shouldUpperCase()) {
                i &= 65503;
            }
            defaultFormatString = formatOptions.appendPrintfOptions(new StringBuilder("%")).append((char) i).toString();
        }
        sb.append(String.format(MessageUtils.FORMAT_LOCALE, defaultFormatString, obj));
    }

    public static StringBuilder appendFormattedMessage(LogData logData, StringBuilder sb) {
        if (logData.getTemplateContext() != null) {
            BaseMessageFormatter baseMessageFormatter = new BaseMessageFormatter(logData.getTemplateContext(), logData.getArguments(), sb);
            sb = (StringBuilder) baseMessageFormatter.build();
            if (logData.getArguments().length > baseMessageFormatter.getExpectedArgumentCount()) {
                sb.append(" [ERROR: UNUSED LOG ARGUMENTS]");
                return sb;
            }
        } else {
            sb.append(MessageUtils.safeToString(logData.getLiteralArgument()));
        }
        return sb;
    }

    private static void appendInvalid(StringBuilder sb, Object obj, String str) {
        sb.append("[INVALID: format=").append(str).append(", type=").append(obj.getClass().getCanonicalName()).append(", value=").append(MessageUtils.safeToString(obj)).append("]");
    }

    @Override // com.google.common.flogger.parser.MessageBuilder
    public void addParameterImpl(int i, int i2, Parameter parameter) {
        getParser().unescape(this.out, getMessage(), this.literalStart, i);
        parameter.accept((ParameterVisitor) this, this.args);
        this.literalStart = i2;
    }

    @Override // com.google.common.flogger.parameter.ParameterVisitor
    public void visit(Object obj, FormatChar formatChar, FormatOptions formatOptions) {
        if (formatChar.getType().canFormat(obj)) {
            appendFormatted(this.out, obj, formatChar, formatOptions);
        } else {
            appendInvalid(this.out, obj, formatChar.getDefaultFormatString());
        }
    }

    @Override // com.google.common.flogger.parameter.ParameterVisitor
    public void visitDateTime(Object obj, DateTimeFormat dateTimeFormat, FormatOptions formatOptions) {
        if (!(obj instanceof Date) && !(obj instanceof Calendar) && !(obj instanceof Long)) {
            appendInvalid(this.out, obj, "%t" + dateTimeFormat.getChar());
        } else {
            this.out.append(String.format(MessageUtils.FORMAT_LOCALE, formatOptions.appendPrintfOptions(new StringBuilder("%")).append(formatOptions.shouldUpperCase() ? 'T' : 't').append(dateTimeFormat.getChar()).toString(), obj));
        }
    }

    @Override // com.google.common.flogger.parameter.ParameterVisitor
    public void visitMissing() {
        this.out.append("[ERROR: MISSING LOG ARGUMENT]");
    }

    @Override // com.google.common.flogger.parameter.ParameterVisitor
    public void visitNull() {
        this.out.append("null");
    }

    @Override // com.google.common.flogger.parser.MessageBuilder
    public StringBuilder buildImpl() {
        getParser().unescape(this.out, getMessage(), this.literalStart, getMessage().length());
        return this.out;
    }
}
