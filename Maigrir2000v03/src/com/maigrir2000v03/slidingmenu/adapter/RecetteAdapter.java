package com.maigrir2000v03.slidingmenu.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maigrir2000v03.R;
import com.maigrir2000v03.slidingmenu.model.RecetteContainer;

public class RecetteAdapter extends ArrayAdapter<RecetteContainer> {
	
	ArrayList<RecetteContainer> RecetteList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public RecetteAdapter(Context context, int resource, ArrayList<RecetteContainer> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		RecetteList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.title = (TextView) v.findViewById(R.id.RecetteTitle);
			holder.desc = (TextView) v.findViewById(R.id.RecetteDesc);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.title.setText(RecetteList.get(position).getTitre());
		holder.desc.setText(RecetteList.get(position).getDescription());
		return v;

	}

	static class ViewHolder {
		public TextView title;
		public TextView desc;

	}

}
