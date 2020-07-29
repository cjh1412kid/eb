/*******************************************************************************
* 项目名称: 甘肃综合业务平台
* 模块名称: 窗体基类
* 当前版本: V 1.0
* 开始时间: 20130702
* 完成时间: 
* 开发者: cwen
* 重要描述:对窗体基本操作进行实现
********************************************************************************
* 版本: V 1.0 
* 描述: cwen 20130702 对窗体基本操作进行实现
*       实现的功能有：点中标题能够拖动。
*******************************************************************************/
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Security.Permissions;
using System.Xml;

namespace NuiteThousandsShoes
{



    [PermissionSet(SecurityAction.Demand, Name = "FullTrust")]
    [System.Runtime.InteropServices.ComVisibleAttribute(true)]



    public partial class BaseForm : Form
    {
        public BaseForm()
        {
            InitializeComponent();
        }

        #region 点中标题能够拖动

        //鼠标拖动变量  
        public bool isMouseDown = false;
        public Point FormLocation;
        public Point mouseOffset;



        //鼠标按下时记录窗体位置  
        public void picBoxTitle_MouseDown(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                isMouseDown = true;
                FormLocation = this.Location;
                mouseOffset = Control.MousePosition;
            }
        }

        //鼠标移动时改变窗体位置  
        public void picBoxTitle_MouseMove(object sender, MouseEventArgs e)
        {
            int x = 0;
            int y = 0;
            if (isMouseDown)
            {
                Point pt = Control.MousePosition;
                x = mouseOffset.X - pt.X;
                y = mouseOffset.Y - pt.Y;
                this.Location = new Point(FormLocation.X - x, FormLocation.Y - y);
            }
        }

        //鼠标松开后释放窗体跟随  
        public void picBoxTitle_MouseUp(object sender, MouseEventArgs e)
        {
            isMouseDown = false;
        }
        #endregion

        #region 初始UI

        public void UIInit(string xmlpath, string imagepath, bool isBackgroundImage = true)
        {
            try
            {
                DataTable dtui = this.ReadUIXMLConfig(xmlpath);
                if (dtui == null || dtui.Rows.Count == 0) return;
                this.UIImageInit(this, dtui, imagepath, isBackgroundImage);
            }
            catch
            {
                ;
            }

        }

        public void UIInit(Control ctl, string xmlpath, string imagepath, bool isBackgroundImage = true)
        {
            try
            {
                DataTable dtui = this.ReadUIXMLConfig(xmlpath);
                if (dtui == null || dtui.Rows.Count == 0) return;
                this.UIImageInit(ctl, dtui, imagepath, isBackgroundImage);
            }
            catch
            {
                ;
            }

        }

        private void UIImageInit(Control ctl, DataTable dt, string imagepath, bool isBackgroundImage)
        {
            foreach (Control c in ctl.Controls)
            {
                if (c.HasChildren && !(c is UserControl))
                {
                    this.UIImageInit(c, dt, imagepath, isBackgroundImage);
                }
                this.SetControlImage(c, dt, imagepath, isBackgroundImage);
            }
        }

        private void SetControlImage(Control ctl, DataTable dt, string imagepath, bool isBackgroundImage)
        {
            foreach (DataRow dr in dt.Rows)
            {
                if (ctl.Name.ToLower() == dr[0].ToString().ToLower())
                {
                    string fileName = imagepath + dr[1].ToString();
                    if (System.IO.File.Exists(fileName) == false) continue;
                    if (isBackgroundImage)
                    {
                        ctl.BackgroundImage = Image.FromFile(fileName);
                    }
                    else
                    {
                        if (ctl is Button)
                        {
                            ((Button)ctl).Image = Image.FromFile(fileName);
                        }
                        else
                        {
                            ctl.BackgroundImage = Image.FromFile(fileName);
                        }
                    }
                    dt.Rows.Remove(dr);
                    break;
                }
            }
        }

        private DataTable ReadUIXMLConfig(string xmlpath)
        {
            DataTable dtresult = new DataTable();
            dtresult.Columns.Add(); dtresult.Columns.Add();
            XmlDocument XmlDoc = new XmlDocument();
            XmlDoc.Load(xmlpath);
            //获取<data>节点的所有子节点
            XmlNodeList nodeList = XmlDoc.SelectSingleNode("Controls").ChildNodes;
            foreach (XmlNode xnode in nodeList)//遍历所有子节点 
            {
                XmlElement my_parm = (XmlElement)xnode;//将子节点类型转换为XmlElement类型
                DataRow dr = dtresult.NewRow();
                dr[0] = my_parm.Name;
                dr[1] = my_parm.InnerText;
                dtresult.Rows.Add(dr);
            }
            return dtresult;
        }

        #endregion

        private void BaseForm_Load(object sender, EventArgs e)
        {
         
        }
    }
}
