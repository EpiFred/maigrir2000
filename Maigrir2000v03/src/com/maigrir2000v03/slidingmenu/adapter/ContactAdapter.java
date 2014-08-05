package com.maigrir2000v03.slidingmenu.adapter;

import java.io.InputStream;
import java.util.ArrayList;

import com.maigrir2000v03.R;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<ContactContainer>{

	ArrayList<ContactContainer> ContactList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public ContactAdapter(Context context, int resource, ArrayList<ContactContainer> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		ContactList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
			holder.tvName = (TextView) v.findViewById(R.id.tvName);
			holder.tvSurname = (TextView) v.findViewById(R.id.tvSurname);
			holder.tvCity = (TextView) v.findViewById(R.id.tvCity);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.imageview.setImageResource(R.drawable.logo_doctor);
		//new DownloadImageTask(holder.imageview).execute(ContactList.get(position).getImage());
		holder.tvName.setText(ContactList.get(position).getName());
		holder.tvSurname.setText(ContactList.get(position).getSurname());
		holder.tvCity.setText("Ville: " + ContactList.get(position).getCity());
		return v;

	}

	static class ViewHolder {
		public ImageView imageview;
		public TextView tvName;
		public TextView tvSurname;
		public TextView tvCity;

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}

}
