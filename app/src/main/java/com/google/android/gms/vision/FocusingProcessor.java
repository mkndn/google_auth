package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class FocusingProcessor implements Detector.Processor {
    private static final String TAG = "FocusingProcessor";
    private Detector detector;
    private int focusId;
    private Tracker tracker;
    private int maxGap = 3;
    private boolean focusActive = false;
    private int frameGap = 0;

    public FocusingProcessor(Detector detector, Tracker tracker) {
        this.detector = detector;
        this.tracker = tracker;
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void receiveDetections(Detector.Detections detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.frameGap == this.maxGap) {
                this.tracker.onDone();
                this.focusActive = false;
            } else {
                this.tracker.onMissing(detections);
            }
            this.frameGap++;
            return;
        }
        this.frameGap = 0;
        if (this.focusActive) {
            Object obj = detectedItems.get(this.focusId);
            if (obj != null) {
                this.tracker.onUpdate(detections, obj);
                return;
            } else {
                this.tracker.onDone();
                this.focusActive = false;
            }
        }
        int selectFocus = selectFocus(detections);
        Object obj2 = detectedItems.get(selectFocus);
        if (obj2 == null) {
            Log.w(TAG, "Invalid focus selected: " + selectFocus);
            return;
        }
        this.focusActive = true;
        this.focusId = selectFocus;
        this.detector.setFocus(selectFocus);
        this.tracker.onNewItem(this.focusId, obj2);
        this.tracker.onUpdate(detections, obj2);
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void release() {
        this.tracker.onDone();
    }

    public abstract int selectFocus(Detector.Detections detections);

    protected void setMaxGapFrames(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid max gap: " + i);
        }
        this.maxGap = i;
    }
}
