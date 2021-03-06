package com.invano.disablesamsungmissedeventwidget;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XposedHelpers;


public class DisableMissedEventWidget implements IXposedHookLoadPackage {

	private boolean isKK = true;
	
	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) 
			isKK = false;
			
		if (!lpparam.packageName.equals(isKK ? "com.android.keyguard" : "android"))
			return;
		
		XposedHelpers.findAndHookMethod(
				isKK ? "com.android.keyguard.sec.ContextualWidgetMonitor" : "com.android.internal.policy.impl.keyguard.sec.ContextualWidgetMonitor",
				lpparam.classLoader,
				"handleMissedEventUpdate",
				XC_MethodReplacement.DO_NOTHING
				);
	}
}

