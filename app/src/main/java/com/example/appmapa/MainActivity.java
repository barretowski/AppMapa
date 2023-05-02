package com.example.appmapa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button btApontar;
    private MapView mapView;
    private GoogleMap googleMap;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btApontar = findViewById(R.id.btApontar);
        mapView = findViewById(R.id.mapView);
        btApontar.setOnClickListener(e->{
            posicionar();
        });

        //configurar o mapa
        mapView.setClickable(true);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {//evento que ocorre quando uma activity dceve ser destruida e recriada, exemplo: dispositov é rotacionado
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle (MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle (MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle); // informa ao mapView que ocorreu um evento onSaveInstace
    }


    private void posicionar() {
        //PONTO CENTRAL DA CIDADE
        LatLng latLong = new LatLng(-22.1244244, -51.3860479);

        //MARCADOR NO MAPA
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLong);
        this.googleMap.addMarker(markerOptions);

        //MARCADOR UNOESTE CAMPUS 1
        latLong = new LatLng(-22.1332654, -51.4051404);
        markerOptions.position(latLong);
        this.googleMap.addMarker(markerOptions);

        this.googleMap.moveCamera
                (CameraUpdateFactory.newLatLng(latLong));

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMinZoomPreference(12);
        this.googleMap.setIndoorEnabled(true);

        UiSettings settings = this.googleMap.getUiSettings();
        settings.setIndoorLevelPickerEnabled(true);

        settings.setMyLocationButtonEnabled(true);
        settings.setMapToolbarEnabled(true);
        settings.setCompassEnabled(true);
        settings.setZoomControlsEnabled(true);



    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}