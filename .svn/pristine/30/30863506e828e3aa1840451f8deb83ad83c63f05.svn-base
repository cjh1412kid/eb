using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Runtime.InteropServices;

namespace NuiteThousandsShoes
{
    public partial class FrmWarn : BaseForm
    {
        /// <summary>
        /// 硬件连接失败警告窗口
        /// </summary>
        public FrmWarn()
        {
            InitializeComponent();
        }

        #region 变量

        private string fUIXMLPath = Application.StartupPath + @"\Config\UIConfig\DisplayClient.xml";     //UIxml配置文件地址
        private string fImangePath = Application.StartupPath + @"\Images\DisplayClient\";                 //图片目录地址

        #endregion
        private void FrmWarn_Shown(object sender, EventArgs e)
        {
            try
            {
                this.UIInit(fUIXMLPath, fImangePath);
                int x = Screen.PrimaryScreen.WorkingArea.Right - this.Width;
                int y = Screen.PrimaryScreen.Bounds.Height - this.Height;
                this.Location = new Point(x, y);//设置窗体在屏幕右下角显示  
            }
            catch { }
        }               


      

        private void btnclose_Click(object sender, EventArgs e)
        {
            try
            {
            }
            catch { }
        }



    }
}
