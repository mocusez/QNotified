/* QNotified - An Xposed module for QQ/TIM
 * Copyright (C) 2019-2021 xenonhydride@gmail.com
 * https://github.com/ferredoxin/QNotified
 *
 * This software is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see
 * <https://www.gnu.org/licenses/>.
 */
package com.rymmmmm.hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import nil.nadph.qnotified.SyncUtils;
import nil.nadph.qnotified.hook.CommonDelayableHook;
import nil.nadph.qnotified.util.Initiator;
import nil.nadph.qnotified.util.LicenseStatus;
import nil.nadph.qnotified.util.Utils;

import java.lang.reflect.Method;

//去除小程序广告 需要手动点关闭
public class RemoveMiniProgramAd extends CommonDelayableHook {
    private static final RemoveMiniProgramAd self = new RemoveMiniProgramAd();

    public static RemoveMiniProgramAd get() {
        return self;
    }

    protected RemoveMiniProgramAd() {
        super("rq_remove_mini_program_ad", SyncUtils.PROC_ANY & ~(SyncUtils.PROC_MAIN | SyncUtils.PROC_MSF | SyncUtils.PROC_QZONE
            | SyncUtils.PROC_PEAK | SyncUtils.PROC_VIDEO));
    }

    @Override
    public boolean initOnce() {
        try {
            for (Method m : Initiator._GdtMvViewController().getDeclaredMethods()) {
                Class<?>[] argt = m.getParameterTypes();
                if (m.getName().equals("x") && argt.length == 0) {
                    XposedBridge.hookMethod(m, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            if (LicenseStatus.sDisableCommonHooks) return;
                            if (!isEnabled()) return;
                            Utils.iput_object(param.thisObject, "c", Boolean.TYPE, true);
                        }
                    });
                }
            }
            return true;
        } catch (Throwable e) {
            Utils.log(e);
            return false;
        }
    }
}
