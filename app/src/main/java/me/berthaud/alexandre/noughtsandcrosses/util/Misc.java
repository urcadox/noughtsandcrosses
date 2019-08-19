package me.berthaud.alexandre.noughtsandcrosses.util;

import android.content.Context;
import android.widget.Toast;

public class Misc {
	public static void displayToast(Context c, String s) {
		Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
	}
}
