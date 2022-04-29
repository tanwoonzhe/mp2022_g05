package com.example.laibrary.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.laibrary.LoginActivity;
import com.example.laibrary.R;
import com.example.laibrary.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ImageView profilePic;
    private TextView profileName, profileAge, profileEmail;
    private Button profileUpdate, changePassword, deleteAcc, logout;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    FirebaseUser firebaseUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        profilePic = view.findViewById(R.id.ivProfilePic);
        profileAge = view.findViewById(R.id.tvProfileAge) ;
        profileName = view.findViewById(R.id.tvProfileName);
        profileEmail = view.findViewById(R.id.tvProfileEmail);
        profileUpdate = view.findViewById(R.id.btnProfileUpdate);
        changePassword = view.findViewById(R.id.btnChangePassword);
        deleteAcc = view.findViewById(R.id.btnDeleteAcc);
        logout = view.findViewById(R.id.btnProfileLogout);
        //DatabaseReference userID = firebaseDatabase.getReference("User Info").child(firebaseAuth.getUid());
        //String stringuserid = userID.toString();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        DatabaseReference databaseReference = firebaseDatabase.getReference("User Info").child(firebaseAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePic);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserProfile userProfile = snapshot.getValue(UserProfile.class);
                profileName.setText("Name: " + userProfile.getUserName());
                profileAge.setText("Age: " + userProfile.getUserAge());
                profileEmail.setText("Email: " + userProfile.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpdateProfile.class));
            }
        });

        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Are You Sure?");
                dialog.setMessage("Deleting this account will result in completely removing your account from the system" +
                        "and you won't be able to access the app");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*
                        Toast.makeText(ProfileActivity.this, "Before delete", Toast.LENGTH_SHORT).show();
                        DatabaseReference deleteUserInfo = FirebaseDatabase.getInstance().
                                getReference("User Info").child(firebaseAuth.getUid());
                        deleteUserInfo.removeValue();

                         */
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    Toast.makeText(getActivity(), "Account deleted", Toast.LENGTH_SHORT).show();
                                    firebaseAuth.signOut();
                                    getActivity().finish();
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                }else{
                                    Toast.makeText(getActivity(), "cannot delete", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getActivity(), task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpdataPassword.class));
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
