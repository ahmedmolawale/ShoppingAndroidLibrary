package ng.shoppi.androidfrontend.util;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by stackcode on 9/21/17.
 */

public class ShoppingUtility {

    private static final int REQUEST_LOCATION = 400;
    private static final int REQUEST_WRITE_STORAGE = 200;
    private static final int REQUEST_SMS = 300;

    private static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean hasStoragePermission(Context context) {
        //We check if we have permission to read or write to SD
        return (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public boolean hasSMSPermission(Context context) {
        //We check if we have permission to send SMS
        return (ContextCompat.checkSelfPermission(context,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED);
    }

    public void RequestSMSPermission(final Context context) {

        if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity) context,
                Manifest.permission.SEND_SMS)) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setMessage("Permission to make call is required by this app.")
                    .setTitle("Permission required");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    //make request
                    ActivityCompat.requestPermissions((AppCompatActivity) context,
                            new String[]{Manifest.permission.SEND_SMS},
                            REQUEST_SMS);
                }
            });

            android.app.AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            //make request
            ActivityCompat.requestPermissions((AppCompatActivity) context,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_SMS);
        }

    }

    public void RequestStoragePermission(final Context context) {

        if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity) context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setMessage("Permission to access the storage is required for this app to work.")
                    .setTitle("Permission required");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    //make request
                    ActivityCompat.requestPermissions((AppCompatActivity) context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_STORAGE);
                }
            });

            android.app.AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            //make request
            ActivityCompat.requestPermissions((AppCompatActivity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }

    }
    public static boolean hasLocationPermission(Context context) {
        //We check if we have permission to request location
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }

    public void RequestLocationPermission(final Context context) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity) context,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            builder.setMessage("Permission to get location is required by this app.")
                    .setTitle("Permission required");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    //make request
                    ActivityCompat.requestPermissions((AppCompatActivity) context,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_LOCATION);
                }
            });

            android.app.AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            //make request
            ActivityCompat.requestPermissions((AppCompatActivity) context,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }
}
