package com.example.cm.mywork.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cm.mywork.Activites.Home;
import com.example.cm.mywork.Activites.MainActivity;
import com.example.cm.mywork.Activites.UpdateUserData;
import com.example.cm.mywork.Models.UserData;
import com.example.cm.mywork.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    TextView name , job , phone , company;
    CircleImageView img;
    ImageView selectImage;
    Button update , logout;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Uri uri;
    AlertDialog alertDialog;
    String n,j,ph,c,i;

    public Profile() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        putData();

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,123);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Log Out");
                builder.setMessage("Are You Want Leave This Page");
                builder.setCancelable(false);
                builder.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });

                 alertDialog = builder.create();
                alertDialog.show();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UpdateUserData.class);
                intent.putExtra("name",n);
                intent.putExtra("job",j);
                intent.putExtra("phone",ph);
                intent.putExtra("company",c);
                intent.putExtra("image",i);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    public void init(View view)
    {
        name = view.findViewById(R.id.profileFragment_name);
        job = view.findViewById(R.id.profileFragment_job);
        phone = view.findViewById(R.id.profileFragment_phone);
        company = view.findViewById(R.id.profileFragment_company);
        img = view.findViewById(R.id.profileFragment_img);
        selectImage = view.findViewById(R.id.profileFragment_selectimg);
        update = view.findViewById(R.id.profileFragment_update);
        logout = view.findViewById(R.id.profileFragment_logout);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

    }

    int f=0;
    public  void putData()
    {
        n = getArguments().getString("name");
        j = getArguments().getString("job");
        ph = getArguments().getString("phone");
        c = getArguments().getString("company");
        i = getArguments().getString("image");

        name.setText(n);
        job.setText(j);
        phone.setText(ph);
        company.setText(c);



        if(i.length() ==0 || i.equals("") || i=="")
        {
            img.setImageResource(R.drawable.pro);
        }else {

            Picasso.get().load(Uri.parse(i)).into(img);
            f = 1;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 123 && resultCode == getActivity().RESULT_OK)
        {
            uri = data.getData();
            img.setImageURI(uri);
            f = 1;
            final Query query = databaseReference.child("Users_Data").orderByChild("phone").equalTo(getArguments().getString("phone"));
            storageReference.child(System.currentTimeMillis()+"_ProfileImage").putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot d : dataSnapshot.getChildren())
                            {
                                UserData userData = d.getValue(UserData.class);
                                userData.setImage(taskSnapshot.getDownloadUrl().toString());
                                 d.getRef().setValue(userData);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            });
        }
    }
}
