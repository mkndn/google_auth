package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new SignInConfigurationCreator();
    private final String mConsumerPkgName;
    private GoogleSignInOptions mGoogleSignInOptions;

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        this.mConsumerPkgName = Preconditions.checkNotEmpty(str);
        this.mGoogleSignInOptions = googleSignInOptions;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SignInConfiguration) {
            SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
            if (this.mConsumerPkgName.equals(signInConfiguration.getConsumerPkgName())) {
                GoogleSignInOptions googleSignInOptions = this.mGoogleSignInOptions;
                if (googleSignInOptions == null) {
                    if (signInConfiguration.getGoogleConfig() != null) {
                        return false;
                    }
                } else if (!googleSignInOptions.equals(signInConfiguration.getGoogleConfig())) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public String getConsumerPkgName() {
        return this.mConsumerPkgName;
    }

    public GoogleSignInOptions getGoogleConfig() {
        return this.mGoogleSignInOptions;
    }

    public int hashCode() {
        return new HashAccumulator().addObject(this.mConsumerPkgName).addObject(this.mGoogleSignInOptions).hash();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SignInConfigurationCreator.writeToParcel(this, parcel, i);
    }
}
