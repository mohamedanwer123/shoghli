package com.example.cm.mywork.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cm.mywork.Models.ProductData;
import com.example.cm.mywork.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddProduct extends AppCompatActivity {

    String phone ;
    String pn , pi , pc , pp , pd ;
    CircleImageView img;
    EditText name , price , des , company;
    Uri uri=null;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ProductData productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        init();
    }

    private void init() {
        img = findViewById(R.id.addproductactivity_img);
        name =  findViewById(R.id.addproductactivity_name);
        price = findViewById(R.id.addproductactivity_price);
        company = findViewById(R.id.addproductactivity_company);
        des = findViewById(R.id.addproductactivity_des);
        phone = getIntent().getStringExtra("phone");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void Save(View view) {

       pn = name.getText().toString().trim();
       pp = price.getText().toString().trim();
       pc = company.getText().toString().trim();
       pd = des.getText().toString().trim();

       if(pn.length() ==0 || pp.length() ==0 || pc.length() ==0 ||pd.length() ==0 || uri==null)
       {
           Toast.makeText(this, "Enter Data", Toast.LENGTH_LONG).show();
       }else
       {
           final ProgressDialog progressDialog = new ProgressDialog(AddProduct.this);
           progressDialog.setTitle("Wait until product save");
           progressDialog.show();
           progressDialog.setCancelable(false);
           storageReference.child(System.currentTimeMillis()+".jpg").putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   productData = new ProductData(taskSnapshot.getDownloadUrl().toString(),pn,pp,pc,pd);
                   databaseReference.child(phone+"_Products").push().setValue(productData);
                   progressDialog.dismiss();
                   name.setText("");
                   price.setText("");
                   company.setText("");
                   des.setText("");
               }
           }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                   progressDialog.setMax((int) (taskSnapshot.getBytesTransferred()*100));
               }
           });
       }
    }

    public void selectImage(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==123 && resultCode == RESULT_OK)
        {
            uri = data.getData();
            img.setImageURI(uri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
