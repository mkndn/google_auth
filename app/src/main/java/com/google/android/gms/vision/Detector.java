package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Frame;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Detector {
    private Processor processor;
    private final Object processorLock = new Object();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Detections {
        private final SparseArray detectedItems;
        private final Frame.Metadata frameMetadata;
        private final boolean isOperational;

        public Detections(SparseArray sparseArray, Frame.Metadata metadata, boolean z) {
            this.detectedItems = sparseArray;
            this.frameMetadata = metadata;
            this.isOperational = z;
        }

        public SparseArray getDetectedItems() {
            return this.detectedItems;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Processor {
        void receiveDetections(Detections detections);

        void release();
    }

    public abstract SparseArray detect(Frame frame);

    protected boolean hasProcessor() {
        boolean z;
        synchronized (this.processorLock) {
            z = this.processor != null;
        }
        return z;
    }

    public boolean isOperational() {
        return true;
    }

    protected void processReceivedDetections(Detections detections) {
        synchronized (this.processorLock) {
            Processor processor = this.processor;
            if (processor == null) {
                throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
            }
            processor.receiveDetections(detections);
        }
    }

    public void receiveFrame(Frame frame) {
        Frame.Metadata metadata = new Frame.Metadata(frame.getMetadata());
        metadata.makeUpright();
        processReceivedDetections(new Detections(detect(frame), metadata, isOperational()));
    }

    public void release() {
        synchronized (this.processorLock) {
            Processor processor = this.processor;
            if (processor != null) {
                processor.release();
                this.processor = null;
            }
        }
    }

    public boolean setFocus(int i) {
        return true;
    }

    public void setProcessor(Processor processor) {
        synchronized (this.processorLock) {
            Processor processor2 = this.processor;
            if (processor2 != null) {
                processor2.release();
            }
            this.processor = processor;
        }
    }
}
