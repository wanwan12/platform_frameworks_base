/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.keyguard;

import com.android.systemui.Dumpable;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * Tracks the screen lifecycle.
 */
public class ScreenLifecycle extends Lifecycle<ScreenLifecycle.Observer> implements Dumpable {

    public static final int SCREEN_OFF = 0;
    public static final int SCREEN_TURNING_ON = 1;
    public static final int SCREEN_ON = 2;
    public static final int SCREEN_TURNING_OFF = 3;

    private int mScreenState = SCREEN_OFF;

    public int getScreenState() {
        return mScreenState;
    }

    public void dispatchScreenTurningOn() {
        mScreenState = SCREEN_TURNING_ON;
        dispatch(Observer::onScreenTurningOn);
    }

    public void dispatchScreenTurnedOn() {
        mScreenState = SCREEN_ON;
        dispatch(Observer::onScreenTurnedOn);
    }

    public void dispatchScreenTurningOff() {
        mScreenState = SCREEN_TURNING_OFF;
        dispatch(Observer::onScreenTurningOff);
    }

    public void dispatchScreenTurnedOff() {
        mScreenState = SCREEN_OFF;
        dispatch(Observer::onScreenTurnedOff);
    }

    @Override
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.println("ScreenLifecycle:");
        pw.println("  mScreenState=" + mScreenState);
    }

    public interface Observer {
        default void onScreenTurningOn() {}
        default void onScreenTurnedOn() {}
        default void onScreenTurningOff() {}
        default void onScreenTurnedOff() {}
    }

}
