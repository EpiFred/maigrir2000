package com.maigrir2000v03.slidingmenu.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.maigrir2000v03.R;
import com.maigrir2000v03.slidingmenu.model.RecetteContainer;

public class RecetteAdapter extends ArrayAdapter<RecetteContainer> implements Filterable {

	private ArrayList<RecetteContainer> RecetteList;
	private Context context;
	private RecetteFilter mFilter;
	private ArrayList<RecetteContainer> origRecetteList;

	/*LayoutInflater vi;
	int Resource;
	ViewHolder holder;*/

	public RecetteAdapter(ArrayList<RecetteContainer> List, Context ctx) {
		super(ctx, R.layout.view_recette, List);
		this.RecetteList = List;
		this.context = ctx;
		this.origRecetteList = RecetteList;
	}
	
	/*public RecetteAdapter(Context context, int resource, ArrayList<RecetteContainer> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		RecetteList = objects;
	}*/

	public int getCount() {
		return RecetteList.size();
	}

	public RecetteContainer getItem(int position) {
		return RecetteList.get(position);
	}

	public long getItemId(int position) {
		return RecetteList.get(position).hashCode();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;

		ViewHolder holder = new ViewHolder();
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.view_recette, null);
			
			TextView title = (TextView) v.findViewById(R.id.RecetteTitle);
			TextView desc = (TextView) v.findViewById(R.id.RecetteDesc);

			
			holder.title = title;
			holder.desc = desc;
			
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		RecetteContainer r = RecetteList.get(position);

		holder.title.setText(r.getTitre());
		holder.desc.setText(r.getDescription());
		return v;

	}

	public void resetData() {
		RecetteList = origRecetteList;
	}
	
	static class ViewHolder {
		public TextView title;
		public TextView desc;

	}

	/**
	 * Implementing the Filterable interface.
	 */
	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new RecetteFilter();
		}
		return mFilter;
	}

	private class RecetteFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {

			FilterResults results = new FilterResults();
			// We implement here the filter logic
			if (constraint == null || constraint.length() == 0) {
				// No filter implemented we return all the list
				results.values = origRecetteList;
				results.count = origRecetteList.size();
			}
			else {
				// We perform filtering operation
				ArrayList<RecetteContainer> recettes = new ArrayList<RecetteContainer>();

				for (RecetteContainer r : RecetteList) {
					if (r.getTitre().toUpperCase().contains(constraint.toString().toUpperCase()))
						recettes.add(r);
				}

				results.values = recettes;
				results.count = recettes.size();

			}
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			if (results.count == 0)
				notifyDataSetInvalidated();
			else {
				RecetteList =  (ArrayList<RecetteContainer>) results.values;
				notifyDataSetChanged();
			}

		}

	}

}

