package org.vikulin.etherwallet.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.vikulin.etherwallet.AddressListActivity;
import org.vikulin.etherwallet.R;
import org.vikulin.etherwallet.icon.Blockies;
import org.web3j.utils.Numeric;

/**
 * Created by vadym on 04.12.16.
 */

public class AddressAdapter extends ArrayAdapter<String> implements AdapterView.OnItemClickListener {

    private final Context context;
    private final String[] objects;
    private final PopupWindow popup;
    private final TextView editText;

    public AddressAdapter(Context context, int textViewResourceId, String[] objects, PopupWindow popup, TextView editText) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
        this.popup = popup;
        this.editText = editText;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.address_layout, parent, false);
        }
        TextView sub=(TextView)convertView.findViewById(R.id.sub);
        String address = objects[position];
        sub.setText(address);
        ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
        icon.setImageDrawable(new BitmapDrawable(context.getResources(), Blockies.createIcon(8, Numeric.prependHexPrefix(address), 10)));
        return convertView;
    }

    public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

        // get the context and main activity to access variables
        // add some animation when a list item was clicked
        Animation fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        fadeInAnimation.setDuration(10);
        v.startAnimation(fadeInAnimation);
        // dismiss the pop up
        popup.dismiss();
        // get the text and set it as the button text
        View text = v.findViewById(R.id.sub);
        if(text==null){
            return;
        }
        String address =  ((TextView)text).getText().toString();
        ((AddressListActivity)(context)).setIcon(address);
        editText.setText(address);
    }
}