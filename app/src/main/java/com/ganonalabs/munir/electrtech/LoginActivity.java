package com.ganonalabs.munir.electrtech;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.common.Scopes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ganonalabs.munir.electrtech.Constants.RC_SIGN_IN;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private RelativeLayout relativeLayout;
    private AuthUI.IdpConfig googleIdp;
    private AuthUI.IdpConfig facebookIdp;
    ArrayList<Object> userData, userProviders;
    private LinearLayout llayout;
    private List<AuthUI.IdpConfig> providers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        llayout = findViewById(R.id.login_activity_area);

        firebaseAuth = FirebaseAuth.getInstance();

         providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                 new AuthUI.IdpConfig.GoogleBuilder().build(),
                 new AuthUI.IdpConfig.EmailBuilder().build()
              //  new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                //new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                );
        //new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
        userData = new ArrayList<>();
        userProviders = new ArrayList<>();
        if(firebaseUser != null){
            //Signed in, launch the Sign In Activity
            // Name, email address, and profile photo Url
            userData.add(firebaseUser.getDisplayName());
            userData.add(firebaseUser.getEmail());
            userData.add(firebaseUser.getPhotoUrl());
            userData.add(firebaseUser.getPhoneNumber());
            for (UserInfo profile : firebaseUser.getProviderData()) {
                userProviders.add(profile);
            }
            //pl = (Parcelable)userData;
            Intent intent = new Intent(this, Main2Activity.class);
            //intent.putParcelableArrayListExtra("userdata", pl);
            // startActivity(new Intent(this, ProfileActivity.class));
            startActivity(intent);
            finish();
            return;
        }
        else{
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setLogo(R.drawable.ic_electrtech)
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        String n ="";
        if(requestCode == RC_SIGN_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if(resultCode== ResultCodes.OK){
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                startActivity(new Intent(this,Main2Activity.class)
                        .putExtra("my_token", idpResponse.getIdpToken()));
                finish();
                return;
                }

            else{
                // Sign in failed
                if(response==null){
                    // AppUser pressed back button
                    Snackbar.make(relativeLayout,"Signin Cancelled", Snackbar.LENGTH_SHORT);
                }
                if (response!= null && response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Snackbar.make(relativeLayout,"No Internet Connection", Snackbar.LENGTH_SHORT);
                    Log.d("Login Info" , "No Internet Connection");
                    new MaterialDialog.Builder(this)
                            .title("Login failed")
                            .content("No Internet Connection")
                            .neutralText("Close")
                            .onNeutral(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                }
                            })
                            .show();


                    return;
                }
                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Snackbar.make(relativeLayout,"No Internet Connection", Snackbar.LENGTH_SHORT);
                    return;
                }
                if (response==null || response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Snackbar.make(relativeLayout,"Unknown Error", Snackbar.LENGTH_SHORT);
                    Log.d("Login Info" , "Unknown Error");

                    new MaterialDialog.Builder(this)
                            .title("Login failed")
                            .content("Unknown error occurred")
                            .neutralText("Close")
                            .onNeutral(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                }
                            })
                            .show();

                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Snackbar.make(relativeLayout,"Unknown Error", Snackbar.LENGTH_SHORT);
                    return;
                }
            }
        }
    }
}
