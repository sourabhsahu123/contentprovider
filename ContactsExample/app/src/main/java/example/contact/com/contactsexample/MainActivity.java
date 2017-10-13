package example.contact.com.contactsexample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Cursor mCursor;
    ArrayList<PhoneNo>pList;
    ListView mContactlist;
    Context con;
 final  int MY_PERMISSIONS_REQUEST_READ_CONTACTS=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=this;
        mContactlist=(ListView)findViewById(R.id.contactList);

        pList = new ArrayList<PhoneNo>();
// For Android 6.0 and Above you have to request the permissions

        //Check whether the application has read contact permission or not
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

               //If Permission is not there ask for permission

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

        }

        else
        {
            //If permission is there just get the cursor

            //Initializing the Cursor
            mCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                    null,
                    null,
                    null
            );

            //            CONTENT_URI,                        // The content URI to access table
            //                mProjection,                        // The columns to return for each row
            //               mSelectionClause                    // Selection criteria
            //               mSelectionArgs,                     // Selection criteria
            //               mSortOrder);                        // The sort order for the returned rows

            // permission was granted, yay! Do the
            // contacts-related task you need to do.

//read the contacts
            if(mCursor!=null)
            {
                if(mCursor.getCount()>=1)
                {
                    while (mCursor.moveToNext())
                    {
                        PhoneNo p = new PhoneNo(mCursor.getString(1),mCursor.getString(0));
                        pList.add(p);


                    }
                }

                ContactAdapter ca = new ContactAdapter(con,pList);
                mContactlist.setAdapter(ca);
            }
        }


        //Iterating the Cursor

        //Declaring the Adapter




    }

    //handle the permissionsss
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Initializing the Cursor
                    mCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                            null,
                            null,
                            null
                    );

                    //            CONTENT_URI,                        // The content URI to access table
                    //                mProjection,                        // The columns to return for each row
                    //               mSelectionClause                    // Selection criteria
                    //               mSelectionArgs,                     // Selection criteria
                    //               mSortOrder);                        // The sort order for the returned rows

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.


                    if(mCursor!=null)
                    {
                        if(mCursor.getCount()>=1)
                        {
                            while (mCursor.moveToNext())
                            {
                                PhoneNo p = new PhoneNo(mCursor.getString(1),mCursor.getString(0));
                                pList.add(p);


                            }
                        }

                        ContactAdapter ca = new ContactAdapter(con,pList);
                        mContactlist.setAdapter(ca);
                    }


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
