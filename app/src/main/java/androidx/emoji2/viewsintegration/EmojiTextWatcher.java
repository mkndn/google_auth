package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* compiled from: PG */
/* loaded from: classes.dex */
final class EmojiTextWatcher implements TextWatcher {
    private final EditText mEditText;
    private final boolean mExpectInitializedEmojiCompat;
    private EmojiCompat.InitCallback mInitCallback;
    private int mMaxEmojiCount = Integer.MAX_VALUE;
    private int mEmojiReplaceStrategy = 0;
    private boolean mEnabled = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class InitCallbackImpl extends EmojiCompat.InitCallback {
        private final Reference mViewRef;

        InitCallbackImpl(EditText editText) {
            this.mViewRef = new WeakReference(editText);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmojiTextWatcher(EditText editText, boolean z) {
        this.mEditText = editText;
        this.mExpectInitializedEmojiCompat = z;
    }

    private EmojiCompat.InitCallback getInitCallback() {
        if (this.mInitCallback == null) {
            this.mInitCallback = new InitCallbackImpl(this.mEditText);
        }
        return this.mInitCallback;
    }

    static void processTextOnEnablingEvent(EditText editText, int i) {
        if (i == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            EmojiCompat.get().process(editableText);
            EmojiInputFilter.updateSelection(editableText, selectionStart, selectionEnd);
        }
    }

    private boolean shouldSkipForDisabledOrNotConfigured() {
        return (this.mEnabled && (this.mExpectInitializedEmojiCompat || EmojiCompat.isConfigured())) ? false : true;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.mEditText.isInEditMode() && !shouldSkipForDisabledOrNotConfigured() && i2 <= i3 && (charSequence instanceof Spannable)) {
            switch (EmojiCompat.get().getLoadState()) {
                case 0:
                case 3:
                    EmojiCompat.get().registerInitCallback(getInitCallback());
                    return;
                case 1:
                    EmojiCompat.get().process((Spannable) charSequence, i, i + i3, this.mMaxEmojiCount, this.mEmojiReplaceStrategy);
                    return;
                case 2:
                default:
                    return;
            }
        }
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            if (this.mInitCallback != null) {
                EmojiCompat.get().unregisterInitCallback(this.mInitCallback);
            }
            this.mEnabled = z;
            if (z) {
                processTextOnEnablingEvent(this.mEditText, EmojiCompat.get().getLoadState());
            }
        }
    }
}
