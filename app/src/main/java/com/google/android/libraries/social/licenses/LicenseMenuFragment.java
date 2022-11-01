package com.google.android.libraries.social.licenses;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LicenseMenuFragment extends Fragment implements LoaderManager.LoaderCallbacks {
    private LicenseSelectionListener licenseSelectionListener;
    private ArrayAdapter listAdapter;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface LicenseSelectionListener {
        void onLicenseSelected(License license);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-google-android-libraries-social-licenses-LicenseMenuFragment  reason: not valid java name */
    public /* synthetic */ void m380x113ed0cc(AdapterView adapterView, View view, int i, long j) {
        License license = (License) adapterView.getItemAtPosition(i);
        LicenseSelectionListener licenseSelectionListener = this.licenseSelectionListener;
        if (licenseSelectionListener != null) {
            licenseSelectionListener.onLicenseSelected(license);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof LicenseSelectionListener) {
            this.licenseSelectionListener = (LicenseSelectionListener) parentFragment;
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity instanceof LicenseSelectionListener) {
            this.licenseSelectionListener = (LicenseSelectionListener) activity;
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new LicenseLoader(getActivity());
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.libraries_social_licenses_license_menu_fragment, viewGroup, false);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getActivity().getSupportLoaderManager().destroyLoader(54321);
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.licenseSelectionListener = null;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader loader) {
        this.listAdapter.clear();
        this.listAdapter.notifyDataSetChanged();
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        this.listAdapter = new ArrayAdapter(activity, R$layout.libraries_social_licenses_license, R$id.license, new ArrayList());
        activity.getSupportLoaderManager().initLoader(54321, null, this);
        ListView listView = (ListView) view.findViewById(R$id.license_list);
        listView.setAdapter((ListAdapter) this.listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.libraries.social.licenses.LicenseMenuFragment$$ExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view2, int i, long j) {
                LicenseMenuFragment.this.m380x113ed0cc(adapterView, view2, i, j);
            }
        });
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader loader, List list) {
        this.listAdapter.clear();
        this.listAdapter.addAll(list);
        this.listAdapter.notifyDataSetChanged();
    }
}
