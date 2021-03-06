package app.easyeducation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTestAnswer extends AppCompatActivity {
    Button add,share;
    Intent dataHolder;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String name,date,module,usernom,userprenom;
    TextView testname,modulename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test_answer);

        initWidget();

        Bundle extras=getIntent().getExtras();
        name = extras.getString("Testname");
         date = extras.getString("Testdate");
         module = extras.getString("TestModule");


         testname.setText(name);
        modulename.setText(module);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        usernom=sharedPreferences.getString("nomuser","");
        userprenom=sharedPreferences.getString("prenomuser","");


        Toast.makeText(getApplicationContext(),usernom,Toast.LENGTH_LONG).show();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("TestAnswers");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Selectpdf();
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadPDFToFirebase(dataHolder.getData());
            }
        });

    }

    private void initWidget() {

        add=findViewById(R.id.add_file_test);
        share=findViewById(R.id.share_test);
        testname=findViewById(R.id.test_name_replyed);
        modulename=findViewById(R.id.modulename_addtestanswer);
    }



    private void Selectpdf() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            dataHolder=data;

        }
    }



    private void UploadPDFToFirebase(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("file is loading ...");
        progressDialog.show();

        StorageReference reference=storageReference.child("TestAnswer "+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri=uriTask.getResult();

                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


                TestAnswer testAnswer=new TestAnswer(name,date,module,usernom,userprenom,uri.toString());



                databaseReference.push().setValue(testAnswer);
                Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();



            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("file Uploaded .. "+(int) progress+"%");
            }
        });
    }








}