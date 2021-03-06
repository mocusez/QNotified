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
package nil.nadph.qnotified.hook;

import android.app.Activity;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import nil.nadph.qnotified.SyncUtils;
import nil.nadph.qnotified.step.DexDeobfStep;
import nil.nadph.qnotified.util.DexKit;
import nil.nadph.qnotified.util.LicenseStatus;

import static nil.nadph.qnotified.util.Initiator.load;
import static nil.nadph.qnotified.util.Utils.*;

public class $endGiftHook extends CommonDelayableHook {
    private static final $endGiftHook self = new $endGiftHook();

    private $endGiftHook() {
        super("qn_disable_$end_gift", SyncUtils.PROC_MAIN, new DexDeobfStep(DexKit.C_TROOP_GIFT_UTIL));
    }

    public static $endGiftHook get() {
        return self;
    }

    @Override
    public boolean initOnce() {
        try {
            Method m = DexKit.doFindClass(DexKit.C_TROOP_GIFT_UTIL).getDeclaredMethod("a", Activity.class, String.class, String.class, load("com/tencent/mobileqq/app/QQAppInterface"));
            XposedBridge.hookMethod(m, new XC_MethodHook(47) {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (LicenseStatus.sDisableCommonHooks) return;
                    if(!isEnabled()) return;
                    param.setResult(null);
                }
            });
            return true;
        } catch (Throwable e) {
            log(e);
            return false;
        }
    }
}

