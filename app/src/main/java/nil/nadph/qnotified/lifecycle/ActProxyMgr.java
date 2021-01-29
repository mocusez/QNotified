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
package nil.nadph.qnotified.lifecycle;

import androidx.annotation.NonNull;

import nil.nadph.qnotified.activity.*;

/**
 * This class is used to cope with Activity
 */
public class ActProxyMgr {
    public static final String STUB_DEFAULT_ACTIVITY = "com.tencent.mobileqq.activity.photo.CameraPreviewActivity";
    public static final String STUB_TRANSLUCENT_ACTIVITY = "cooperation.qlink.QlinkStandardDialogActivity";
    public static final String ACTIVITY_PROXY_ACTION = "qn_act_proxy_action";
    public static final String ACTIVITY_PROXY_INTENT = "qn_act_proxy_intent";
    public static final int ACTION_RESERVED = 0;
    public static final int ACTION_EXFRIEND_LIST = 1;
    public static final int ACTION_ADV_SETTINGS = 2;
    public static final int ACTION_ABOUT = 3;
    public static final int ACTION_SHELL = 4;
    public static final int ACTION_MUTE_AT_ALL = 5;
    public static final int ACTION_MUTE_RED_PACKET = 6;
    public static final int ACTION_TROUBLESHOOT_ACTIVITY = 8;
    public static final int ACTION_FRIENDLIST_EXPORT_ACTIVITY = 9;
    public static final int ACTION_FAKE_BAT_CONFIG_ACTIVITY = 10;
    public static final int ACTION_CHAT_TAIL_CONFIG_ACTIVITY = 11;
    public static final int ACTION_CHAT_TAIL_TROOPS_ACTIVITY = 12;
    public static final int ACTION_CHAT_TAIL_FRIENDS_ACTIVITY = 13;

    /**
     * TODO: Refactor and remove this method, as well as there constants
     * TODO: Cope with the notification proxy PendingIntent
     *
     * @param action activity number
     * @return The XxxActivity.class object
     * @deprecated Direct use {@code XxxActivity.class} instead.
     */
    public static Class<?> getActivityByAction(int action) {
        switch (action) {
            case ACTION_EXFRIEND_LIST:
                return ExfriendListActivity.class;
            case ACTION_ADV_SETTINGS:
                return SettingsActivity.class;
            case ACTION_MUTE_AT_ALL:
            case ACTION_MUTE_RED_PACKET:
            case ACTION_CHAT_TAIL_TROOPS_ACTIVITY:
                return TroopSelectActivity.class;
            case ACTION_CHAT_TAIL_FRIENDS_ACTIVITY:
                return FriendSelectActivity.class;
            case ACTION_ABOUT:
                return AboutActivity.class;
            case ACTION_TROUBLESHOOT_ACTIVITY:
                return TroubleshootActivity.class;
            case ACTION_FRIENDLIST_EXPORT_ACTIVITY:
                return FriendlistExportActivity.class;
            case ACTION_FAKE_BAT_CONFIG_ACTIVITY:
                return FakeBatCfgActivity.class;
            case ACTION_CHAT_TAIL_CONFIG_ACTIVITY:
                return ChatTailActivity.class;
            default:
                return null;
        }
    }

    // NOTICE: ** If you have created your own package, add it to proguard-rules.pro.**

    public static boolean isModuleProxyActivity(@NonNull String className) {
        if (className == null) {
            return false;
        }
        return className.startsWith("nil.nadph.qnotified.")
            || className.startsWith("me.zpp0196.qqpurify.activity.")
            || className.startsWith("me.singleneuron.")
            || className.startsWith("me.ketal.activity.")
            || className.startsWith("com.rymmmmm.activity.");
    }

    public static boolean isResourceInjectionRequired(@NonNull String className) {
        if (className == null) {
            return false;
        }
        return className.startsWith("me.zpp0196.qqpurify.activity.")
            || className.startsWith("me.singleneuron.");
    }
}
