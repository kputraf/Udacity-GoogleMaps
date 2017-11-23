package com.example.krisnaputraf.myapplication;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,OnStreetViewPanoramaReadyCallback {

    static final CameraPosition SURABAYA = CameraPosition.builder()
            .target(new LatLng(-7.260228, 112.746906))
            .zoom(16)
            .bearing(0)
            .build();
    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204, -122.3491))
            .zoom(17)
            .bearing(0)
            .build();
    static final CameraPosition TOKYO = CameraPosition.builder()
            .target(new LatLng(35.6895, 139.6917))
            .zoom(17)
            .bearing(90)
            .build();
    GoogleMap m_map;
    boolean mapReady = false;

   MarkerOptions kos,smkTelkom,roker,bakso,doeloer,masjid;
   LatLng kosLtLg = new LatLng(-7.979316, 112.657870);
   LatLng smkTelkomLtLg = new LatLng(-7.977164, 112.658760);
   LatLng rokerLtLg = new LatLng(-7.9760753,112.6628679);
   LatLng baksoLtLg = new LatLng(-7.979885, 112.657127);
   LatLng doeloerLtLg = new LatLng(-7.9747464,112.6600879);
   LatLng masjidLtLg = new LatLng(-7.9744279,112.6591168);
    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetview);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);


        kos = new MarkerOptions()
                .position(new LatLng(-7.979316, 112.657870))
                .title("Kos")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_home));
        smkTelkom = new MarkerOptions()
                .position(new LatLng(-7.977164, 112.658760))
                .title("Sekolah")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_school));
        roker = new MarkerOptions()
                .position(new LatLng(-7.9760753,112.6628679))
                .title("Ayam Goreng Roker")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_restaurant));
        bakso = new MarkerOptions()
                .position(new LatLng(-7.979885, 112.657127))
                .title("Bakso Solo")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_restaurant));
        doeloer = new MarkerOptions()
                .position(new LatLng(-7.9747464,112.6600879))
                .title("Bebek Doeloer")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_restaurant));
        masjid = new MarkerOptions()
                .position(new LatLng(-7.9744279,112.6591168))
                .title("Masjid Manarul Islam")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_masjid));

        Button btnSeattle = (Button) findViewById(R.id.btnSeattle);
        btnSeattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(SEATTLE);
            }
        });

        Button btnDublin = (Button) findViewById(R.id.btnSurabaya);
        btnDublin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (mapReady)
                     flyTo(SURABAYA);
             }
        });

        Button btnTokyo = (Button) findViewById(R.id.btnTokyo);
        btnTokyo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (mapReady)
                     flyTo(TOKYO);
             }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;
        m_map.addMarker(kos);
        m_map.addMarker(smkTelkom);
        m_map.addMarker(roker);
        m_map.addMarker(bakso);
        m_map.addMarker(doeloer);
        m_map.addPolyline(new PolylineOptions().geodesic(true)
                 .add(kosLtLg)
                 .add(baksoLtLg)
                 .add(rokerLtLg)
                 .add(doeloerLtLg)
                 .add(smkTelkomLtLg)
                 .add(kosLtLg)
        );
        flyTo(SURABAYA);
    }

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(new LatLng(-7.977274,112.659125));
        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                        .bearing(-35)
                        .build();
        streetViewPanorama.animateTo(camera,4000);
    }
}