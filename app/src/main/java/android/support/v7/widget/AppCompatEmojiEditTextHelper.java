package android.support.v7.widget;

import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.emoji2.viewsintegration.EmojiEditTextHelper;

/* compiled from: PG */
/* loaded from: classes.dex */
class AppCompatEmojiEditTextHelper {
    private final EmojiEditTextHelper mEmojiEditTextHelper;
    private final EditText mView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppCompatEmojiEditTextHelper(EditText editText) {
        this.mView = editText;
        this.mEmojiEditTextHelper = new EmojiEditTextHelper(editText, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyListener getKeyListener(KeyListener keyListener) {
        if (isEmojiCapableKeyListener(keyListener)) {
            return this.mEmojiEditTextHelper.getKeyListener(keyListener);
        }
        return keyListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEmojiCapableKeyListener(KeyListener keyListener) {
        return !(keyListener instanceof NumberKeyListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        try {
            boolean z = obtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_emojiCompatEnabled) ? obtainStyledAttributes.getBoolean(R$styleable.AppCompatTextView_emojiCompatEnabled, true) : true;
            obtainStyledAttributes.recycle();
            setEnabled(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo) {
        return this.mEmojiEditTextHelper.onCreateInputConnection(inputConnection, editorInfo);
    }

    void setEnabled(boolean z) {
        this.mEmojiEditTextHelper.setEnabled(z);
    }
}
