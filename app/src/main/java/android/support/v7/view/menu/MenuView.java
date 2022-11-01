package android.support.v7.view.menu;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MenuView {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl menuItemImpl, int i);

        boolean prefersCondensedTitle();
    }

    void initialize(MenuBuilder menuBuilder);
}
