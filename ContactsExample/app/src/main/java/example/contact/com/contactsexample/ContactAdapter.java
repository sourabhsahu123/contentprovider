package example.contact.com.contactsexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sourabh on 10/13/2017.
 */

public class ContactAdapter extends BaseAdapter {

ArrayList<PhoneNo> pList;
    Context context;

    public ContactAdapter(Context con,ArrayList<PhoneNo>phoneList)
    {
        pList=phoneList;
        context=con;

    }

    @Override
    public int getCount() {
        return pList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater li = LayoutInflater.from(context);
        View v =li.inflate(R.layout.contact_item,null);
        TextView name=(TextView)v.findViewById(R.id.name);
        TextView number=(TextView)v.findViewById(R.id.number);
        name.setText(pList.get(i).getName());
        number.setText(pList.get(i).getNumber());
        return v;
    }
}
