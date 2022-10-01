package wasif.fyp.smartrestaurant.Tabs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import wasif.fyp.smartrestaurant.GPSTracker;
import wasif.fyp.smartrestaurant.IntroLocationActivity;
import wasif.fyp.smartrestaurant.R;
import wasif.fyp.smartrestaurant.SignInActivity;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    MarkerOptions options = new MarkerOptions();
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    Button btnClosePopup;
    TextView r_name;
    public static Activity location;

    TextView order_name;
    Marker marker;
    GPSTracker gpsTracker;
    ArrayList markerPoints = new ArrayList();
    ImageView prfl_img;
    SharedPreferences mSharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }

    EditText Name, Number;

    Bitmap myBitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        gpsTracker = new GPSTracker(getActivity());
        mSharedPreferences = getActivity().getSharedPreferences(SignInActivity.PREFERENCE, Context.MODE_PRIVATE);
        Name = view.findViewById(R.id.name);
        Number = view.findViewById(R.id.no);
        Name.setText("" + mSharedPreferences.getString(IntroLocationActivity.PREF_USERNAME, ""));
        Number.setText("" + mSharedPreferences.getString(IntroLocationActivity.PREF_PHONE, ""));
        prfl_img = (ImageView) view.findViewById(R.id.profile_image);
        prfl_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnableRuntimePermission();
            }
        });


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void EnableRuntimePermission() {

        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                isStoragePermissionGranted();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }


    public boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(getActivity(), CAMERA);
        int result1 = ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE);


        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 1);
//        Toast.makeText(getApplicationContext(), "NOT STARTED", Toast.LENGTH_SHORT).show();
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {

            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 13);
            return true;


        } else { //permission is automatically granted on sdk<23 upon installation
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 13);

            Log.v("TAG", "Permission is granted");
            return true;
        }
    }


    String filePath = "";

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {

            case 13:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                        Uri selectedImage = getImageUri(getActivity(), photo);
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        filePath = cursor.getString(columnIndex);
                        cursor.close();
                        Bitmap thumbnail = (BitmapFactory.decodeFile(filePath));
//                                image_check = 1;
                        myBitmap = thumbnail;
                        prfl_img.setImageBitmap(thumbnail);
                    } else {
                        Uri selectedImage = imageReturnedIntent.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        filePath = cursor.getString(columnIndex);
                        cursor.close();
                        Bitmap thumbnail = (BitmapFactory.decodeFile(filePath));
//                                image_check = 1;
                        myBitmap = thumbnail;
                        prfl_img.setImageBitmap(thumbnail);
                    }     //pic.setImageDrawable(d);
                } else {
                    //  setContentView(R.layout.activity_feed_back);
                }
                break;

        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
