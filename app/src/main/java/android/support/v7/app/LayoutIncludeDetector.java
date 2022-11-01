package android.support.v7.app;

import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
class LayoutIncludeDetector {
    private final Deque mXmlParserStack = new ArrayDeque();

    private static boolean isParserOutdated(XmlPullParser xmlPullParser) {
        if (xmlPullParser != null) {
            try {
                if (xmlPullParser.getEventType() != 3) {
                    return xmlPullParser.getEventType() == 1;
                }
                return true;
            } catch (XmlPullParserException e) {
                return true;
            }
        }
        return true;
    }

    private static XmlPullParser popOutdatedAttrHolders(Deque deque) {
        while (!deque.isEmpty()) {
            XmlPullParser xmlPullParser = (XmlPullParser) ((WeakReference) deque.peek()).get();
            if (isParserOutdated(xmlPullParser)) {
                deque.pop();
            } else {
                return xmlPullParser;
            }
        }
        return null;
    }

    private static boolean shouldInheritContext(XmlPullParser xmlPullParser, XmlPullParser xmlPullParser2) {
        if (xmlPullParser2 != null && xmlPullParser != xmlPullParser2) {
            try {
                if (xmlPullParser2.getEventType() == 2) {
                    return "include".equals(xmlPullParser2.getName());
                }
                return false;
            } catch (XmlPullParserException e) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean detect(AttributeSet attributeSet) {
        if (attributeSet instanceof XmlPullParser) {
            XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
            if (xmlPullParser.getDepth() == 1) {
                XmlPullParser popOutdatedAttrHolders = popOutdatedAttrHolders(this.mXmlParserStack);
                this.mXmlParserStack.push(new WeakReference(xmlPullParser));
                return shouldInheritContext(xmlPullParser, popOutdatedAttrHolders);
            }
            return false;
        }
        return false;
    }
}
