package com.invano.disablesamsungmissedeventwidget;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XposedHelpers;


public class DisableMissedEventWidget implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		
		if (!lpparam.packageName.equals("com.android.keyguard"))
			return;
		
		XposedHelpers.findAndHookMethod(
				"com.android.keyguard.sec.ContextualWidgetMonitor", 	//class for Android 4.4.2
				lpparam.classLoader,
				"handleMissedEventUpdate",
				XC_MethodReplacement.DO_NOTHING
				);
	}
}

