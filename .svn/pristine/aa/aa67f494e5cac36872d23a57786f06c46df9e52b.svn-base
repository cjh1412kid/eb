using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NuiteThousandsShoes
{
    public partial class FrmDisplayClient_Play : BaseForm
    {
        #region 变量
        private string fUIXMLPath = Application.StartupPath + @"\Config\UIConfig\DisplayClient.xml";     //UIxml配置文件地址
        private string fImangePath = Application.StartupPath + @"\Images\DisplayClient\";                //图片目录地址
        private ConDisplayClient_Play fConDisplayClient_Play = new ConDisplayClient_Play();
        private bool fBClose = false;
        private delegate void BeginInvokePanel(bool istrue);
        private Point fPointCurrent;                                                                     //当前鼠标坐标
        private UCBrowser ucBrowser;
       
        #endregion

        public FrmDisplayClient_Play()
        {
            InitializeComponent();
            InitConst.ReadConfig();//读取配置文件
            ucBrowser = new UCBrowser();
        }

        private void FrmDisplayClient_Play_Shown(object sender, EventArgs e)
        {
            this.UIInit(fUIXMLPath, fImangePath);//初始化UI
            TaskControl.HideTask(true);//隐藏状态栏
            this.WindowState = FormWindowState.Normal;
            this.Width = Screen.PrimaryScreen.WorkingArea.Width;
            this.Height = Screen.PrimaryScreen.Bounds.Height;
            this.Left = 0;
            this.Top = 0;
            fConDisplayClient_Play.FrmDefault = this;
            //以上是设置窗体状态


            //读写器开始启动
            tmrReaderStatus.Start();

            //开始监听播放的内容（视频、标签）
           
            this.pnlmain.Controls.Add(ucBrowser);
            ucBrowser.Dock = DockStyle.Fill;
            ucBrowser.BringToFront();
            fConDisplayClient_Play.InitData(ucBrowser);
        }

      
        private void btnclose_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void FrmDisplayClient_Play_FormClosed(object sender, FormClosedEventArgs e)
        {
            if (fBClose) return;
            try
            {
                fConDisplayClient_Play.FreeAll();
            }
            catch
            {
            }
            try {
                ucBrowser.Closing();
            }
            catch
            {
            }
            fBClose = true;
            TaskControl.HideTask(false);
            Application.Exit();
        }


        #region 弹出菜单不显示处理
        private void ControlPnlTop(bool isture)
        {
            pnltop.Visible = isture;
        }
        const int WM_LBUTTONDOWN = 528;
        [System.Security.Permissions.PermissionSet(System.Security.Permissions.SecurityAction.Demand, Name = "FullTrust")]
        protected override void WndProc(ref Message message)
        {
            try
            {
                switch (message.Msg)
                {
                    case WM_LBUTTONDOWN:
                        {
                            if (this.Width > 300)
                            {
                                fPointCurrent = Cursor.Position; //获取当前鼠标位置

                                if (pnltop.Visible == true)
                                {
                                    if (fPointCurrent.Y <= 140)
                                    {
                                        this.BeginInvoke(new BeginInvokePanel(ControlPnlTop), true);


                                    }
                                    else
                                    {
                                        this.BeginInvoke(new BeginInvokePanel(ControlPnlTop), false);
                                    }
                                }
                                else
                                {
                                    this.BeginInvoke(new BeginInvokePanel(ControlPnlTop), true);
                                }
                            }
                        }
                        break;
                }
                base.WndProc(ref message);
            }
            catch
            { }
        }
        #endregion

        private void tmrForOut_Tick(object sender, EventArgs e)
        {
            //tmrForOut.Enabled = false;
            //bool bNeedSet = false;
            //try
            //{
            //    if (InitConst.GetCloseShareData("BPlay"))
            //    {
            //        this.Close();
            //    }
            //    else
            //        bNeedSet = true;
            //}
            //catch
            //{
            //}
            //tmrForOut.Enabled = bNeedSet;
        }
        //启动Reader并监控Reader状态
        private void tmrReaderStatus_Tick(object sender, EventArgs e)
        {
            tmrReaderStatus.Enabled = false;
            try
            {
                bool flag = fConDisplayClient_Play.InitAndGetReaderStatus();
                if (!flag)
                    tmrReaderStatus.Enabled = true;
            }
            catch
            {
            }
            finally
            {
            }
        }

    }
}
