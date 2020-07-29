using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;

namespace NuiteThousandsShoes
{
    class TaskControl
    {

        [DllImport("user32.dll", EntryPoint = "FindWindowEx", SetLastError = true)]
        public static extern IntPtr FindWindowEx(IntPtr hwndParent, IntPtr hwndChildAfter, string lpszClass, string lpszWindow);

        [DllImport("user32.dll", EntryPoint = "ShowWindow", SetLastError = true)]
        public static extern bool ShowWindow(IntPtr hWnd, uint nCmdShow);


        public static void HideTask(bool isHide)
        {
            try
            {
                IntPtr trayHwnd = FindWindowEx(IntPtr.Zero, IntPtr.Zero, "Shell_TrayWnd", null);
                IntPtr hStar = FindWindowEx(IntPtr.Zero, IntPtr.Zero, "Button", null);

                if (isHide)
                {
                    ShowWindow(trayHwnd, 0);
                    ShowWindow(hStar, 0);
                }
                else
                {
                    ShowWindow(trayHwnd, 1);
                    ShowWindow(hStar, 1);
                }
            }
            catch { }
        }
    }
}
