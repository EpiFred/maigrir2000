package com.maigrir2000v03;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class FacebookFragment extends Fragment {

public FacebookFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_facebook, container, false);

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		WebView webview = (WebView) getView().findViewById(R.id.webView1);
		webview.setBackgroundColor(0);
		webview.loadUrl("https://www.facebook.com/maigrir2000");

	}
}
