package com.ganonalabs.munir.electrtech.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.ganonalabs.munir.electrtech.BaseActivity;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.model.AppUser;
import com.ganonalabs.munir.electrtech.utils.ProfileAlertBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.ganonalabs.munir.electrtech.Constants.permisionList;
import static com.ganonalabs.munir.electrtech.Constants.permsRequestCode;

public class UserProfileActivity extends BaseActivity {

    private Toolbar toolbar;
    private String name, email, uid, editedEmail, userProvider;

    private TextView userName, userEmail, changeEmail, userLocation, phoneNumber, userAddress;
    private ImageView userProfilePhoto;
    private ProfileAlertBuilder profileAlertBuilder;
    private NestedScrollView profileScroll;
    private Button btnChangePic, button_change_password;
    private DatabaseReference databaseReference, dbUserRef;
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public Map<String, Object> userDataMap = new HashMap<String, Object>();
    public final int REQUEST_CODE_PICKER = 123;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference, profilepicReference;
    public AppUser appUser;

    private Snackbar snackbar;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user_profile);
        //Toolbar toolbar = findViewById(R.id.job_schedule_toolbar);
       // setSupportActionBar(toolbar);

        getSupportActionBar(); //.setDisplayHomeAsUpEnabled(true)
        i = getIntent();
        appUser = (AppUser)i.getSerializableExtra("USERDATA");

        UserProfileActivity.super.requestAppPermissions(permisionList, R.string.runtime_permissions_txt, permsRequestCode);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        dbUserRef = databaseReference.child("users");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getInstance().getReference();
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userProfilePhoto = findViewById(R.id.userProfilePhoto);
        phoneNumber = findViewById(R.id.phoneNumber);
        button_change_password = findViewById(R.id.button_change_password);
        profileScroll = findViewById(R.id.profileScroll);
        userAddress = findViewById(R.id.userAdress);
        if(userAddress != null){
            userAddress.setHorizontallyScrolling(false);
            userAddress.setLines(2);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(appUser!=null) {
            userProvider = appUser.getProviderId();

            dbUserRef.orderByKey().equalTo(appUser.getUid()).limitToFirst(1).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        appUser = postSnapshot.getValue(AppUser.class);

                        if(appUser.getEmail()==null){
                            userEmail.setText("");
                        }
                        else{
                            userEmail.setText(appUser.getEmail());
                        }
                        if(appUser.getName()==null){
                            userName.setText("");
                        }
                        else{
                            userName.setText(appUser.getName());
                        }
                        if(appUser.getPhoneNumber()==null){
                            phoneNumber.setText("");
                        }
                        else{
                            phoneNumber.setText(appUser.getPhoneNumber().toString());
                        }
                        if(appUser.getAddress()==null){
                            userAddress.setText("");
                        }
                        else{
                            userAddress.setText(appUser.getAddress());
                        }

                        if (userProvider.equals("password")) {
                            button_change_password.setVisibility(View.VISIBLE);
                        }
                        Glide.with(getApplicationContext()).load(appUser.getPhotoURL()).into(userProfilePhoto);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }


    public void editProfile(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Context context = UserProfileActivity.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText displayNameEditText = new EditText(context);
        displayNameEditText.setHint(R.string.name_hint);

        if (appUser.getName() == null) {
            displayNameEditText.setHint(R.string.name_hint);
        } else {
            displayNameEditText.setText(appUser.getName());
        }




        final EditText phoneEditText = new EditText(context);
        phoneEditText.setHint(R.string.hint_phone_number);
        phoneEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        if (appUser.getPhoneNumber() == null) {
            phoneEditText.setHint(R.string.hint_phone_number);
        } else {
            phoneEditText.setText(appUser.getPhoneNumber());
        }

        final EditText emailEditText = new EditText(context);
        emailEditText.setHint(R.string.email_hint);
        if (appUser.getEmail() == null) {
            emailEditText.setHint(R.string.email_hint);
        } else {

            emailEditText.setText(appUser.getEmail());
        }

        final EditText locationEditText = new EditText(context);
        locationEditText.setHint(R.string.location_hint);
        if (appUser.getAddress() == null) {
            locationEditText.setHint(R.string.location_hint);
        } else {
            locationEditText.setText(appUser.getAddress());
        }

        layout.addView(displayNameEditText);
        if (userProvider.equals("google.com") || userProvider.equals("password") || userProvider.equals("facebook.com")) {
            layout.addView(phoneEditText);
        }
        if (userProvider.equals("phone")) {
            layout.addView(emailEditText);
        }
        layout.addView(locationEditText);
        //layout.addView(locationEditText);

        alert.setIcon(R.drawable.ess_logo);
        alert.setTitle("ESS");
        alert.setMessage("Update your information");
        alert.setView(layout);

        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                //Editable YouEditTextValue = edittext.getText();
                //OR
                //String YouEditTextValue = edittext.getText().toString();
                if (haveNetworkConnection()) {
                    if (displayNameEditText.getText() == null || displayNameEditText.getText().length() <= 0) {
                        showSnack(profileScroll, "Name can not be empty");
                        return;
                    }

                    if (phoneEditText.getText() == null  || phoneEditText.getText().length() <= 0) {
                        showSnack(profileScroll, "Phone number can not be empty");
                        return;
                    }
                    String editedName = (displayNameEditText.getText().toString().length() > 0) ? displayNameEditText.getText().toString() : userDataMap.get("name").toString();
                    String editedPhone = (phoneEditText.getText().toString().length() > 0) ? phoneEditText.getText().toString() : userDataMap.get("phoneNumber").toString();
                    String editedEmail = emailEditText.getText().toString();
                    String editedLocation = (locationEditText.getText().toString().length() > 0) ? locationEditText.getText().toString() : userDataMap.get("address").toString();
                   // String editedLocation = locationEditText.getText().toString();
                    try {
                        dbUserRef.child(user.getUid()).child("name").setValue(editedName);
                        dbUserRef.child(user.getUid()).child("phoneNumber").setValue(editedPhone);
                        dbUserRef.child(user.getUid()).child("address").setValue(editedLocation);
                        if (!editedEmail.isEmpty() && !validEmail(editedEmail)) {
                            showSnack(profileScroll, "Please insert a correct Email");
                            return;
                        } else {
                            dbUserRef.child(user.getUid()).child("email").setValue(editedEmail);
                        }
                        // dbUserRef.child(user.getUid()).child("location").setValue(editedLocation);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                else{
                    noConnectionSnack(false,profileScroll);
                }
            }


        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }


    private boolean validEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }



    public void updatePassword(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        edittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
        profileAlertBuilder = new ProfileAlertBuilder(this);

        alert = profileAlertBuilder.alertForEditText("",
                "MySymphony Profile",
                "Change your Password",
                edittext);


        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                //Editable YouEditTextValue = edittext.getText();
                //OR
                //String YouEditTextValue = edittext.getText().toString();
                if(haveNetworkConnection()){
                    if (edittext.getText() == null || edittext.getText().length() == 0) {
                        showSnack(profileScroll, "Password could not be empty");
                        return;
                    }
                    if (edittext.getText().length() < 6) {
                        showSnack(profileScroll, "Minimum password length is 6 character");
                        return;
                    }
                    String editedPassword = edittext.getText().toString();
                    user.updatePassword(editedPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("User_Password_Change:", "User password updated.");
                                showSnack(profileScroll, "Password changed successfully");
                            }
                        }
                    });
                }
                else{
                    noConnectionSnack(false, profileScroll);
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });
        AlertDialog d = alert.show();
        if (edittext.getText().toString() == null || edittext.getText().toString().isEmpty()) {
            d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
        d.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);


    }



}
