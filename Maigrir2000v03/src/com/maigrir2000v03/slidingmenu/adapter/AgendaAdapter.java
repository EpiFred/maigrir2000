package com.maigrir2000v03.slidingmenu.adapter;

import java.util.ArrayList;

import com.maigrir2000v03.R;
import com.maigrir2000v03.slidingmenu.model.AgendaContainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AgendaAdapter extends ArrayAdapter<AgendaContainer> {

	ArrayList<AgendaContainer> AgendaList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public AgendaAdapter(Context context, int resource, ArrayList<AgendaContainer> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		AgendaList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.tvEvent = (TextView) v.findViewById(R.id.tvEvent);
			holder.tvDateEvent = (TextView) v.findViewById(R.id.tvDateEvent);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.tvEvent.setText(AgendaList.get(position).getTitle());
		holder.tvDateEvent.setText("Évènement le " + AgendaList.get(position).getDate());
		return v;

	}

	static class ViewHolder {
		public TextView tvEvent;
		public TextView tvDateEvent;

	}
}
