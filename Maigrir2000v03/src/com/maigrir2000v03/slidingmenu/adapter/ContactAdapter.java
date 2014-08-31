package com.maigrir2000v03.slidingmenu.adapter;

import java.util.ArrayList;

import com.maigrir2000v03.R;
import com.maigrir2000v03.slidingmenu.model.ContactContainer;
import com.maigrir2000v03.slidingmenu.model.RecetteContainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<ContactContainer> implements Filterable{

	ArrayList<ContactContainer> ContactList;
	private Context context;
	private ContactFilter mFilter;
	private ArrayList<ContactContainer> origContactList;

	/*LayoutInflater vi;
	int Resource;
	ViewHolder holder;*/


	public ContactAdapter(ArrayList<ContactContainer> List, Context ctx) {
		super(ctx, R.layout.view_contact, List);
		this.ContactList = List;
		this.context = ctx;
		this.origContactList = ContactList;
	}
	/*
	public ContactAdapter(Context context, int resource, ArrayList<ContactContainer> objects) {

		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		ContactList = objects;
	}
	 */
	
	public int getCount() {
		return ContactList.size();
	}

	public ContactContainer getItem(int position) {
		return ContactList.get(position);
	}

	public long getItemId(int position) {
		return ContactList.get(position).hashCode();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		ViewHolder holder = new ViewHolder();

		if (v == null) {
			
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.view_contact, null);

			TextView name = (TextView) v.findViewById(R.id.tvName);
			TextView surname = (TextView) v.findViewById(R.id.tvSurname);
			TextView city = (TextView) v.findViewById(R.id.tvCity);
			ImageView image = (ImageView) v.findViewById(R.id.ivImage);
			
			holder.imageview = image;
			holder.tvName = name;
			holder.tvSurname = surname;
			holder.tvCity = city;

			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		ContactContainer c = ContactList.get(position);

		holder.imageview.setImageResource(R.drawable.logo_doctor);
		holder.tvName.setText(c.getName());
		holder.tvSurname.setText(c.getSurname());
		holder.tvCity.setText("Ville: " + c.getCity());
		return v;

	}

	public void resetData() {
		ContactList = origContactList;
	}

	static class ViewHolder {
		public ImageView imageview;
		public TextView tvName;
		public TextView tvSurname;
		public TextView tvCity;

	}

	/**
	 * Implementing the Filterable interface.
	 */
	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new ContactFilter();
		}
		return mFilter;
	}

	private class ContactFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {

			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = origContactList;
				results.count = origContactList.size();
			}
			else {
				// We perform filtering operation
				ArrayList<ContactContainer> contacts = new ArrayList<ContactContainer>();

				for (ContactContainer c : ContactList) {
					if (c.getSurname().toUpperCase().startsWith(constraint.toString().toUpperCase()))
						contacts.add(c);
					else if (c.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
						contacts.add(c);
				}

				results.values = contacts;
				results.count = contacts.size();

			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {
				ContactList =  (ArrayList<ContactContainer>) results.values;
				notifyDataSetChanged();
			}

		}

	}

	/*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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

	}*/

}
