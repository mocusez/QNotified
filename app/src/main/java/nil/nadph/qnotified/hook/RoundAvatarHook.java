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

import android.app.Application;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import nil.nadph.qnotified.step.DexDeobfStep;
import nil.nadph.qnotified.util.DexKit;

import static nil.nadph.qnotified.util.Utils.*;

public class RoundAvatarHook extends CommonDelayableHook {
    private static final RoundAvatarHook self = new RoundAvatarHook();

    RoundAvatarHook() {
        super("qn_round_avatar", new DexDeobfStep(DexKit.C_SIMPLE_UI_UTIL));
    }

    public static RoundAvatarHook get() {
        return self;
    }

    @Override
    public boolean initOnce() {
        try {
            Method a = null, b = null;
            Class clz = DexKit.doFindClass(DexKit.C_SIMPLE_UI_UTIL);
            for (Method m : clz.getDeclaredMethods()) {
                if (!boolean.class.equals(m.getReturnType())) continue;
                Class[] argt = m.getParameterTypes();
                if (argt.length != 1) continue;
                if (String.class.equals(argt[0])) {
                    if (m.getName().equals("a")) a = m;
                    if (m.getName().equals("b")) b = m;
                }
            }
            XC_MethodHook hook = new XC_MethodHook(43) {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (!isEnabled()) return;
                    param.setResult(false);
                }
            };
            if (b != null) {
                XposedBridge.hookMethod(b, hook);
            } else {
                XposedBridge.hookMethod(a, hook);
            }
            return true;
        } catch (Throwable e) {
            log(e);
            return false;
        }
    }

    @Override
    public boolean isValid() {
        Application app = getApplication();
        return app == null || !isTim(app);
    }
}
