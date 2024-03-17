package com.tziogas.articledownloader.utils;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinNT;


public interface Kernel32Ex extends Kernel32 {
    WinNT.HANDLE CreateMutex(
            WinBase.SECURITY_ATTRIBUTES lpMutexAttributes,
            boolean bInitialOwner,
            String lpName
    );

    boolean ReleaseMutex(
            WinNT.HANDLE hMutex
    );
}
