package androidx.transition;

import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Scene {
    public static Scene getCurrentScene(ViewGroup viewGroup) {
        return (Scene) viewGroup.getTag(R$id.transition_current_scene);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setCurrentScene(ViewGroup viewGroup, Scene scene) {
        viewGroup.setTag(R$id.transition_current_scene, scene);
    }

    public void exit() {
        throw null;
    }
}
