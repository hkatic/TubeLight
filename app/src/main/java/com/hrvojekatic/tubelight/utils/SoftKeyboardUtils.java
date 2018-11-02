package com.hrvojekatic.tubelight.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

public final class SoftKeyboardUtils {

	public static void hideSoftKeyboard(Context context, View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static void showSoftKeyboard(Context context, View view) {
		if (view.requestFocus()) {
			InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			Objects.requireNonNull(inputMethodManager).showSoftInput(view, InputMethodManager.SHOW_FORCED);
		}
	}
}
