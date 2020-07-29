/**************************************************************************************************
* 项目名称: 南京太亚科技有限责任公司——石化管线GIS平台
* 模块名称: 本类是对常用的进度条显示的控制
* 当前版本: V 1.2
* 开始时间: 2009
* 完成时间: 2009
* 开发者  : .. ALEX ..
* 重要描述:
***************************************************************************************************
* 版本: V 1.2 (..ALEX...20120103.)
* 描述: 由于主窗体加载地图采用线程，则在本类对主窗体的设置时，加以判断是否可以INVOKE
* 版本: V 1.0 (.. ALEX .. .20091208)
* 描述: 工程建立
**************************************************************************************************/

using System.Drawing;
using System.Windows.Forms;

namespace NuiteThousandsShoes
{
    public static class ProgressHelper
    {
        #region 代理定义部分

        private delegate void SetMainFormParamDelegate();//..ALEX...20120103.声明一个委托   

        private static SetMainFormParamDelegate setMainFormParamDelegate = null; 

        #endregion
        public static Form CurMainForm = null;
        public static void ShowProgressForm(string strInfo, int percent)
        {//显示进度窗体    
            Pacia.Display.Progress.ProgressHelper.ShowProgressForm(strInfo, InitConst.ApplicationText, percent);
        }
        public static void ChangeProgressFormPercent(int percent)
        {//仅仅改变进度值
            Pacia.Display.Progress.ProgressHelper.ChangeProgressFormPercent(percent);
        }
        public static void HideProgressForm()
        {//隐藏进度窗体 
            Pacia.Display.Progress.ProgressHelper.HideProgressForm();
            DealMainFormParamOfHideProgressForm();
        }

        #region 隐藏进度条时，对主窗体的一些参数设置
        //..ALEX..20120103.
        private static void DealMainFormParamOfHideProgressForm()
        {
            //重要说明,由于有些窗体在进度条消失后,关闭窗体时会引起:主窗体退到某窗体(操作系统任务栏已打开的某个窗体)的后面,让人感觉主窗体好像消失了.
            //所以,为解决此问题,在窗体关闭时,要将主窗体BringToFront.
            //而且在PROGRESSHELPER.CS中的方法HideProgressForm,也加上了本语句 
            if (CurMainForm != null)
            {
                if (CurMainForm.InvokeRequired)
                {
                    if (setMainFormParamDelegate == null)
                    {
                        setMainFormParamDelegate = new SetMainFormParamDelegate(SetMainFormParam);
                    }
                    CurMainForm.BeginInvoke(setMainFormParamDelegate);
                }
                else
                {
                    SetMainFormParam();
                }
            }
        }
        private static void SetMainFormParam()
        {
            if (CurMainForm != null)
                CurMainForm.BringToFront();
        }

        #endregion
    }
}
