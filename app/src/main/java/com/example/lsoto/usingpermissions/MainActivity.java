package com.example.lsoto.usingpermissions;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
// Every Android app runs in a limited-access sandbox. If an app needs to use resources or
// information outside of its own sandbox, the app has to request the appropriate permission.
// You declare that your app needs a permission by listing the permission in the App Manifest.
// Depending on how sensitive the permission is, the system might grant the permission automatically,
// or the device user might have to grant the request.

// Your app only needs permissions for actions that it performs directly. Your app does not need
// permission if it is requesting that another app perform the task or provide the information.

// Beginning in Android 6.0 (API level 23), users grant permissions to apps while the app is running,
// not when they install the app. This approach streamlines the app install process, since the user
// does not need to grant permissions when they install or update the app.

// - If the device is running Android 5.1 or lower, or your app's target SDK is 22 or lower:
//      If you list a dangerous permission in your manifest, the user has to grant the permission
//      when they install the app; if they do not grant the permission, the system does not install
//      the app at all.
// - If the device is running Android 6.0 or higher, and your app's target SDK is 23 or higher:
//      The app has to list the permissions in the manifest, and it must request each dangerous
//      permission it needs while the app is running. The user can grant or deny each permission,
//      and the app can continue to run with limited capabilities even if the user denies a
//      permission request.
// - Beginning with Android 6.0 (API level 23), users can revoke permissions from any app at any time,
//      even if the app targets a lower API level. You should test your app to verify that it behaves
//      properly when it's missing a needed permission, regardless of what API level your app targets.
public class MainActivity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**** Check For Permissions ****/
    // If your app needs a dangerous permission, you must check whether you have that permission
    // every time you perform an operation that requires that permission. The user is always free
    // to revoke the permission, so even if the app used the camera yesterday, it can't assume it
    // still has that permission today.
    public int CheckCameraPermission(){

        Activity thisActivity = this;
        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.CAMERA);

        if (permissionCheck == PERMISSION_GRANTED){
            Log.d("", "");
        } else {
            Log.d("", "");
        }

        return permissionCheck;
    }

    // If your app doesn't already have the permission it needs, the app must call one of the
    // requestPermissions() methods to request the appropriate permissions. Your app passes the
    // permissions it wants, and also an integer request code that you specify to identify this
    // permission request. This method functions asynchronously: it returns right away, and after
    // the user responds to the dialog box, the system calls the app's callback method with the
    // results, passing the same request code that the app passed to requestPermissions().

    // In some circumstances, you might want to help the user understand why your app needs
    // a permission. Before you request a permission, you should consider providing an explanation
    // to the user. Keep in mind that you don't want to overwhelm the user with explanations;
    // if you provide too many explanations, the user might find the app frustrating and remove it.
    // One approach you might use is to provide an explanation only if the user has already
    // turned down that permission request. If a user keeps trying to use functionality that
    // requires a permission, but keeps turning down the permission request, that probably shows
    // that the user doesn't understand why the app needs the permission to provide that
    // functionality. In a situation like that, it's probably a good idea to show an explanation.
    public void RequestCameraPermission(){
        Activity thisActivity = this;
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(thisActivity,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
