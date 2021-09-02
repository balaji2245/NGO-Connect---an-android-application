package com.example.bmrd.stesbuddy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback, LocationListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    LocationRequest request;

    private Button mFinishBTN;



    GoogleApiClient client;

    public static LatLng latLngCurrent;
    public static double Lat;
    public static double Lng;
    public int statusOfMap;

    private Task<Void> mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        mFinishBTN=(Button) findViewById(R.id.finishBTN);



        mFinishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent=new Intent(MapsActivity3.this,REST24Activity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();

            }
        });




    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        client.connect();



    }



    @Override
    public void onLocationChanged(Location location) {

        if(location==null){
            Toast.makeText(MapsActivity3.this,"Sorry..Location not found",Toast.LENGTH_SHORT).show();
        }
        else {


            Lat=location.getLatitude();
            Lng=location.getLongitude();

            latLngCurrent = new LatLng(location.getLatitude(), location.getLongitude());

            //  Toast.makeText(MapsActivity.this, latLngCurrent + "2", Toast.LENGTH_LONG).show();


            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLngCurrent, 15);
            mMap.animateCamera(update);


            MarkerOptions options = new MarkerOptions();
            options.position(latLngCurrent);
            options.title("Current Location");
            mMap.addMarker(options);

            statusOfMap=1;


            sendLat();
            sendLng();

            //callActivity();


            // Putting location details on cloud

            FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

            String uid=current_user.getUid();

            mDatabase=FirebaseDatabase.getInstance().getReference().child("future_ngo").child(uid).child("lat").setValue(Lat+"");
            mDatabase=FirebaseDatabase.getInstance().getReference().child("future_ngo").child(uid).child("lon").setValue(Lng+"");



            /*
            mDatabase= FirebaseDatabase.getInstance().getReference().child("admin").child("Temp_loc1");

            System.out.println(Lat+"++++++++++++++++++++");

            HashMap<String,String> VolunteersMap= new HashMap<>();
            VolunteersMap.put("Lat",Lat+"");
            VolunteersMap.put("Lan",Lng+"");
            //VolunteersMap.put("Email_id",email);

            mDatabase.setValue(VolunteersMap);


*/
            ///calling distance calculating method





        }

    }



    public static double sendLat(){
        return Lat;
    }

    public static double sendLng(){
        return Lng;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        request = new LocationRequest().create();
        //request.setInterval(500000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(MapsActivity3.this,"Please turn on location",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(MapsActivity3.this,"Please turn on GPS",Toast.LENGTH_LONG).show();
    }
}



